package com.idocnet.inos;

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
import android.widget.Toast;

import com.idocnet.inos.model.Color;
import com.idocnet.inos.view.adapter.ChooseColorAdapter;
import com.idocnet.inos.view.fragment.event.EventTypeFragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseColorActivity extends AppCompatActivity{
    private Toolbar toolbarTask;
    private ImageView imgBack;
    private RecyclerView rvChooseColor;
    public static final String COLOR_NAME = "COLOR_NAME";
    public static final String COLOR_IMAGE = "COLOR_IMAGE";

    private List<Color> listColor = new ArrayList<>();
    private ChooseColorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);
        initViews();
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        rvChooseColor = findViewById(R.id.rvChooseColor);
        fakeData();
    }

    private void fakeData(){
        listColor.add(new Color(R.drawable.ic_circle, "Màu mặc định", false));
        listColor.add(new Color(R.drawable.ic_circle_orange, "Màu cam", false));
        listColor.add(new Color(R.drawable.ic_circle_bnana, "Màu chuối", false));
        listColor.add(new Color(R.drawable.ic_circle_hungque, "Màu húng quế", false));
        listColor.add(new Color(R.drawable.ic_circle_green, "Màu xanh lam", false));
        listColor.add(new Color(R.drawable.ic_circle_blue, "Màu mặc định", false));
        listColor.add(new Color(R.drawable.ic_circle_vietquat, "Màu việt quất", false));
        listColor.add(new Color(R.drawable.ic_circle_oaihuong, "Màu oải hương", false));
        listColor.add(new Color(R.drawable.ic_circle_nho, "Màu nho", false));

        adapter = new ChooseColorAdapter(listColor, position -> {

            Intent data = new Intent();
            data.putExtra(COLOR_NAME, listColor.get(position).getTvColor());
            data.putExtra(COLOR_IMAGE, listColor.get(position).getImgColor());
            setResult(Activity.RESULT_OK, data);
            finish();
        });
        rvChooseColor.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvChooseColor.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, ((LinearLayoutManager) layoutManager).getOrientation());
        rvChooseColor.addItemDecoration(dividerItemDecoration);
        imgBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}
