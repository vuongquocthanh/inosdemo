package com.idocnet.inos.view.fragment.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.callback.MonthChangeNameListener;
import com.idocnet.inos.utils.Constants;
import com.idocnet.inos.view.adapter.DayViewPagerAdapter;
import com.idocnet.inos.view.fragment.EventFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayFragment extends Fragment {
    @BindView(R.id.day_name)
    TextView dayName;
    @BindView(R.id.day_number)
    TextView dayNumber;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<DayDetailFragment> fragments = new ArrayList<>();
    private int dayOfYear;
    String[] days = new String[] { "Th2", "Th3", "Th4", "Th5", "Th6", "Th7", "CN" };

    private MonthChangeNameListener monthChangeNameListener;

    public static Fragment newInstance(int dayOfYear){
        DayFragment fragment = new DayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("DAYOFYEAR", dayOfYear);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_day, container, false);
        ButterKnife.bind(this, view);

        monthChangeNameListener = (EventFragment) getParentFragment();

        if (getArguments() != null){
            dayOfYear = getArguments().getInt("DAYOFYEAR");
            Log.d("DAYOFYEARLOG", "week: "+dayOfYear);
        }

        fragments.add(DayDetailFragment.newInstance(dayOfYear-1));
        fragments.add(DayDetailFragment.newInstance(dayOfYear));
        fragments.add(DayDetailFragment.newInstance(dayOfYear+1));

        DayViewPagerAdapter adapter = new DayViewPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE) {
                    if (viewPager.getCurrentItem() < 1) {
                        previousSwipe();
                    } else if (viewPager.getCurrentItem() > 1) {
                        nextSwipe();
                    }
                    adapter.setDayOfYear(dayOfYear);
                    viewPager.setCurrentItem(1, false);
                }
            }
        });
        viewPager.setCurrentItem(1,false);

        getDay();
        return view;
    }

    private void getDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR , dayOfYear);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        dayNumber.setText(day);
        dayName.setText(getDayName(calendar));

        if (dayOfYear == 0){
            dayNumber.setTextColor(this.getResources().getColor(R.color.colorAccent));
            dayName.setTextColor(this.getResources().getColor(R.color.colorAccent));
        }else {
            dayNumber.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
            dayName.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        monthChangeNameListener.changeMonthName(Constants.months[calendar.get(Calendar.MONTH)]);
    }

    private void nextSwipe(){
        dayOfYear++;
        getDay();
    }

    private void previousSwipe(){
        dayOfYear--;
        getDay();
    }

    private String getDayName(Calendar calendar){
        return days[calendar.get(Calendar.DAY_OF_WEEK) -1];
    }
}
