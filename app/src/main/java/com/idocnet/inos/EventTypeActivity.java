package com.idocnet.inos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.idocnet.inos.model.Visibility;
import com.idocnet.inos.view.adapter.VisibilityAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventTypeActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvEventType;
    private List<Visibility> listEventType;
    private VisibilityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);
        initViews();
        fakeData();
        adapter = new VisibilityAdapter(listEventType);
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
        listEventType.add(new Visibility("Task", false));
        listEventType.add(new Visibility("Meeting", false));
    }
}
