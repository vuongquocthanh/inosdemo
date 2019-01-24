package com.idocnet.inos.view.fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Contact;
import com.idocnet.inos.model.Conversation;
import com.idocnet.inos.model.InosUser;
import com.idocnet.inos.view.adapter.AdapterContact;
import com.idocnet.inos.view.adapter.AdapterConversation;
import com.idocnet.inos.view.adapter.AdapterInosUser;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchAllContact extends Fragment implements AdapterConversation.Listener {
    View viewFragment;
    private RecyclerView rvLienHe;
    private TextView tvXemThem;
    private RecyclerView rvInosUser;
    private RecyclerView rvNhom;
    private RecyclerView rvTinNhan;
    private AdapterConversation adapterConversation;
    private AdapterContact adapterContact;
    private AdapterInosUser adapterInosUser;
    private List<Contact> listContact;
    private List<Conversation> listConversation;
    private List<InosUser> listInosUser;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_search_all_contact, container, false);
        initViewFragment();
        adapterContact = new AdapterContact(listContact);
        adapterConversation = new AdapterConversation(listConversation, this);
        adapterInosUser = new AdapterInosUser(listInosUser);
        RecyclerView.LayoutManager layoutManagerTinNhan = new LinearLayoutManager(viewFragment.getContext());
        RecyclerView.LayoutManager layoutManagerInosUser = new LinearLayoutManager(viewFragment.getContext());
        RecyclerView.LayoutManager layoutManagerContact = new LinearLayoutManager(viewFragment.getContext());
        rvTinNhan.setAdapter(adapterConversation);
        rvTinNhan.setLayoutManager(layoutManagerTinNhan);
        rvInosUser.setAdapter(adapterInosUser);
        rvInosUser.setLayoutManager(layoutManagerInosUser);
        rvLienHe.setAdapter(adapterContact);
        rvLienHe.setLayoutManager(layoutManagerContact);
        return viewFragment;
    }

    public void initViewFragment(){
        rvLienHe = viewFragment.findViewById(R.id.rvLienHe);
        tvXemThem = viewFragment.findViewById(R.id.tvXemThem);
        rvInosUser = viewFragment.findViewById(R.id.rvInosUser);
        rvNhom = viewFragment.findViewById(R.id.rvNhom);
        rvTinNhan = viewFragment.findViewById(R.id.rvTinNhan);
        fakeData();
    }

    private void fakeData(){
        listContact = new ArrayList<>();
        listConversation = new ArrayList<>();
        listInosUser = new ArrayList<>();

        for (int i=0; i<5; i++){
            listInosUser.add(new InosUser("Name "+i, "Email "+i));
            listConversation.add(new Conversation("Name "+i, "Message "+i, "Time "+i));
            listContact.add(new Contact("Name "+i));
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}
