package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Message;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessengerPrivateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private int VIEW_TYPE_SENT_MESSAGE = 1;
    private int VIEW_TYPE_RECEIVED_MESSAGE = 2;
    private List<Message> listMessage;
    private Listener listener;

    public MessengerPrivateAdapter(List<Message> listMessage, Listener listener) {
        this.listMessage = listMessage;
        this.listener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        return listMessage.get(position) == null ? VIEW_TYPE_RECEIVED_MESSAGE:VIEW_TYPE_SENT_MESSAGE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == VIEW_TYPE_SENT_MESSAGE){
            return new SentMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sent_message, viewGroup, false));
        }else if(i == VIEW_TYPE_RECEIVED_MESSAGE){
            return new ReceivedMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_received_message, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = listMessage.get(i);
        if (viewHolder instanceof SentMessageViewHolder){
            SentMessageViewHolder holder = (SentMessageViewHolder) viewHolder;
            holder.tvSentMessage.setText(message.getMessage());
            holder.tvTime.setText(message.getTime());
            holder.itemView.setOnClickListener(v -> listener.onItemClick(i));
            holder.itemView.setOnLongClickListener(v -> {
                listener.onItemLongClick(i);
                return false;
            });
        }else if (viewHolder instanceof ReceivedMessageViewHolder){
            ReceivedMessageViewHolder holder = (ReceivedMessageViewHolder) viewHolder;
            holder.civAvatar.setImageResource(message.getAvatar());
            holder.tvReceivedMessage.setText(message.getMessage());
            holder.tvTime.setText(message.getTime());
            holder.itemView.setOnClickListener(v -> listener.onItemClick(i));
            holder.itemView.setOnLongClickListener(v -> {
                listener.onItemLongClick(i);
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return listMessage.size();
    }

    public interface Listener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public class SentMessageViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSentMessage;
        private TextView tvTime;
        SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSentMessage = itemView.findViewById(R.id.tvSentMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    public class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView civAvatar;
        private TextView tvReceivedMessage;
        private TextView tvTime;
        ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            civAvatar = itemView.findViewById(R.id.civAvatar);
            tvReceivedMessage = itemView.findViewById(R.id.tvReceivedMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
