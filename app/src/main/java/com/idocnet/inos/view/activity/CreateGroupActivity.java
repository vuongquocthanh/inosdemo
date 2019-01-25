package com.idocnet.inos.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Group;
import com.idocnet.inos.view.adapter.CreateGroupAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnTextChanged;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupAdapter.Listener {

    private Toolbar toolbarCreateGroup;
    private TextView tvHuy, tvCancelSearch;
    private EditText edGroupName, edSearch;
    private RecyclerView rvCreateGroup;
    private List<Group> listGroup = new ArrayList<>();
    private CreateGroupAdapter adapter = new CreateGroupAdapter(listGroup, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
        tvHuy.setOnClickListener(v -> finish());
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCancelSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvCancelSearch.setOnClickListener(v -> tvCancelSearch.setVisibility(View.GONE));
    }

    private void initView(){
        toolbarCreateGroup = findViewById(R.id.toolbarCreateGroup);
        tvHuy = findViewById(R.id.tvHuy);
        tvCancelSearch = findViewById(R.id.tvCancelSearch);
        edGroupName = findViewById(R.id.edGroupName);
        edSearch = findViewById(R.id.edSearch);
        rvCreateGroup = findViewById(R.id.rvCreateGroup);
        fakeData();
        rvCreateGroup.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCreateGroup.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    private void fakeData(){
        for (int i=0; i<20; i++){
            listGroup.add(new Group(R.drawable.ic_avatar, "Name "+i, false));
        }
    }

    @Override
    public void onItemClick(int poisition) {

    }
}
