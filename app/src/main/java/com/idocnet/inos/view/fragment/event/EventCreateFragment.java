package com.idocnet.inos.view.fragment.event;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.view.adapter.EventCreatePagerAdapter;
import com.idocnet.inos.view.fragment.EventFragment;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class EventCreateFragment extends Fragment{
    View viewFragment, viewDialog;
    private TextView tvCancel;
    private TextView tvContinues;
    private FrameLayout frameLayout;
    Dialog dialog;
    private Toolbar toolbarEventCreate;
    private ImageView imgCancel;
    private TextView tvSave;
    private TabLayout tablayoutCreateEvent;
    private ViewPager viewPagerCreateEvent;
    private EventCreatePagerAdapter eventCreatePagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_event_create, container, false);
        initViewFragment();
        eventCreatePagerAdapter = new EventCreatePagerAdapter(getChildFragmentManager());
        viewPagerCreateEvent.setAdapter(eventCreatePagerAdapter);
        tablayoutCreateEvent.setupWithViewPager(viewPagerCreateEvent);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return viewFragment;
    }


    public void initViewFragment(){
        frameLayout = viewFragment.findViewById(R.id.frameLayout);
        toolbarEventCreate = viewFragment.findViewById(R.id.toolbarEventCreate);
        imgCancel = viewFragment.findViewById(R.id.imgCancel);
        tvSave = viewFragment.findViewById(R.id.tvSave);
        tablayoutCreateEvent = viewFragment.findViewById(R.id.tablayoutCreateEvent);
        viewPagerCreateEvent = viewFragment.findViewById(R.id.viewPagerCreateEvent);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(viewFragment.getContext());
        LayoutInflater inflater = (LayoutInflater) viewFragment.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        viewDialog = inflater.inflate(R.layout.dialog_cancel_event_create, null);
        initViewDialog();
        builder.setView(viewDialog);
        dialog = builder.create();
        Window window  = dialog.getWindow();
        WindowManager.LayoutParams wlp  = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, new EventFragment());
                transaction.commit();
                dialog.dismiss();
            }
        });
        tvContinues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initViewDialog() {
        tvCancel = viewDialog.findViewById(R.id.tvCancel);
        tvContinues = viewDialog.findViewById(R.id.tvContinues);
    }
}
