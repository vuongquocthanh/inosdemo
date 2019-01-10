package com.idocnet.inos.view.fragment.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idocnet.inos.R;
import com.idocnet.inos.view.adapter.MonthAdapter;
import com.idocnet.inos.view.custome.GridSpacingItemDecoration;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonthDetailFragment extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    public static MonthDetailFragment newInstance(int monthOfYear){
        MonthDetailFragment fragment = new MonthDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("MONTH", monthOfYear);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_detail, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(7,1, true));
        if (getArguments() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, getArguments().getInt("MONTH"));
            MonthAdapter adapter = new MonthAdapter(getActivity(),getParentFragment(), calendar);
            recyclerView.setAdapter(adapter);
        }

        return view;
    }

    public void setMonthOfYear(int monthOfYear){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthOfYear);
        MonthAdapter adapter = new MonthAdapter(getActivity(),getParentFragment(), calendar);
        recyclerView.setAdapter(adapter);
    }
}
