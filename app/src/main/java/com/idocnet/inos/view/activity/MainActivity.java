package com.idocnet.inos.view.activity;

import android.os.Bundle;
import android.view.View;

import com.idocnet.inos.R;
import com.idocnet.inos.app.BaseMenuActivity;
import com.idocnet.inos.view.adapter.ViewPagerMainAdapter;
import com.idocnet.inos.view.fragment.EventFragment;
import com.idocnet.inos.view.fragment.HomeFragment;
import com.idocnet.inos.view.fragment.MessagerFragment;
import com.idocnet.inos.view.fragment.NotificationFragment;
import com.idocnet.inos.view.fragment.SolutionFragment;
import com.idocnet.inos.view.fragment.event.EventCreateFragment;
import com.idocnet.inos.view.fragment.event.EventTypeFragment;

public class MainActivity extends BaseMenuActivity implements EventTypeFragment.onClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ViewPagerMainAdapter viewPagerMainAdapter = new ViewPagerMainAdapter(getSupportFragmentManager());
        viewPagerMainAdapter.addFragment(new HomeFragment());
        viewPagerMainAdapter.addFragment(new EventFragment());
        viewPagerMainAdapter.addFragment(new MessagerFragment());
        viewPagerMainAdapter.addFragment(new NotificationFragment());
        viewPagerMainAdapter.addFragment(new SolutionFragment());

        viewPager.setAdapter(viewPagerMainAdapter);
//        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(navIndex);
        viewClick(navIndex);
    }

    public void homeClick(View view){
        viewClick(0);
    }

    public void eventClick(View view){
        viewClick(1);
    }

    public void messagerClick(View view){
        viewClick(2);
    }

    public void notificationClick(View view){
        viewClick(3);
    }

    public void solutionClick(View view){
        viewClick(4);
    }

    private void viewClick(int postion){
        setColorItemNonClickBotNav();
        navIndex = postion;
        setColorItemClickBotNav();
        viewPager.setCurrentItem(navIndex);
    }

    @Override
    public void lnTaskClick() {
        Bundle bundle = new Bundle();
        bundle.putString("data", "true");
        EventCreateFragment eventCreateFragment = new EventCreateFragment();
        eventCreateFragment.setArguments(bundle);
    }
}
