package com.shahrdari.adapters;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.shahrdari.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private List<ProductItem> feed = new ArrayList<>();
    //public List<PayeItem> Tempfeed = new ArrayList<>();
    //private Map<Integer, LinearLayout> details = new HashMap<>();
    //private int s = 0;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public ProductsAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        if (i == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, null);
            return new ProductHolder(v);
        } else return null;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        if (holder instanceof ProductHolder) {
            final ProductHolder Holder;
            try {
                Holder = (ProductHolder) holder;
                Holder.setIsRecyclable(false);


                Holder.unitTextView.setVisibility(View.GONE);
                int sizeTitle = feed.get(i).getMahname().length() + feed.get(i).getMahdarajeh().length() + 2;
                int sizeUnit = feed.get(i).getMahvahed().length() + feed.get(i).getMahsize().length() + 1;
                Spannable nametoSpan = new SpannableString(feed.get(i).getMahname() + " " + feed.get(i).getMahdarajeh() + " " + "(" + feed.get(i).getMahsize() + feed.get(i).getMahvahed() + ")");
                nametoSpan.setSpan(new TextAppearanceSpan(mContext, R.style.title), 0, sizeTitle, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                nametoSpan.setSpan(new TextAppearanceSpan(mContext, R.style.unit), sizeTitle, sizeTitle + sizeUnit + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Holder.name.setText(nametoSpan);

//        holder.name.setText(product.getName() + " " + product.getBrand());
//        holder.unitTextView.setText("(" + product.getUnit() + ")");

                Holder.price.setVisibility(View.VISIBLE);
                Holder.price.setText(NumberUtil.getPriceFormat(feed.get(i).getMahfi()) /*+ Constants.CURRENCY*/);
                /*if (feed.get(i).getSellPrice() < feed.get(i).getStickerPrice()) {
                    Holder.price.setPaintFlags(Holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else if (feed.get(i).getSellPrice() == feed.get(i).getStickerPrice()) {
                    Holder.price.setVisibility(View.GONE);
                } else {
                    Holder.price.setPaintFlags(Holder.price.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }*/


                Holder.code.setText(String.valueOf(feed.get(i).getMahcod()));
                Holder.tvDescription.setText(String.valueOf(feed.get(i).getMahdetail()));

                Glide.with(mContext)
                        .load(BuildConfig.BASEURL + feed.get(i).getMahpic())
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .into(Holder.image);

                Holder.image.setOnClickListener(v -> {
                    onItemClickListener.onItemClick(feed.get(i).getMahpic());
                });
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


    public void addItems(List<ProductItem> posts) {
        this.feed.addAll(posts);
        notifyDataSetChanged();
    }

    public void ClearFeed() {
        feed.clear();
        notifyDataSetChanged();
    }


    public class ProductHolder extends RecyclerView.ViewHolder {


        ViewDataBinding binding;
        ImageView image;
        TextView name, price, code, unitTextView, tvDescription;
        FrameLayout imagePlaceholder;

        public ProductHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.image_view_image);
            this.name = view.findViewById(R.id.text_view_name);
            this.price = view.findViewById(R.id.text_view_price);
            this.code = view.findViewById(R.id.text_view_code);
            this.unitTextView = view.findViewById(R.id.text_view_unit);
            this.tvDescription = view.findViewById(R.id.tv_description);
            this.imagePlaceholder = view.findViewById(R.id.image_placeholder);
        }

        /*public void bind(Object obj) {
            binding.setVariable(BR.eventItem, obj);
            binding.executePendingBindings();
        }*/
    }
}