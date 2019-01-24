package com.idocnet.inos.view.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.idocnet.inos.AllContactActivity;
import com.idocnet.inos.R;

public class FragmentContact extends Fragment {

    private View viewFragment;
    private LinearLayout lnAllContact;
    private FrameLayout frameLayout;
    private final int REQUEST_ALL_CONTACT = 123;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_contact, container, false);
        initViewFragment();
        lnAllContact.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), AllContactActivity.class), REQUEST_ALL_CONTACT));
        return viewFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initViewFragment(){
        lnAllContact = viewFragment.findViewById(R.id.lnAllContact);
        frameLayout = viewFragment.findViewById(R.id.frameLayout);
    }
}
