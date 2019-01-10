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
import com.idocnet.inos.callback.DayClickListener;
import com.idocnet.inos.callback.MonthChangeNameListener;
import com.idocnet.inos.model.Day;
import com.idocnet.inos.utils.Constants;
import com.idocnet.inos.view.adapter.WeekViewPagerAdapter;
import com.idocnet.inos.view.fragment.EventFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeekFragment extends Fragment {
    @BindView(R.id.sunday)
    TextView sunday;
    @BindView(R.id.monday)
    TextView monday;
    @BindView(R.id.tuesday)
    TextView tuesday;
    @BindView(R.id.wednesday)
    TextView wednesday;
    @BindView(R.id.thursday)
    TextView thursday;
    @BindView(R.id.friday)
    TextView friday;
    @BindView(R.id.saturday)
    TextView saturday;

    @BindView(R.id.lbl_sunday)
    TextView lblSunday;
    @BindView(R.id.lbl_monday)
    TextView lblMonday;
    @BindView(R.id.lbl_tuesday)
    TextView lblTuesday;
    @BindView(R.id.lbl_wednesday)
    TextView lblWednesday;
    @BindView(R.id.lbl_thursday)
    TextView lblThursday;
    @BindView(R.id.lbl_friday)
    TextView lblFriday;
    @BindView(R.id.lbl_saturday)
    TextView lblSaturday;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Day[] days = new Day[7];
    TextView [] dayOfWeek;
    TextView [] lblDayOfWeek;

    private WeekViewPagerAdapter adapter;
    private List<WeekDetailFragment> fragments = new ArrayList<>();
    private int weekOfYear = 0;

    private DayClickListener dayClickListener;
    private MonthChangeNameListener monthChangeNameListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_week, container, false);
        ButterKnife.bind(this, view);

        dayClickListener = (EventFragment) getParentFragment();
        monthChangeNameListener = (EventFragment) getParentFragment();

        for (int i = 0; i < 7; i++){
            days[i] = new Day();
        }
        dayOfWeek = new TextView[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
        lblDayOfWeek = new TextView[]{lblMonday, lblTuesday, lblWednesday, lblThursday, lblFriday, lblSaturday, lblSunday};

        setDayOnClick(); // click day to Day view

        getWeekDay();

        fragments.add(WeekDetailFragment.newInstance(-1));
        fragments.add(WeekDetailFragment.newInstance(0));
        fragments.add(WeekDetailFragment.newInstance(1));

        adapter = new WeekViewPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d("VIEWPAGER", "onPageScrolled: "+i+", v: "+v);
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
                    adapter.setWeekOfYear(weekOfYear);
                    viewPager.setCurrentItem(1, false);
                }
            }
        });
        viewPager.setCurrentItem(1);

        return view;
    }

    public void getWeekDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR , weekOfYear);
        int delta = -calendar.get(Calendar.DAY_OF_WEEK) + 2;
        calendar.add(Calendar.DAY_OF_MONTH , delta);
        for (int i = 0; i < 7; i++){
            days[i].day = calendar.get(Calendar.DAY_OF_MONTH); // +1 week start with monday
            days[i].month = calendar.get(Calendar.MONTH);
            days[i].year = calendar.get(Calendar.YEAR);
            days[i].dayOfYear = calendar.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR) +1;
            calendar.add(Calendar.DAY_OF_MONTH , 1);
        }

        fillDays();

        monthChangeNameListener.changeMonthName(Constants.months[calendar.get(Calendar.MONTH)]);
    }

    void previousSwipe(){
        weekOfYear--;
        getWeekDay();
    }

    void nextSwipe(){
        weekOfYear++;
        getWeekDay();
    }

    private void fillDays(){
        for (int i = 0; i < 7; i++){
            dayOfWeek[i].setText(String.valueOf(days[i].day));
            int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if (weekOfYear == 0 && days[i].day == today){
                dayOfWeek[i].setTextColor(this.getResources().getColor(R.color.colorAccent));
                lblDayOfWeek[i].setTextColor(this.getResources().getColor(R.color.colorAccent));
            }else {
                dayOfWeek[i].setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
                lblDayOfWeek[i].setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
            }
        }
    }

    private void setDayOnClick(){
        for (int i = 0; i < 7; i++){
            Day day = days[i];
            dayOfWeek[i].setOnClickListener(__ -> dayClickListener.dayClick(day));
            lblDayOfWeek[i].setOnClickListener(__ -> dayClickListener.dayClick(day));
        }
    }
}
