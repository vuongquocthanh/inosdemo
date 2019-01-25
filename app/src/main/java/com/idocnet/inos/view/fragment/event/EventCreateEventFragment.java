package com.idocnet.inos.view.fragment.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idocnet.inos.view.activity.AddInfoActivity;
import com.idocnet.inos.view.activity.AddParticipantsActivity;
import com.idocnet.inos.view.activity.ChooseColorActivity;
import com.idocnet.inos.view.activity.EventTypeActivity;
import com.idocnet.inos.R;
import com.idocnet.inos.view.activity.VisibilityActivity;

public class EventCreateEventFragment extends Fragment {
    View viewFragment;
    private final String TAG = EventCreateEventFragment.class.getSimpleName();
    private LinearLayout lnTask, lnAddParticipants, lnAddInfo, lnChooseColor, lnPrivate;
    private ImageView imgChooseColor;
    private TextView tvChooseColor, tvPrivate, tvEventType;
    private int REQUEST_CODE_TASK = 1;
    private int REQUEST_CODE_PARTICIPANTS = 2;
    private int REQUEST_CODE_ADD_INFO = 3;
    private int REQUEST_CODE_CHOOSE_COLOR = 456;
    private int REQUEST_CODE_PRIVATE = 5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_event_create_event, container, false);
        initViewFragment();
        lnTask.setOnClickListener(v -> startActivityForResult(new Intent(viewFragment.getContext(), EventTypeActivity.class), REQUEST_CODE_TASK));


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
        imgChooseColor = viewFragment.findViewById(R.id.imgChooseColor);
        tvChooseColor = viewFragment.findViewById(R.id.tvChooseColor);
        tvPrivate = viewFragment.findViewById(R.id.tvPrivate);
        tvEventType = viewFragment.findViewById(R.id.tvEventType);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_CHOOSE_COLOR){
            if (resultCode==Activity.RESULT_OK){
                String colorName = data.getStringExtra(ChooseColorActivity.COLOR_NAME);
                int colorImg = data.getIntExtra(ChooseColorActivity.COLOR_IMAGE, 0);
                imgChooseColor.setImageResource(colorImg);
                tvChooseColor.setText(colorName);
            }
        }

        if (requestCode==REQUEST_CODE_PRIVATE && resultCode==Activity.RESULT_OK){
            String privateName = data.getStringExtra(VisibilityActivity.VISIBILITY_NAME);
            tvPrivate.setText(privateName);
        }

        if (requestCode==REQUEST_CODE_TASK && resultCode==Activity.RESULT_OK){
            String eventTypeName = data.getStringExtra(EventTypeActivity.EVENTTYPE);
            tvEventType.setText(eventTypeName);
        }
    }
}
