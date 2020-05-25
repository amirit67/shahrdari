package com.shahrdari.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shahrdari.R;
import com.shahrdari.adapters.CategoriesProductAdapter;
import com.shahrdari.utils.ItemDecorationAlbumColumns2;

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
        rv.addItemDecoration(new ItemDecorationAlbumColumns2(rv.getContext(), 1));
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
       /* feed.clear();
        try {
            cr = MyApp.database.rawQuery("SELECT * from CATEGORIES where PARENT_ID = '" + getArguments().getString("filter_item_pos") + "' ", null);
        } catch (Exception e) {
            cr = MyApp.database.rawQuery("SELECT * from CATEGORIES where PARENT_ID = 'null' ", null);
        }
        while (cr.moveToNext()) {
            CategoryItem item = new CategoryItem();
            item.setId(cr.getString(cr.getColumnIndex("CODE")));
            item.setName(cr.getString(cr.getColumnIndex("NAME")));
            item.setHasChild(cr.getString(cr.getColumnIndex("HASCHILD")));
            item.set_photourl(cr.getString(cr.getColumnIndex("IMAGEURL")));
            feed.add(item);
        }*/
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
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
