package com.shahrdari.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shahrdari.R;
import com.shahrdari.models.marqueeObject;

import java.util.List;


public class MarqueeAdapter extends
        RecyclerView.Adapter<MarqueeAdapter.ViewHolder> {


    private static final String TAG = MarqueeAdapter.class.getSimpleName();

    private Context context;
    private List<marqueeObject> list;
    private OnItemClickListener onItemClickListener;

    public MarqueeAdapter(Context context, List<marqueeObject> list,
                          OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_marquee, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        marqueeObject item = list.get(position % list.size());


        holder.bind(item, onItemClickListener);
        Drawable down = context.getResources().getDrawable(R.drawable.ic_down);
        Drawable up = context.getResources().getDrawable(R.drawable.ic_up);
        holder.bindTo(item, up, down);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() * 2;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        ImageView arrow;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final marqueeObject model,
                         final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(getLayoutPosition()));
        }


        public void bindTo(marqueeObject item, Drawable up, Drawable down) {
            name.setText(item.getName());
            number.setText(item.getNumber());
            if (item.isStatusCode()) {
                arrow.setImageDrawable(up);
            } else arrow.setImageDrawable(down);
        }
    }


}