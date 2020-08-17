package com.shahrdari.fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.adapters.ProductsAdapter;
import com.shahrdari.di.HSH;
import com.shahrdari.interactor.ProductsView;
import com.shahrdari.models.ProductItem;
import com.shahrdari.remote.viewModel.ProductsFragmentVM;
import com.shahrdari.utils.ItemDecorationAlbumColumns;

import java.util.List;


public class ProductsFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    String code = "1";
    ProductsFragmentVM productsFragmentVM = new ProductsFragmentVM();
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar pb;
    private ProductsAdapter adapter;
    private Activity ac;
    private LinearLayoutManager layoutManager;
    private View rootView = null;

    public static ProductsFragment newInstance(String code) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, code);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_products, container, false);

            DeclareElements();
            ac = getActivity();
            getEvents();

            swipeContainer.setOnRefreshListener(() -> {
                adapter.ClearFeed();
                getEvents();
            });
        }
        return rootView;
    }


    private void getEvents() {

        ProductsView productsView = new ProductsView() {
            @Override
            public void onGetProducts(List<ProductItem> productItems) {
                try {
                    if (productItems.size() == 0)
                        Toast.makeText(getActivity(), "محصولی یافت نشد", Toast.LENGTH_SHORT).show();
                    swipeContainer.setRefreshing(false);
                    pb.setVisibility(View.GONE);
                    adapter.addItems(productItems);

                    /*if (layoutManager.getItemCount() > 19)
                        layoutManager.scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);*/
                } catch (Exception e) {
                }
            }

            @Override
            public void showMessage(String msg) {
                Log.e("onError", msg);
                HSH.showtoast(ac, "خطا در ارتباط با سرور");
                swipeContainer.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void showMessage(int resource) {
                HSH.showtoast(ac, "خطا در ارتباط با سرور");
                swipeContainer.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }
        };

        String s1 = "-1-2-6-15-16-17-18-19-20-21-";
        String s2 = "-3-";
        String s3 = "-8-9-10-";
        String s4 = "-4-5-7-11-12-13-14-";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (s1.contains(code))
                window.setStatusBarColor(Color.parseColor("#FFCF1161"));
            else if (s2.contains(code))
                window.setStatusBarColor(Color.parseColor("#FF62AC03"));
            else if (s3.contains(code))
                window.setStatusBarColor(Color.parseColor("#FF14CF3D"));
            else if (s4.contains(code))
                window.setStatusBarColor(Color.parseColor("#B700B7F1"));
            else
                window.setStatusBarColor(Color.parseColor("#FFCF1161"));
        }

        if (s1.contains(code)) {
            rootView.findViewById(R.id.csParent).setBackgroundResource(R.drawable.ic_intersect_1);
            productsFragmentVM.GetProducts1(productsView, code.replace("-", ""));
        } else if (s2.contains(code)) {
            rootView.findViewById(R.id.csParent).setBackgroundResource(R.drawable.ic_intersect_2);
            productsFragmentVM.GetProducts2(productsView, code.replace("-", ""));
        } else if (s3.contains(code)) {
            rootView.findViewById(R.id.csParent).setBackgroundResource(R.drawable.ic_intersect_3);
            productsFragmentVM.GetProducts3(productsView, code.replace("-", ""));
        } else if (s4.contains(code)) {
            rootView.findViewById(R.id.csParent).setBackgroundResource(R.drawable.ic_intersect_4);
            productsFragmentVM.GetProducts4(productsView, code.replace("-", ""));
        } else {
            rootView.findViewById(R.id.csParent).setBackgroundResource(R.drawable.ic_intersect_1);
            productsFragmentVM.SearchProducts(productsView, code.replace("-", ""));
        }
    }


    public void DeclareElements() {
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        pb = rootView.findViewById(R.id.pb);
        rv = rootView.findViewById(R.id.rv_products);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new ItemDecorationAlbumColumns(getActivity(), ItemDecorationAlbumColumns.VERTICAL_LIST));
        adapter = new ProductsAdapter(getActivity(), s -> {
            showImageBigSize(s);
        });
        rv.setAdapter(adapter);

        try {
            ((TextView) rootView.findViewById(R.id.toolbar_title)).setText(getResources().getStringArray(R.array.categoryTitle)[Integer.parseInt(code.replace("-", "")) - 1]);
        } catch (Exception e) {
            ((TextView) rootView.findViewById(R.id.toolbar_title)).setText("جستجوی " + code);
        }
    }

    @Override
    public void onClick(View v) {
       /* if (v.getId() == R.id.img_contact) {
            if (contactsFragment == null)
                contactsFragment = new *//*NewAddressActivity*//*ContactsFragment();
            openFragment(getActivity(), contactsFragment);
        }*/
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {

            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                if (rootView.findViewById(R.id.cs_img).getVisibility() == View.VISIBLE)
                    rootView.findViewById(R.id.cs_img).setVisibility(View.GONE);
                else
                    getFragmentManager().popBackStack();
                return true;
            }

            return false;
        });
    }

    private void showImageBigSize(String imgName) {
        rootView.findViewById(R.id.cs_img).setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.cs_img).setBackgroundColor(Color.WHITE);

        Glide.with(getActivity())
                .load(BuildConfig.BASEURL + imgName)
                .into((ImageView) rootView.findViewById(R.id.img_attach_bigSize));
    }
}
