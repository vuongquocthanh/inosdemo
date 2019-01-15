package com.idocnet.inos.view.fragment.event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.idocnet.inos.NotificationActivity;
import com.idocnet.inos.R;
import com.idocnet.inos.RemindActivity;
import com.idocnet.inos.RepeatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventCreateTimeFragment extends Fragment {
    View viewFragment;
    private TextView datePicker, timePicker, dateEndPicker, timeEndPicker;
    private LinearLayout lnRemind, lnRepeat;
    private long datePicked = 0;
    private int REQUEST_REMIND = 1;
    private int REQUEST_REPEAT = 2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_event_create_time, container, false);
        initViewFragment();
        datePicker.setOnClickListener(v -> showDialogDate(datePicker));

        timePicker.setOnClickListener(v -> showDialogTime(timePicker));

        dateEndPicker.setOnClickListener(v -> showDialogDate(dateEndPicker));

        timeEndPicker.setOnClickListener(v -> showDialogTime(timeEndPicker));

        lnRemind.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), RemindActivity.class), REQUEST_REMIND));

        lnRepeat.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), RepeatActivity.class), REQUEST_REPEAT));
        return viewFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initViewFragment(){
        datePicker = viewFragment.findViewById(R.id.datePicker);
        timePicker = viewFragment.findViewById(R.id.timePicker);
        dateEndPicker = viewFragment.findViewById(R.id.dateEndPicker);
        timeEndPicker = viewFragment.findViewById(R.id.timeEndPicker);
        lnRemind = viewFragment.findViewById(R.id.lnRemind);
        lnRepeat = viewFragment.findViewById(R.id.lnRepeat);
    }

    public void showDialogDate(TextView textView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(viewFragment.getContext(), (view1, year1, month1, dayOfMonth) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year1, month1, dayOfMonth);
            datePicked = calendar1.getTimeInMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
            String strDate = simpleDateFormat.format(datePicked);
            textView.setText(strDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    public void showDialogTime( TextView textView){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(viewFragment.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText(hourOfDay+":"+minute);
            }
        }, hour, minutes, true);

        timePickerDialog.show();
    }
}
