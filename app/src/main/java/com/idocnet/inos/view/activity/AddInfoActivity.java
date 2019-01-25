package com.idocnet.inos.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.AddInfo;
import com.idocnet.inos.view.adapter.AddInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddInfoActivity extends AppCompatActivity {
    private Toolbar toolbarTask;
    private ImageView imgBack;
    private EditText edSearch;
    private TextView tvSave;
    private RecyclerView rvAddInfo;

    private List<AddInfo> listInfo;
    private AddInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        initViews();
        fakeData();
        adapter = new AddInfoAdapter(listInfo);
        rvAddInfo.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAddInfo.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,((LinearLayoutManager) layoutManager).getOrientation());
        rvAddInfo.addItemDecoration(dividerItemDecoration);
        imgBack.setOnClickListener(v -> finish());
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        edSearch = findViewById(R.id.edSearch);
        tvSave = findViewById(R.id.tvSave);
        rvAddInfo = findViewById(R.id.rvAddInfo);
    }

    private void fakeData(){
        listInfo = new ArrayList<>();
        listInfo.add(new AddInfo(R.drawable.ic_goals, "Mục đích công việc"));
        listInfo.add(new AddInfo(R.drawable.ic_vehicle, "Phương tiện "));
        listInfo.add(new AddInfo(R.drawable.ic_hand_shake, "Tổ chức bởi"));
        listInfo.add(new AddInfo(R.drawable.ic_tools, "Công cụ hỗ trợ"));
    }
}
