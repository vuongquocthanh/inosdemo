package com.idocnet.inos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SettingMessageActivity extends AppCompatActivity {
    private Toolbar toolbarHome;
    private ImageView imgBackMessenger;
    private TextView titleProfile;
    private Switch switchNotificationMessage;
    private Switch switchNotificationInteractive;
    private Switch switchNotificationSys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_message);
        initViews();
        imgBackMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews(){
        toolbarHome = findViewById(R.id.toolbarHome);
        imgBackMessenger = findViewById(R.id.imgBackMessenger);
        titleProfile = findViewById(R.id.titleProfile);
        switchNotificationMessage = findViewById(R.id.switchNotificationMessage);
        switchNotificationInteractive = findViewById(R.id.switchNotificationInteractive);
        switchNotificationSys = findViewById(R.id.switchNotificationSys);
    }
}
