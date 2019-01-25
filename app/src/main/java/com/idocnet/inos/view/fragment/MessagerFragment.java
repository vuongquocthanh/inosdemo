package com.idocnet.inos.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.idocnet.inos.R;
import com.idocnet.inos.view.activity.CreateConversationActivity;
import com.idocnet.inos.view.activity.CreateGroupActivity;
import com.idocnet.inos.view.activity.SettingMessageActivity;
import com.idocnet.inos.view.adapter.MessagePagerAdapter;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessagerFragment extends Fragment {
    private View viewFragment;
    private SearchView searchViewMessage;
    private ConstraintLayout avatar;
    private Toolbar toolbar;
    private CircleImageView imgAvatar;
    private ImageView imgStatus;
    private TabLayout tabLayoutMessage;
    private ViewPager viewPagerMessage;
    private MessagePagerAdapter messagePagerAdapter;
    private FloatingActionButton fabNewConversation;
    private FloatingActionButton fabNewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragment = inflater.inflate(R.layout.fragment_messager, container, false);
        initViews();
        ButterKnife.bind(this, viewFragment);
        avatar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(viewFragment.getContext(), avatar);
                popupMenu.getMenuInflater().inflate(R.menu.menu_status, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.online:
                                imgStatus.setImageResource(R.drawable.ic_circle_green);
                                return true;
                            case R.id.absent:
                                imgStatus.setImageResource(R.drawable.ic_circle_bnana);
                                return true;
                            case R.id.offline:
                                imgStatus.setImageResource(R.drawable.ic_offline);
                                return true;
                            case R.id.busy:
                                imgStatus.setImageResource(R.drawable.ic_busy);
                                return true;
                        }
                        return false;
                    }
                });
                @SuppressLint("RestrictedApi") MenuPopupHelper menuPopupHelper = new MenuPopupHelper(viewFragment.getContext(), (MenuBuilder) popupMenu.getMenu(), avatar);
                menuPopupHelper.setForceShowIcon(true);
                menuPopupHelper.show();
            }
        });

        fabNewConversation.setOnClickListener(v -> {
            startActivity(new Intent(viewFragment.getContext(),CreateConversationActivity.class));
        });


        fabNewGroup.setOnClickListener(v -> {
            startActivity(new Intent(viewFragment.getContext(), CreateGroupActivity.class));
        });
        searchViewMessage.setOnSearchClickListener(v -> avatar.setVisibility(View.GONE));
        searchViewMessage.setOnCloseListener(() -> {
            avatar.setVisibility(View.VISIBLE);
            return false;
        });
        EditText searchEditText = searchViewMessage.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
        messagePagerAdapter = new MessagePagerAdapter(getChildFragmentManager());
        viewPagerMessage.setAdapter(messagePagerAdapter);
        tabLayoutMessage.setupWithViewPager(viewPagerMessage);
        return viewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_message, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notifycation_off:
                Toast.makeText(getContext(), "Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                startActivity(new Intent(viewFragment.getContext(), SettingMessageActivity.class));
                Toast.makeText(getContext(), "Setting", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initViews() {
        searchViewMessage = viewFragment.findViewById(R.id.searchViewMessage);
        avatar = viewFragment.findViewById(R.id.avatar);
        toolbar = viewFragment.findViewById(R.id.toolbar);
        imgAvatar = viewFragment.findViewById(R.id.imgAvatar);
        imgStatus = viewFragment.findViewById(R.id.imgStatus);
        tabLayoutMessage = viewFragment.findViewById(R.id.tabLayoutMessage);
        viewPagerMessage = viewFragment.findViewById(R.id.viewPagerMessage);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_more_vert_));
        fabNewConversation = viewFragment.findViewById(R.id.fabNewConversation);
        fabNewGroup = viewFragment.findViewById(R.id.fabNewGroup);
    }
}
