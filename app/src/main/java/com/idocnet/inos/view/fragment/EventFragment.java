package com.idocnet.inos.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.idocnet.inos.R;
import com.idocnet.inos.callback.DayClickListener;
import com.idocnet.inos.callback.MonthChangeNameListener;
import com.idocnet.inos.model.Day;
import com.idocnet.inos.utils.Constants;
import com.idocnet.inos.view.adapter.MonthAdapter;
import com.idocnet.inos.view.fragment.event.DayFragment;
import com.idocnet.inos.view.fragment.event.MonthFragment;
import com.idocnet.inos.view.fragment.event.WeekFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventFragment extends Fragment implements MonthChangeNameListener, DayClickListener {
    @BindView(R.id.tv_month_label)
    TextView tvMonthName;
    @BindView(R.id.tv_view_type)
    TextView tvViewType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);

        loadFragment(new MonthFragment(), "Tháng");

        return view;
    }

    @Override
    public void changeMonthName(String name) {
        tvMonthName.setText(name);
    }

    @OnClick({R.id.iv_view_type, R.id.tv_view_type})
    void changeViewClick(){
        PopupMenu popup = new PopupMenu(getActivity(), Objects.requireNonNull(getView()).findViewById(R.id.iv_view_type));
        popup.getMenuInflater()
                .inflate(R.menu.popup_change_event_view, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.month:
                    loadFragment(new MonthFragment(), "Tháng");
                    break;

                case R.id.week:
                    loadFragment(new WeekFragment(), "Tuần");
                    break;

                case R.id.day:
                    loadFragment(new DayFragment(), "Ngày");
                    break;
            }
            return true;
        });
        popup.show();
    }

    private void loadFragment(Fragment fragment, String name){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        tvViewType.setText(name);
    }

    @Override
    public void dayClick(Day day) {
        loadFragment(DayFragment.newInstance(day.dayOfYear), "Ngày");
        tvMonthName.setText(Constants.months[day.month]);
    }
}
