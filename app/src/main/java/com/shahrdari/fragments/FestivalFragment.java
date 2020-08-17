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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.shahrdari.R;
import com.shahrdari.adapters.FestivalsAdapter;
import com.shahrdari.di.HSH;
import com.shahrdari.interactor.FestivalView;
import com.shahrdari.models.FestivalModel;
import com.shahrdari.remote.viewModel.FestivalFragmentVM;
import com.shahrdari.utils.ItemDecorationAlbumColumns2;

import java.util.List;


public class FestivalFragment extends Fragment implements View.OnClickListener {

    FestivalFragmentVM festivalFragmentVM = new FestivalFragmentVM();
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar pb;
    private FestivalsAdapter adapter;
    private Activity ac;
    private LinearLayoutManager layoutManager;
    private View rootView = null;

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

        FestivalView festivalView = new FestivalView() {
            @Override
            public void onGetFestivals(List<FestivalModel> festivalModels) {
                try {
                    swipeContainer.setRefreshing(false);
                    pb.setVisibility(View.GONE);
                    adapter.addItems(festivalModels);

                    /*if (layoutManager.getItemCount() > 19)
                        layoutManager.scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);*/
                } catch (Exception e) {
                }
            }

            @Override
            public void showMessage(String msg) {
                Log.e("onError", msg);
                HSH.showtoast(ac, "خطا در اتصال به اینترنت");
                swipeContainer.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void showMessage(int resource) {

            }
        };

        festivalFragmentVM.GetFestivals(festivalView);

    }


    public void DeclareElements() {
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText("جشنواره ها");
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        pb = rootView.findViewById(R.id.pb);
        rv = rootView.findViewById(R.id.rv_products);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new ItemDecorationAlbumColumns2(getActivity(), ItemDecorationAlbumColumns2.VERTICAL_LIST));
        adapter = new FestivalsAdapter(getActivity(), s -> {

        });
        rv.setAdapter(adapter);

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFCF1161"));
        }

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


}
