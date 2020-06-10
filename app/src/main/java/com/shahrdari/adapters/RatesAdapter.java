package com.shahrdari.adapters;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.models.ProductItem;
import com.shahrdari.models.RateModel;
import com.shahrdari.utils.ExpandableTextView;
import com.shahrdari.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;


public class RatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private List<RateModel> feed = new ArrayList<>();
    //public List<PayeItem> Tempfeed = new ArrayList<>();
    //private Map<Integer, LinearLayout> details = new HashMap<>();
    //private int s = 0;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public RatesAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        if (i == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rate, null);
            return new RateHolder(v);
        } else return null;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        if (holder instanceof RateHolder) {
            final RateHolder Holder;
            try {
                Holder = (RateHolder) holder;
                Holder.setIsRecyclable(false);


                Holder.title.setText(feed.get(i).getNerkhGName());
                Holder.start.setText(feed.get(i).getNerkhS());
                Holder.expire.setText(feed.get(i).getNerkhE());

                Holder.detail.setInterpolator(new OvershootInterpolator());

// or set them separately
                Holder.detail.setExpandInterpolator(new OvershootInterpolator());
                Holder.detail.setCollapseInterpolator(new OvershootInterpolator());

                Holder.itemView.setOnClickListener(v -> {
                    Holder.detail.toggle();
                    if (Holder.detail.isExpanded())
                    {
                        Holder.detail.collapse();
                        Holder.more.setText("بیشتر");
                    }
                    else
                    {
                        Holder.detail.expand();
                        Holder.more.setText("بستن");
                    }
                });
                Holder.detail.setText(feed.get(i).getDetails());
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int getItemCount() {
        return ((feed.size() != 0) ? feed.size() : 0);
    }

    public interface OnItemClickListener {
        void onItemClick(String s);
    }


    public void addItems(List<RateModel> posts) {
        this.feed.addAll(posts);
        notifyDataSetChanged();
    }

    public void ClearFeed() {
        feed.clear();
        notifyDataSetChanged();
    }


    public class RateHolder extends RecyclerView.ViewHolder {

        ExpandableTextView detail;
        TextView title, start, expire, more;

        public RateHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.tv_title);
            this.detail = view.findViewById(R.id.tv_detail);
            this.expire = view.findViewById(R.id.tv_expire);
            this.start = view.findViewById(R.id.tv_start);
            this.more = view.findViewById(R.id.tv_more);

        }

        /*public void bind(Object obj) {
            binding.setVariable(BR.eventItem, obj);
            binding.executePendingBindings();
        }*/
    }

}