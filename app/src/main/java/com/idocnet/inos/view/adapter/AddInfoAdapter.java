package com.idocnet.inos.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idocnet.inos.R;
import com.idocnet.inos.model.AddInfo;

import java.util.List;

public class AddInfoAdapter extends RecyclerView.Adapter<AddInfoAdapter.ViewHolder> {

    private List<AddInfo> listInfo;

    public AddInfoAdapter(List<AddInfo> listInfo) {
        this.listInfo = listInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_add_info, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AddInfo addInfo = listInfo.get(i);
        viewHolder.imgIcon.setImageResource(addInfo.getImgIconInfo());
        viewHolder.tvAddInfo.setText(addInfo.getInfoName());
    }

    @Override
    public int getItemCount() {
        return listInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgIcon;
        private TextView tvAddInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvAddInfo = itemView.findViewById(R.id.tvAddInfo);

        }
    }
}
