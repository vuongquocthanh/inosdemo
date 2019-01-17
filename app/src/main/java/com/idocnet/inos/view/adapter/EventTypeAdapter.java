package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.EventType;
import com.idocnet.inos.model.Visibility;

import java.util.List;

public class EventTypeAdapter extends RecyclerView.Adapter<EventTypeAdapter.ViewHolder> {

    private List<EventType> listEventType;
    private onItemClickListener onItemClickListener;

    public EventTypeAdapter(List<EventType> listEventType, EventTypeAdapter.onItemClickListener onItemClickListener) {
        this.listEventType = listEventType;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_event_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventType eventType = listEventType.get(i);
        viewHolder.tvEventType.setText(eventType.getTvEventType());
        viewHolder.cbEventType.setChecked(eventType.isCbEventType());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.itemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listEventType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEventType;
        private CheckBox cbEventType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventType = itemView.findViewById(R.id.tvEventType);
            cbEventType = itemView.findViewById(R.id.cbEventType);
        }
    }

    public interface onItemClickListener{
        void itemClick(int position);
    }
}
