package com.idocnet.inos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.idocnet.inos.model.Color;
import com.idocnet.inos.view.adapter.ChooseColorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseColorActivity extends AppCompatActivity {
    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvChooseColor;

    private List<Color> listColor;
    private ChooseColorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);
        initViews();
        fakeData();
        adapter = new ChooseColorAdapter(listColor);
        rvChooseColor.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvChooseColor.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());
        rvChooseColor.addItemDecoration(dividerItemDecoration);

        imgBack.setOnClickListener(v -> finish());
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        rvChooseColor = findViewById(R.id.rvChooseColor);
    }

    private void fakeData(){
        listColor = new ArrayList<>();
        listColor.add(new Color(R.drawable.ic_circle, "Màu mặc định", false));
        listColor.add(new Color(R.drawable.ic_circle_orange, "Màu cam", false));
        listColor.add(new Color(R.drawable.ic_circle_bnana, "Màu chuối", false));
        listColor.add(new Color(R.drawable.ic_circle_hungque, "Màu húng quế", false));
        listColor.add(new Color(R.drawable.ic_circle_green, "Màu xanh lam", false));
        listColor.add(new Color(R.drawable.ic_circle_blue, "Màu mặc định", false));
        listColor.add(new Color(R.drawable.ic_circle_vietquat, "Màu việt ", false));
        listColor.add(new Color(R.drawable.ic_circle_oaihuong, "Màu oải hương", false));
        listColor.add(new Color(R.drawable.ic_circle_nho, "Màu nho", false));
    }
}
