package com.idocnet.inos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private LinearLayout lnNeverRepeat;
    private CheckBox cbNeverRepeat;
    private LinearLayout lnDateRepeat;
    private CheckBox cbDateRepeat;
    private LinearLayout lnNumberRepeat;
    private CheckBox cbNumberRepeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        initViews();
    }

    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        lnNeverRepeat = findViewById(R.id.lnNeverRepeat);
        cbNeverRepeat = findViewById(R.id.cbNeverRepeat);
        lnDateRepeat = findViewById(R.id.lnDateRepeat);
        cbDateRepeat = findViewById(R.id.cbDateRepeat);
        lnNumberRepeat = findViewById(R.id.lnNumberRepeat);
        cbNumberRepeat = findViewById(R.id.cbNumberRepeat);
        ButterKnife.bind(this);
    }
}
