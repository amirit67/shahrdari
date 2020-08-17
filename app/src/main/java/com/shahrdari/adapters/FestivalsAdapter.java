package com.shahrdari.adapters;

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
import com.shahrdari.models.FestivalModel;

import java.util.ArrayList;
import java.util.List;


public class FestivalsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private List<FestivalModel> feed = new ArrayList<>();
    //public List<PayeItem> Tempfeed = new ArrayList<>();
    //private Map<Integer, LinearLayout> details = new HashMap<>();
    //private int s = 0;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public FestivalsAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        if (i == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_festival, null);
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

                Holder.tvTitle.setText(feed.get(i).getTitr());
                Holder.tvTime.setText(feed.get(i).getDate_S_E());
                //Holder.tvAddress.setText(feed.get(i).getAdr());
                Holder.tvArea.setText(feed.get(i).getAdr());
                Glide.with(mContext)
                        .load(BuildConfig.BASEURL + feed.get(i).getPic())
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .into(Holder.imgFestival);

                Holder.imgMap.setOnClickListener(v -> {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(feed.get(i).getGmap()));
                        intent.setPackage("com.google.android.apps.maps");
                        mContext.startActivity(intent);
                    } catch (Exception ex) {
                        try {
                            Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feed.get(i).getGmap()));
                            mContext.startActivity(unrestrictedIntent);
                        } catch (Exception innerEx) {
                            Toast.makeText(mContext, "Please install a maps application", Toast.LENGTH_LONG).show();
                        }
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

    public void addItems(List<FestivalModel> posts) {
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

        ImageView imgFestival, imgMap;
        TextView tvTitle, tvTime, tvAddress, tvArea;

        public MarketHolder(View view) {
            super(view);
            this.imgFestival = view.findViewById(R.id.imgFestival);
            this.imgMap = view.findViewById(R.id.imgMap);
            this.tvTitle = view.findViewById(R.id.tvTitle);
            this.tvTime = view.findViewById(R.id.tvTime);
            this.tvAddress = view.findViewById(R.id.tvAddress);
            this.tvArea = view.findViewById(R.id.tvArea);
        }

        /*public void bind(Object obj) {
            binding.setVariable(BR.eventItem, obj);
            binding.executePendingBindings();
        }*/
    }
}