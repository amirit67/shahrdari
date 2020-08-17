package com.shahrdari.fragments;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shahrdari.R;
import com.shahrdari.adapters.CategoriesProductAdapter;
import com.shahrdari.utils.ItemDecorationAlbumColumns3;

import java.util.Objects;

public class FragmentCategoriesProduct extends CoreFragment implements View.OnClickListener {

    private View rootView = null;
    private CategoriesProductAdapter adapter;
    //private List<CategoryItem> feed = new ArrayList<>();
    private RecyclerView rv;
    //private Cursor cr;
    private ImageButton back;

    private void DeclareElements() {
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        rv = rootView.findViewById(R.id.listView);
        rv.addItemDecoration(new ItemDecorationAlbumColumns3(rv.getContext(), 1));
        StaggeredGridLayoutManager lmanager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(lmanager);
        adapter = new CategoriesProductAdapter(this);
        rv.setAdapter(adapter);

        back = rootView.findViewById(R.id.img_back);
        back.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_categories_product, container, false);
        DeclareElements();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FF62AC03"));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                Objects.requireNonNull(getFragmentManager()).popBackStack();
                break;
        }
    }
}
