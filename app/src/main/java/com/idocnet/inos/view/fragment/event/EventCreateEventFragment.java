package com.idocnet.inos.view.fragment.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idocnet.inos.R;
import com.idocnet.inos.TaskActivity;

public class EventCreateEventFragment extends Fragment {
    View viewFragment;
    private LinearLayout lnTask;
    private int REQUEST_CODE = 123;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_event_create_event, container, false);
        lnTask = viewFragment.findViewById(R.id.lnTask);
        lnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(viewFragment.getContext(), TaskActivity.class), REQUEST_CODE);
            }
        });
        return viewFragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
