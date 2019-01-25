package com.idocnet.inos.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.idocnet.inos.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepeatActivity extends AppCompatActivity {
    private Toolbar toolbarTask;
    private ImageView imgBack;
    private LinearLayout lnNoRepeat;
    private LinearLayout lnDayRepeat;
    private LinearLayout lnWeekRepeat;
    private LinearLayout lnMonthRepeat;
    private LinearLayout lnYearRepeat;

    private int REQUEST_CODE_DATE = 1;
    private int REQUEST_CODE_WEEK = 2;
    private int REQUEST_CODE_MONTH = 3;
    private int REQUEST_CODE_YEAR = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);
        initView();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        lnNoRepeat = findViewById(R.id.lnNoRepeat);
        lnDayRepeat = findViewById(R.id.lnDayRepeat);
        lnWeekRepeat = findViewById(R.id.lnWeekRepeat);
        lnMonthRepeat = findViewById(R.id.lnMonthRepeat);
        lnYearRepeat = findViewById(R.id.lnYearRepeat);
        ButterKnife.bind(this);
    }
}
