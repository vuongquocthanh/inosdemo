package com.idocnet.inos.view.fragment.message;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.idocnet.inos.view.activity.MessengerPrivateActivity;
import com.idocnet.inos.R;
import com.idocnet.inos.model.Conversation;
import com.idocnet.inos.view.adapter.AdapterConversation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FragmentConversation extends Fragment implements AdapterConversation.Listener {
    View viewFragment;
    private RecyclerView rvConversation;
    private AdapterConversation adapter;
    private List<Conversation> listConversation;
    private View viewDialog;
    private Dialog dialog;

    private TextView tvXemHoSo;
    private TextView tvAnCuocTroChuyen;
    private TextView tvBlock;
    private TextView tvPin;
    private TextView tvXoaLienHe;
    private TextView tvHuy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_conversation, container, false);
        initViewFragment();
        adapter = new AdapterConversation(listConversation, this);
        rvConversation.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(viewFragment.getContext());
        rvConversation.setLayoutManager(layoutManager);
        return viewFragment;
    }

    private void initViewFragment(){
        rvConversation = viewFragment.findViewById(R.id.rv_conversation);
        fakeData();
    }

    private void fakeData(){
        listConversation = new ArrayList<>();
        listConversation.add(new Conversation("Cao Hieu", "Lão trư muốn chia tài sản, trở về với vợ hiền, con thơ", "12:08"));
        listConversation.add(new Conversation("Cao Hieu", "Lão trư muốn chia tài sản, trở về với vợ hiền, con thơ", "12:08"));
        listConversation.add(new Conversation("Cao Hieu", "Lão trư muốn chia tài sản, trở về với vợ hiền, con thơ", "12:08"));
        listConversation.add(new Conversation("Cao Hieu", "Lão trư muốn chia tài sản, trở về với vợ hiền, con thơ", "12:08"));
        listConversation.add(new Conversation("Cao Hieu", "Lão trư muốn chia tài sản, trở về với vợ hiền, con thơ", "12:08"));
    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(viewFragment.getContext(), MessengerPrivateActivity.class));
    }

    @Override
    public void onItemLongClick(int position) {
        displayDialog();
    }

    private void displayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(viewFragment.getContext());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        viewDialog = inflater.inflate(R.layout.dialog_conversation_function, null);
        initViewDialog();
        builder.setView(viewDialog);
        dialog = builder.create();
        Window window  = dialog.getWindow();
        WindowManager.LayoutParams wlp  = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    private void initViewDialog() {
        tvXemHoSo = viewDialog.findViewById(R.id.tvXemHoSo);
        tvAnCuocTroChuyen = viewDialog.findViewById(R.id.tvAnCuocTroChuyen);
        tvBlock = viewDialog.findViewById(R.id.tvBlock);
        tvPin = viewDialog.findViewById(R.id.tvPin);
        tvXoaLienHe = viewDialog.findViewById(R.id.tvXoaLienHe);
        tvHuy = viewDialog.findViewById(R.id.tvHuy);
    }
}
