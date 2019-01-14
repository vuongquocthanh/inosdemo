package com.idocnet.inos.view.fragment.event;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.idocnet.inos.R;

public class EventTypeFragment extends Fragment {
    private View viewFragment;
    private LinearLayout lnTask;
    private onClickListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_task, container, false);
        lnTask = viewFragment.findViewById(R.id.lnTask);
        lnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.lnTaskClick();
            }
        });
        return viewFragment;
    }

    public interface onClickListener{
        void lnTaskClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (onClickListener) getActivity();
        }catch (ClassCastException e){
            Log.d("ERROR", e.getLocalizedMessage());
        }

    }
}
