package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Conversation;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterConversation extends RecyclerView.Adapter<AdapterConversation.ViewHolder> {

    private List<Conversation> listConversation;
    private Listener listener;
    public AdapterConversation(List<Conversation> listConversation, Listener listener) {
        this.listConversation = listConversation;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_conversation, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Conversation conversation = listConversation.get(i);
        viewHolder.tvName.setText(conversation.getName());
        viewHolder.tvLastMess.setText(conversation.getMessage());
        viewHolder.tvTime.setText(conversation.getTime());

        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(i));

        viewHolder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(i);
            return false;
        });
    }

    public interface Listener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    @Override
    public int getItemCount() {
        return listConversation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout avatar;
        private CircleImageView imgAvatar;
        private ImageView imgStatus;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvLastMess;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgStatus = itemView.findViewById(R.id.imgStatus);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvLastMess = itemView.findViewById(R.id.tvLastMess);

        }
    }
}
