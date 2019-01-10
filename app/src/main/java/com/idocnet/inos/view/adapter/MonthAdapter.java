package com.idocnet.inos.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.util.ChineseCalendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.callback.DayClickListener;
import com.idocnet.inos.model.Day;
import com.idocnet.inos.view.fragment.EventFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {
    static final int FIRST_DAY_OF_WEEK =0; // sunday = 0, monday = 1

    private Context context;
    private Calendar calendar, preCalender, nextCalender;
    private List<Day> days = new ArrayList<>();
    private ChineseCalendar chineseCalendar;
    private DayClickListener listener;

    public MonthAdapter(Context context, Fragment fragment, Calendar calendar){
        this.context = context;
        this.calendar = calendar;
        listener = (EventFragment) fragment;
        preCalender = Calendar.getInstance();
        nextCalender = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) == calendar.getActualMinimum(Calendar.MONTH)) {
            preCalender.set(calendar.get(Calendar.YEAR) - 1, calendar.getActualMaximum(Calendar.MONTH), 1);
        }else {
            preCalender.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        }

        if (calendar.get(Calendar.MONTH) == calendar.getActualMaximum(Calendar.MONTH)) {
            nextCalender.set(calendar.get(Calendar.YEAR) + 1, calendar.getActualMinimum(Calendar.MONTH), 1);
        }else {
            nextCalender.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
        }

        fillDay();
    }

    @NonNull
    @Override
    public MonthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day, viewGroup, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        lp.height = viewGroup.getMeasuredHeight() / 6;
        Log.d("HEIGHTLOG", "height: "+viewGroup.getMeasuredHeight());
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvDay.setText(String.valueOf(days.get(i).day));
        if (days.get(i).day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                && days.get(i).month == Calendar.getInstance().get(Calendar.MONTH)
                && days.get(i).year == Calendar.getInstance().get(Calendar.YEAR)){
            viewHolder.tvDay.setBackground(context.getResources().getDrawable(R.drawable.bg_today));
            viewHolder.tvDay.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (days.get(i).day != 0) {
                chineseCalendar = new ChineseCalendar(getDate(days.get(i).day, days.get(i).month, days.get(i).year));
                if (days.get(i).day == 1){
                    String lunarDay = ""+chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)+"/"+(chineseCalendar.get(ChineseCalendar.MONTH)+1);
                    viewHolder.tvLunarDay.setText(lunarDay);
                }else {
                    viewHolder.tvLunarDay.setText(String.valueOf(chineseCalendar.get(ChineseCalendar.DAY_OF_MONTH)));
                }
            }
        }

        if (days.get(i).type.equals("")){
            viewHolder.tvDay.setTextColor(context.getResources().getColor(R.color.colorGray));
            viewHolder.tvLunarDay.setTextColor(context.getResources().getColor(R.color.colorGray));
        }

        viewHolder.bindClick(days.get(i));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.layout)
        ConstraintLayout item;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_lunar_day)
        TextView tvLunarDay;
        @BindView(R.id.events)
        LinearLayout llEvents;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindClick(Day day){
            item.setOnClickListener(__ -> listener.dayClick(day));
        }
    }

    private void fillDay(){
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int preLastDay = preCalender.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i = 0; i < 42; i++){
            days.add(new Day());
        }

        int j = 1;
        if (firstDay > 1) j = firstDay - 1;

        int dayNumber = 1;
        int dayNumberNext = 1;
        int dayNumberPre = preLastDay;

        for (int i = j-2; i>=0; i--){
            days.get(i).day = dayNumberPre;
            days.get(i).month = preCalender.get(Calendar.MONTH);
            days.get(i).year = preCalender.get(Calendar.YEAR);
            days.get(i).dayOfYear = getDayOfYear(preCalender, dayNumberPre);
            days.get(i).type = "";
            dayNumberPre--;
        }

        for (int i = j-1; i < days.size(); i++){
            if (dayNumber <= lastDay){
                days.get(i).day = dayNumber;
                days.get(i).month = calendar.get(Calendar.MONTH);
                days.get(i).year = calendar.get(Calendar.YEAR);
                days.get(i).dayOfYear = getDayOfYear(calendar, dayNumber);
            }else {
                days.get(i).day = dayNumberNext;
                days.get(i).month = nextCalender.get(Calendar.MONTH);
                days.get(i).year = nextCalender.get(Calendar.YEAR);
                days.get(i).dayOfYear = getDayOfYear(nextCalender, dayNumberNext);
                days.get(i).type = "";
                dayNumberNext++;
            }
            dayNumber++;
        }
    }

    private Date getDate(int day, int month, int year){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = "" + year + "-" + (month +1) + "-" + day;
        Log.d("CalendarAdapter", "time: " + time);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private int getDayOfYear(Calendar currCalender, int day){
        currCalender.set(Calendar.DAY_OF_MONTH, day);
        return currCalender.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }
}
