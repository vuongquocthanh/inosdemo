package com.idocnet.inos.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.idocnet.inos.view.fragment.message.FragmentContact;
import com.idocnet.inos.view.fragment.message.FragmentConversation;

public class MessagePagerAdapter extends FragmentPagerAdapter {
    public MessagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentConversation();
            case 1:
                return new FragmentContact();
            default:
                return new FragmentConversation();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Trò chuyện";
            case 1:
                return "Liên hệ";
            default:
                return "Trò chuyện";
        }
    }
}
