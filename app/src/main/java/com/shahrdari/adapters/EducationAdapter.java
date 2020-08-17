package com.shahrdari.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.models.EducationModel;
import com.shahrdari.utils.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;


public class EducationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private List<EducationModel> feed = new ArrayList<>();
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public EducationAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        if (i == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_education, null);
            return new MarketHolder(v);
        } else return null;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        if (holder instanceof MarketHolder) {
            final MarketHolder Holder;
            try {
                Holder = (MarketHolder) holder;
                Holder.setIsRecyclable(false);

                Holder.tvTitle.setText(feed.get(i).getEduTitr());
                Holder.tvArea.setText(feed.get(i).getEduMav());
                //Holder.tvAddress.setText(feed.get(i).getAdr());
                Holder.tvTime.setText(feed.get(i).getEduDetails());
                Glide.with(mContext)
                        .load(BuildConfig.BASEURL + feed.get(i).getEduPic())
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .into(Holder.imgFestival);

                Holder.itemView.setOnClickListener(v -> {
                    Holder.tvTime.toggle();
                    if (Holder.tvTime.isExpanded()) {
                        Holder.tvTime.collapse();
                        Holder.more.setText("بیشتر");
                    } else {
                        Holder.tvTime.expand();
                        Holder.more.setText("بستن");
                    }
                });

            } catch (Exception e) {
            }
        }
    }

    @Override
    public int getItemCount() {
        return ((feed.size() != 0) ? feed.size() : 0);
    }

    public void addItems(List<EducationModel> posts) {
        this.feed.addAll(posts);
        notifyDataSetChanged();
    }

    public void ClearFeed() {
        feed.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String s);
    }

    public class MarketHolder extends RecyclerView.ViewHolder {

        ImageView imgFestival;
        TextView tvTitle, tvAddress, tvArea, more;
        ExpandableTextView tvTime;

        public MarketHolder(View view) {
            super(view);
            this.imgFestival = view.findViewById(R.id.imgFestival);
            this.tvTitle = view.findViewById(R.id.tvTitle);
            this.tvTime = view.findViewById(R.id.tvTime);
            this.tvAddress = view.findViewById(R.id.tvAddress);
            this.tvArea = view.findViewById(R.id.tvArea);
            this.more = view.findViewById(R.id.tv_more);
        }

        /*public void bind(Object obj) {
            binding.setVariable(BR.eventItem, obj);
            binding.executePendingBindings();
        }*/
    }
}