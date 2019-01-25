package com.idocnet.inos.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.EventType;
import com.idocnet.inos.model.Visibility;
import com.idocnet.inos.view.adapter.EventTypeAdapter;
import com.idocnet.inos.view.adapter.VisibilityAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventTypeActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvEventType;
    private List<EventType> listEventType;
    private EventTypeAdapter adapter;
    public static final String EVENTTYPE = "EVENTTYPE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);
        initViews();
        fakeData();
        adapter = new EventTypeAdapter(listEventType, new EventTypeAdapter.onItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent data = new Intent();
                data.putExtra(EVENTTYPE, listEventType.get(position).getTvEventType());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
        rvEventType.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvEventType.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());
        rvEventType.addItemDecoration(dividerItemDecoration);
        imgBack.setOnClickListener(v -> finish());
    }
    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        rvEventType = findViewById(R.id.rvEventType);
    }

    private void fakeData(){
        listEventType = new ArrayList<>();
        listEventType.add(new EventType("Task", false));
        listEventType.add(new EventType("Meeting", false));
    }
}
