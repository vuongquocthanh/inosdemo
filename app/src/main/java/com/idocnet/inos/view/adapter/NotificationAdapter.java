package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<Notification> listNotification;

    public NotificationAdapter(List<Notification> listNotification) {
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Notification notification = listNotification.get(i);
        viewHolder.tvNotificationName.setText(notification.getNameNotification());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.itemView.getContext(), "Click " + i, Toast.LENGTH_SHORT).show();
                if (viewHolder.checkboxNotification.isChecked()) {
                    viewHolder.checkboxNotification.setChecked(false);
                    notification.setCheck(false);
                } else if (!viewHolder.checkboxNotification.isChecked()) {
                    viewHolder.checkboxNotification.setChecked(true);
                    notification.setCheck(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNotificationName;
        private CheckBox checkboxNotification;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNotificationName = itemView.findViewById(R.id.tvNotificationName);
            checkboxNotification = itemView.findViewById(R.id.checkboxNotification);
        }
    }
}
