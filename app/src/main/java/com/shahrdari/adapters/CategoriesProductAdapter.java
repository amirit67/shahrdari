package com.shahrdari.adapters;

import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.shahrdari.R;
import com.shahrdari.fragments.FragmentStack;
import com.shahrdari.fragments.ProductsFragment;

import static com.shahrdari.utils.AnimationUtils.m4697e;

public class CategoriesProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    TypedArray imgs;
    //private List<CategoryItem> feed;
    private Fragment ctx;

    public CategoriesProductAdapter(Fragment fragmentActivity) {
        ctx = fragmentActivity;
        imgs = ctx.getResources().obtainTypedArray(R.array.products_imgs);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_product, null);
        return new CategoryHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        if (holder instanceof CategoryHolder) {
            final CategoryHolder Holder = (CategoryHolder) holder;
            m4697e(Holder.itemView, 100);
            Holder.setIsRecyclable(false);
            Holder.txtTitlle.setText(ctx.getResources().getStringArray(R.array.categoryTitle)[i]);
            Holder.img.setImageResource(imgs.getResourceId(i, -1));
            holder.itemView.setOnClickListener(v -> {
                FragmentStack fragmentStack = new FragmentStack(ctx.getActivity(), ctx.getFragmentManager(), R.id.fragment_container);
                fragmentStack.replace(ProductsFragment.newInstance("-" + (i + 1) + "-"));
            });

        }
    }

    @Override
    public int getItemCount() {
        try {
            return 21;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        public TextView txtTitlle;
        public ImageView img;

        public CategoryHolder(View view) {
            super(view);
            this.txtTitlle = view.findViewById(R.id.title);
            this.img = view.findViewById(R.id.img);
        }

    }
}