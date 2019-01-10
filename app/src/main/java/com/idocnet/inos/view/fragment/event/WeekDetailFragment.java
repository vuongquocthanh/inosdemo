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
import com.idocnet.inos.model.Event;
import com.idocnet.inos.view.custome.WeekCustomView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;

public class WeekDetailFragment extends Fragment {
    RelativeLayout canvas;
    private int weekOfYear;
    public static WeekDetailFragment newInstance(int weekofyear){
        WeekDetailFragment fragment = new WeekDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("WEEK", weekofyear);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_week_detail, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null){
            weekOfYear = getArguments().getInt("WEEK");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR , weekOfYear);

        }

        canvas = view.findViewById(R.id.layout);

        fillEvents();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setWeekOfYear(int weekOfYear){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR , weekOfYear);

        fillEvents();
    }

    private void fillEvents(){
        List<Event> mEvents = new ArrayList<>();
        canvas.removeAllViews();

        mEvents.add(new Event("Event 2", "1:00", "4:30",0));
        mEvents.add(new Event("Event 1", "2:00", "7:30",0));
        mEvents.add(new Event("Event 2", "1:00", "4:30",1));
        mEvents.add(new Event("Event 2", "1:00", "4:30",3));
        mEvents.add(new Event("Event 2", "5:00", "6:30",5));
        mEvents.add(new Event("Event 2", "5:00", "6:30",5));
        mEvents.add(new Event("Event 3", "9:00", "14:30",1));
        mEvents.add(new Event("Event 4", "14:00", "17:30",4));
        mEvents.add(new Event("Event 5", "18:00", "19:30",7));

        WeekCustomView weekCustomView = new WeekCustomView(getContext(), mEvents, false, weekOfYear);

        canvas.addView(weekCustomView);
    }
}
