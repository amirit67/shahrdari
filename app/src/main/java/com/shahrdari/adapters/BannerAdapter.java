package com.shahrdari.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.models.BannerModel;
import com.shahrdari.utils.NumberUtil;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends
        SliderViewAdapter<BannerAdapter.SliderAdapterVH> {

    private Context context;
    private List<BannerModel> mSliderItems = new ArrayList<>();

    public BannerAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<BannerModel> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(BannerModel sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        BannerModel sliderItem = mSliderItems.get(position);

        viewHolder.tvPrice.setText(NumberUtil.getPriceFormat(mSliderItems.get(position).getMahfi()) + " ريال");
        Glide.with(viewHolder.itemView)
                .load(BuildConfig.BASEURL + mSliderItems.get(position).getMahpic())
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(false))
                .into(viewHolder.mView);
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView mView;
        TextView tvPrice;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.banner_img);
            tvPrice = itemView.findViewById(R.id.tv_price);
            this.itemView = itemView;
        }
    }

}