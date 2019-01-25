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
import com.idocnet.inos.model.Visibility;
import com.idocnet.inos.view.adapter.VisibilityAdapter;

import java.util.ArrayList;
import java.util.List;

public class VisibilityActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvVisibility;
    private List<Visibility> listVisibility;
    private VisibilityAdapter adapter;
    public static final String VISIBILITY_NAME = "VISIBILITY_NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visibility);
        initViews();
        fakeData();
        adapter = new VisibilityAdapter(listVisibility, position -> {
            Intent data = new Intent();
            data.putExtra(VISIBILITY_NAME, listVisibility.get(position).getTvVisibility());
            setResult(Activity.RESULT_OK, data);
            finish();
        });
        rvVisibility.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvVisibility.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());
        rvVisibility.addItemDecoration(dividerItemDecoration);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        rvVisibility = findViewById(R.id.rvVisibility);
    }

    private void fakeData(){
        listVisibility = new ArrayList<>();
        listVisibility.add(new Visibility("Private", false));
        listVisibility.add(new Visibility("Organization 1", false));
        listVisibility.add(new Visibility("Organization 2", false));
        listVisibility.add(new Visibility("Công ty CP đầu tư Thành Nam", false));
        listVisibility.add(new Visibility("Công ty TNHH Hoàng ", false));
    }
}
