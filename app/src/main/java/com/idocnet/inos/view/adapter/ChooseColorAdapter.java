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
import com.idocnet.inos.model.Color;

import java.util.List;

public class ChooseColorAdapter extends RecyclerView.Adapter<ChooseColorAdapter.ViewHolder> {

    private List<Color> listColor;
    private onItemClickListener onItemClickListener;

    public ChooseColorAdapter(List<Color> listColor, ChooseColorAdapter.onItemClickListener onClickListener) {
        this.listColor = listColor;
        this.onItemClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_choose_color, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Color color = listColor.get(i);
        viewHolder.imgColor.setImageResource(color.getImgColor());
        viewHolder.tvColor.setText(color.getTvColor());
        viewHolder.cbColor.setChecked(color.isCbColor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.itemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listColor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgColor;
        private TextView tvColor;
        private CheckBox cbColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgColor = itemView.findViewById(R.id.imgColor);
            tvColor = itemView.findViewById(R.id.tvColor);
            cbColor = itemView.findViewById(R.id.cbColor);
        }
    }

    public interface onItemClickListener{
        void itemClick(int position);
    }
}
