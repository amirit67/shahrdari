package com.shahrdari.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.models.MarketModel;

import java.util.ArrayList;
import java.util.List;


public class MarketsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private List<MarketModel> feed = new ArrayList<>();
    //public List<PayeItem> Tempfeed = new ArrayList<>();
    //private Map<Integer, LinearLayout> details = new HashMap<>();
    //private int s = 0;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public MarketsAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        if (i == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bazaar, null);
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

                Holder.tvTitle.setText(feed.get(i).getName());
                Holder.tvQhorfe.setText(feed.get(i).getQorfe() + " غرفه");
                Holder.tvPhone.setText(feed.get(i).getTell());
                Holder.tvAddress.setText(feed.get(i).getAdr());
                Holder.tvArea.setText(feed.get(i).getMas());
                Glide.with(mContext)
                        .load(BuildConfig.BASEURL + feed.get(i).getPic())
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .into(Holder.imgMarket);

                Holder.tvPhone.setOnClickListener(v -> {
                    Uri number = Uri.parse("tel:" + feed.get(i).getTell());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    mContext.startActivity(callIntent);
                });

                Holder.imgMap.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(feed.get(i).getGmap()));
                    intent.setPackage("com.google.android.apps.maps");
                    try {
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        try {
                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feed.get(i).getGmap()));
                            mContext.startActivity(unrestrictedIntent);
                        } catch (ActivityNotFoundException innerEx) {
                            Toast.makeText(mContext, "Please install a maps application", Toast.LENGTH_LONG).show();
                        }
                    }

                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(feed.get(i));
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

    public void addItems(List<MarketModel> posts) {
        this.feed.addAll(posts);
        notifyDataSetChanged();
    }

    public void ClearFeed() {
        feed.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(MarketModel s);
    }

    public class MarketHolder extends RecyclerView.ViewHolder {

        ImageView imgMarket, imgMap;
        TextView tvTitle, tvQhorfe, tvPhone, tvAddress, tvArea;

        public MarketHolder(View view) {
            super(view);
            this.imgMarket = view.findViewById(R.id.imgMarket);
            this.imgMap = view.findViewById(R.id.imgMap);
            this.tvTitle = view.findViewById(R.id.tvTitle);
            this.tvQhorfe = view.findViewById(R.id.tvQhorfe);
            this.tvPhone = view.findViewById(R.id.tvPhone);
            this.tvAddress = view.findViewById(R.id.tvAddress);
            this.tvArea = view.findViewById(R.id.tvArea);
        }

        /*public void bind(Object obj) {
            binding.setVariable(BR.eventItem, obj);
            binding.executePendingBindings();
        }*/
    }
}