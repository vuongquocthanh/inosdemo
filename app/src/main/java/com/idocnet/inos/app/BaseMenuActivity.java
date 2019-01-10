package com.idocnet.inos.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.idocnet.inos.R;

public class BaseMenuActivity extends BaseActivity {
    public static int navIndex;
    protected ConstraintLayout botNav;
    protected ImageView ivHome;
    protected ImageView ivHomeGray;
    protected ImageView ivEvent;
    protected ImageView ivEventGray;
    protected ImageView ivMessager;
    protected ImageView ivMessagerGray;
    protected ImageView ivNotification;
    protected ImageView ivNotificationGray;
    protected ImageView ivSolution;
    protected ImageView ivSolutionGray;
    protected ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bottom_menu);

    }

    protected void initView(){
        botNav = findViewById(R.id.bot_nav);
        ivHome = findViewById(R.id.iv_home);
        ivHomeGray = findViewById(R.id.iv_home_gray);
        ivEvent = findViewById(R.id.iv_event);
        ivEventGray = findViewById(R.id.iv_event_gray);
        ivMessager = findViewById(R.id.iv_messager);
        ivMessagerGray = findViewById(R.id.iv_messager_gray);
        ivNotification = findViewById(R.id.iv_notification);
        ivNotificationGray = findViewById(R.id.iv_notification_gray);
        ivSolution = findViewById(R.id.iv_solution);
        ivSolutionGray = findViewById(R.id.iv_solution_gray);
        viewPager = findViewById(R.id.viewPager);
    }

    protected void setColorItemNonClickBotNav() {
        switch (navIndex) {
            case 0:
                ivHome.setVisibility(View.INVISIBLE);
                ivHomeGray.setVisibility(View.VISIBLE);
                break;
            case 1:
                ivEvent.setVisibility(View.INVISIBLE);
                ivEventGray.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivMessager.setVisibility(View.INVISIBLE);
                ivMessagerGray.setVisibility(View.VISIBLE);
                break;
            case 3:
                ivNotification.setVisibility(View.INVISIBLE);
                ivNotificationGray.setVisibility(View.VISIBLE);
                break;
            case 4:
                ivSolution.setVisibility(View.INVISIBLE);
                ivSolutionGray.setVisibility(View.VISIBLE);
                break;
        }
    }

    protected void setColorItemClickBotNav() {
        switch (navIndex) {
            case 0:
                ivHome.setVisibility(View.VISIBLE);
                ivHomeGray.setVisibility(View.INVISIBLE);
                break;
            case 1:
                ivEvent.setVisibility(View.VISIBLE);
                ivEventGray.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ivMessager.setVisibility(View.VISIBLE);
                ivMessagerGray.setVisibility(View.INVISIBLE);
                break;
            case 3:
                ivNotification.setVisibility(View.VISIBLE);
                ivNotificationGray.setVisibility(View.INVISIBLE);
                break;
            case 4:
                ivSolution.setVisibility(View.VISIBLE);
                ivSolutionGray.setVisibility(View.INVISIBLE);
                break;
        }
    }

}
