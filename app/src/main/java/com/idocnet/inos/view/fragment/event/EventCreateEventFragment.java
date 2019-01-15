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

import com.idocnet.inos.AddInfoActivity;
import com.idocnet.inos.AddParticipantsActivity;
import com.idocnet.inos.ChooseColorActivity;
import com.idocnet.inos.R;
import com.idocnet.inos.TaskActivity;
import com.idocnet.inos.VisibilityActivity;

public class EventCreateEventFragment extends Fragment {
    View viewFragment;
    private LinearLayout lnTask, lnAddParticipants, lnAddInfo, lnChooseColor, lnPrivate;
    private int REQUEST_CODE_TASK = 1;
    private int REQUEST_CODE_PARTICIPANTS = 2;
    private int REQUEST_CODE_ADD_INFO = 3;
    private int REQUEST_CODE_CHOOSE_COLOR = 4;
    private int REQUEST_CODE_PRIVATE = 5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_event_create_event, container, false);
        initViewFragment();
        lnTask.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), TaskActivity.class), REQUEST_CODE_TASK));

        lnAddParticipants.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), AddParticipantsActivity.class), REQUEST_CODE_PARTICIPANTS));

        lnAddInfo.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), AddInfoActivity.class), REQUEST_CODE_ADD_INFO));

        lnChooseColor.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), ChooseColorActivity.class), REQUEST_CODE_CHOOSE_COLOR));

        lnPrivate.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), VisibilityActivity.class), REQUEST_CODE_PRIVATE));
        return viewFragment;
    }

    public void initViewFragment(){
        lnTask = viewFragment.findViewById(R.id.lnTask);
        lnAddParticipants = viewFragment.findViewById(R.id.lnAddParticipants);
        lnAddInfo = viewFragment.findViewById(R.id.lnAddInfo);
        lnChooseColor = viewFragment.findViewById(R.id.lnChooseColor);
        lnPrivate = viewFragment.findViewById(R.id.lnPrivate);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
