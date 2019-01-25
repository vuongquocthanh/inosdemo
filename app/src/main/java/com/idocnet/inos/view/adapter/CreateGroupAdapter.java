package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Group;

import java.util.List;

public class CreateGroupAdapter extends RecyclerView.Adapter<CreateGroupAdapter.ViewHolder> {

    private List<Group> listGroup;
    private Listener listener;

    public CreateGroupAdapter(List<Group> listGroup, Listener listener) {
        this.listGroup = listGroup;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_create_group, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Group group = listGroup.get(i);
        viewHolder.imgAvatar.setImageResource(group.getAvatar());
        viewHolder.tvName.setText(group.getName());
        viewHolder.checkbox.setChecked(group.isCheck());
        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(i));
    }

    @Override
    public int getItemCount() {
        return listGroup.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar;
        private TextView tvName;
        private CheckBox checkbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface Listener{
        void onItemClick(int poisition);
    }
}
