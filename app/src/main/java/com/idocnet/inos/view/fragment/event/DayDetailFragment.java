package com.idocnet.inos.view.fragment.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Day;
import com.idocnet.inos.model.Event;
import com.idocnet.inos.view.custome.WeekCustomView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

public class DayDetailFragment extends Fragment {

    RelativeLayout canvas;
    private int dayOfYear;

    public static DayDetailFragment newInstance(int dayOfYear){
        DayDetailFragment fragment = new DayDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("DAY", dayOfYear);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_day_detail, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null){
            dayOfYear = getArguments().getInt("DAY");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, dayOfYear);
        }

        canvas = view.findViewById(R.id.day_view);

        fillEvent();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void setDayOfYear(int dayOfYear){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, dayOfYear);

        fillEvent();
    }

    private void fillEvent(){
        List<Event> mEvents = new ArrayList<>();
        canvas.removeAllViews();
        mEvents.add(new Event("Event 2", "1:00", "4:30",0));
        mEvents.add(new Event("Event 1", "2:00", "7:30",0));
        mEvents.add(new Event("Event 2", "1:00", "4:30",0));
        mEvents.add(new Event("Event 2", "1:00", "4:30",0));
        mEvents.add(new Event("Event 2", "5:00", "6:30",0));
        mEvents.add(new Event("Event 2", "5:00", "6:30",0));
        mEvents.add(new Event("Event 3", "9:00", "14:30",0));
        mEvents.add(new Event("Event 4", "14:00", "17:30",0));
        mEvents.add(new Event("Event 5", "18:00", "19:30",0));

        WeekCustomView weekCustomView = new WeekCustomView(getContext(), mEvents, true, dayOfYear);

        canvas.addView(weekCustomView);
    }
}
