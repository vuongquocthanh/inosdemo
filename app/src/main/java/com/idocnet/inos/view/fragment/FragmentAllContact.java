package com.idocnet.inos.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Contact;
import com.idocnet.inos.view.adapter.AdapterContact;

import java.util.ArrayList;
import java.util.List;

public class FragmentAllContact extends Fragment {
    View viewFragment;
    private ImageView imgBackAll;
    private RecyclerView rvAllContact;
    private AdapterContact adapter;
    private List<Contact> listContact;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_all_contact, container, false);
        initViewFragment();
        imgBackAll.setOnClickListener(v -> getActivity().finish());
        adapter = new AdapterContact(listContact);
        rvAllContact.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(viewFragment.getContext());
        rvAllContact.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewFragment.getContext(), ((LinearLayoutManager) layoutManager).getOrientation());
        rvAllContact.addItemDecoration(dividerItemDecoration);
        return viewFragment;
    }

    public void initViewFragment(){
        imgBackAll = viewFragment.findViewById(R.id.imgBackAll);
        rvAllContact = viewFragment.findViewById(R.id.rvAllContact);
        fakeData();
    }

    private void fakeData(){
        listContact = new ArrayList<>();
        for (int i=0; i<5; i++){
            listContact.add(new Contact("Hoang Tung "+i));
        }
    }
}
