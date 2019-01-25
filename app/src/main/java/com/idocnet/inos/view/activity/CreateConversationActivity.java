package com.idocnet.inos.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.idocnet.inos.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateConversationActivity extends AppCompatActivity {
    private TextView tvHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conversation);
        initView();
    }

    @OnClick(R.id.tvHuy)
    void tvHuyClick(){
        finish();
    }


    private void initView(){
        tvHuy = findViewById(R.id.tvHuy);
        ButterKnife.bind(this);
    }
}
