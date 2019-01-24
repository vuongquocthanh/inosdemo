package com.idocnet.inos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity {

    private Toolbar toolbarTask;
    private ImageView imgBack;
    private LinearLayout lnXemHoSo;
    private LinearLayout lnTaonhom;
    private LinearLayout lnImgShared;
    private LinearLayout lnDocumentShared;
    private LinearLayout lnBlock;
    private LinearLayout lnDeleteContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initViews();
    }

    @OnClick(R.id.imgBack)
    void imgBackClick(){
        finish();
    }
    private void initViews(){
        toolbarTask = findViewById(R.id.toolbarTask);
        imgBack = findViewById(R.id.imgBack);
        lnXemHoSo = findViewById(R.id.lnXemHoSo);
        lnTaonhom = findViewById(R.id.lnTaonhom);
        lnImgShared = findViewById(R.id.lnImgShared);
        lnDocumentShared = findViewById(R.id.lnDocumentShared);
        lnBlock = findViewById(R.id.lnBlock);
        lnDeleteContact = findViewById(R.id.lnDeleteContact);
        ButterKnife.bind(this);
    }
}
