package com.idocnet.inos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddParticipantsActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private EditText edSearch;
    private TextView tvSave;
    private RecyclerView rvAddParticipants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);
        initViews();
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
        edSearch = findViewById(R.id.edSearch);
        tvSave = findViewById(R.id.tvSave);
        rvAddParticipants = findViewById(R.id.rvAddParticipants);

    }
}
