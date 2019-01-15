package com.idocnet.inos;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RemindActivity extends AppCompatActivity {
    private Toolbar toolbarTask;
    private LinearLayout lnNotification;
    private TextView tvNotification;
    private LinearLayout lnTruoc;
    private TextView tvTruoc;
    private ImageView imgBack;
    private int REQUEST_NOTIFICATION = 1;
    private int REQUEST_BEFORE = 1;

    private String TAG = RemindActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        initViews();
        imgBack.setOnClickListener(v -> finish());
        lnNotification.setOnClickListener(v -> startActivityForResult(new Intent(RemindActivity.this, NotificationActivity.class), REQUEST_NOTIFICATION));
        lnTruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RemindActivity.this, BeforeEventActivity.class), REQUEST_BEFORE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_BEFORE){
            if (resultCode==Activity.RESULT_OK){
                String timeNumber = data.getStringExtra(BeforeEventActivity.TimeNumber);
                String timeType = data.getStringExtra(BeforeEventActivity.TimeType);
                if (timeNumber.equals("")){
                    tvTruoc.setText("");
                }else{
                    tvTruoc.setText(timeNumber+" "+timeType);
                }
            }
        }
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        lnNotification = findViewById(R.id.lnNotification);
        tvNotification = findViewById(R.id.tvNotification);
        lnTruoc = findViewById(R.id.lnTruoc);
        tvTruoc = findViewById(R.id.tvTruoc);
        imgBack = findViewById(R.id.imgBack);
    }
}
