package com.idocnet.inos;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.idocnet.inos.view.fragment.FragmentAllContact;
import com.idocnet.inos.view.fragment.message.FragmentSearchAllContact;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ConstraintLayout avatar;
    private CircleImageView imgAvatar;
    private ImageView imgStatus;
    private SearchView searchViewAllContact;
    private ImageView imgBack;
    private RecyclerView rvAllContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);
        initView();
        showFragment(new FragmentAllContact());
        EditText searchEditText = searchViewAllContact.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
        searchViewAllContact.setOnSearchClickListener(v -> {
            showFragment(new FragmentSearchAllContact());
            avatar.setVisibility(View.GONE);
            imgBack.setVisibility(View.VISIBLE);
            imgBack.setOnClickListener(v1 -> showFragment(new FragmentAllContact()));
        });

        searchViewAllContact.setOnCloseListener(() -> {
            showFragment(new FragmentAllContact());
            avatar.setVisibility(View.VISIBLE);
            imgBack.setVisibility(View.GONE);
            return false;
        });
    }


    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        avatar = findViewById(R.id.avatar);
        imgAvatar = findViewById(R.id.imgAvatar);
        imgStatus = findViewById(R.id.imgStatus);
        searchViewAllContact = findViewById(R.id.searchViewAllContact);
        imgBack = findViewById(R.id.imgBack);
        rvAllContact = findViewById(R.id.rvAllContact);
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }
}
