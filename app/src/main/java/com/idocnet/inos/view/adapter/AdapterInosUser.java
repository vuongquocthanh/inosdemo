package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.InosUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterInosUser extends RecyclerView.Adapter<AdapterInosUser.ViewHolder> {
    private List<InosUser> listInosUser;

    public AdapterInosUser(List<InosUser> listInosUser) {
        this.listInosUser = listInosUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inos_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InosUser inosUser = listInosUser.get(i);
        viewHolder.tvName.setText(inosUser.getName());
        viewHolder.tvEmail.setText(inosUser.getEmail());
    }

    @Override
    public int getItemCount() {
        return listInosUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civAvatar;
        private TextView tvName;
        private TextView tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            civAvatar = itemView.findViewById(R.id.civAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);

        }
    }
}
