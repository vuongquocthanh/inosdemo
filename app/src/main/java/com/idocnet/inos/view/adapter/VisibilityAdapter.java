package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.Visibility;

import java.util.List;

public class VisibilityAdapter extends RecyclerView.Adapter<VisibilityAdapter.ViewHolder> {

    private List<Visibility> listVisibility;

    public VisibilityAdapter(List<Visibility> listVisibility) {
        this.listVisibility = listVisibility;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_visibility, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Visibility visibility = listVisibility.get(i);
        viewHolder.tvVisibility.setText(visibility.getTvVisibility());
        viewHolder.cbVisibility.setChecked(visibility.isCbVisibility());
    }

    @Override
    public int getItemCount() {
        return listVisibility.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvVisibility;
        private CheckBox cbVisibility;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVisibility = itemView.findViewById(R.id.tvVisibility);
            cbVisibility = itemView.findViewById(R.id.cbVisibility);

        }
    }
}
