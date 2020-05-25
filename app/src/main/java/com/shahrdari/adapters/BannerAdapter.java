package com.shahrdari.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.models.BannerModel;
import com.shahrdari.utils.NumberUtil;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private final List<BannerModel> mValues;
    private Context fragment;

    public BannerAdapter(List<BannerModel> mValues, Context fragment) {
        this.mValues = mValues;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.bindTo(holder.mItem);
        holder.itemView.setOnClickListener(v -> {
//                FragmentStack fragmentStack = new FragmentStack(fragment.getActivity(), fragment.getFragmentManager(), R.id.fragment_container);
//                fragmentStack.replace(VideoDetailFragment.newInstance(similarContentsItem.getId() ,similarContentsItem.getMediaType() ));
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mView;
        public TextView tvPrice;
        public BannerModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view.findViewById(R.id.banner_img);
            tvPrice = view.findViewById(R.id.tv_price);
        }


        void bindTo(BannerModel responseItem) {
            tvPrice.setText(NumberUtil.getPriceFormat(responseItem.getMahfi()) + " ريال");
            Glide.with(fragment)
                    .load(BuildConfig.BASEURL + responseItem.getMahpic())
                    /* .diskCacheStrategy(DiskCacheStrategy.NONE)
                     .skipMemoryCache(true)*/
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(this.mView);

        }


    }


}
