package com.idocnet.inos.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.idocnet.inos.R;

public class NotificationActivity extends AppCompatActivity {
    private ImageView imgBack;
    private LinearLayout lnNotification, lnEmail, lnSMS;
    private CheckBox cbNotification, cbEmail, cbSMS;
    public static String CHECK_NOTIFICATION = "CHECK_NOTIFICATION";
    public static String CHECK_EMAIL = "CHECK_EMAIL";
    public static String CHECK_SMS = "CHECK_SMS";
    private String TAG = NotificationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbNotification.isChecked()){
                    cbNotification.setChecked(false);
                }else{
                    cbNotification.setChecked(true);
                }
            }
        });

        lnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEmail.isChecked()){
                    cbEmail.setChecked(false);
                }else{
                    cbEmail.setChecked(true);
                }
            }
        });

        lnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSMS.isChecked()){
                    cbSMS.setChecked(false);
                }else{
                    cbSMS.setChecked(true);
                }
            }
        });
    }

    private void initViews(){
        imgBack = findViewById(R.id.imgBack);
        lnNotification = findViewById(R.id.lnNotification);
        lnEmail = findViewById(R.id.lnEmail);
        lnSMS = findViewById(R.id.lnSMS);

        cbNotification = findViewById(R.id.cbNotification);
        cbEmail = findViewById(R.id.cbEmail);
        cbSMS = findViewById(R.id.cbSMS);
    }
}
