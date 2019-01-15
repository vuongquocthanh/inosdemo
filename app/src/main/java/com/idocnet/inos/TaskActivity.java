package com.idocnet.inos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class TaskActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvEventType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        initViews();
        imgBack.setOnClickListener(v -> finish());
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        rvEventType = findViewById(R.id.rvEventType);

    }
}
