package com.shahrdari.fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahrdari.BuildConfig;
import com.shahrdari.R;
import com.shahrdari.adapters.MarketsAdapter;
import com.shahrdari.di.HSH;
import com.shahrdari.interactor.MarketView;
import com.shahrdari.models.MarketModel;
import com.shahrdari.remote.viewModel.MarketsFragmentVM;
import com.shahrdari.utils.CircleImageView;
import com.shahrdari.utils.ItemDecorationAlbumColumns2;
import com.shahrdari.utils.properratingbar.ProperRatingBar;

import java.util.List;

import okhttp3.ResponseBody;


public class MarketsFragment extends Fragment implements View.OnClickListener {

    MarketsFragmentVM marketsFragmentVM = new MarketsFragmentVM();
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar pb;
    private MarketsAdapter adapter;
    private Activity ac;
    private LinearLayoutManager layoutManager;
    private MarketView interActor;
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

        interActor = new MarketView() {
            @Override
            public void onGetMarkets(List<MarketModel> marketModels) {
                try {
                    swipeContainer.setRefreshing(false);
                    pb.setVisibility(View.GONE);
                    adapter.addItems(marketModels);

                    /*if (layoutManager.getItemCount() > 19)
                        layoutManager.scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);*/
                } catch (Exception e) {
                }
            }

            @Override
            public void onSendComment(ResponseBody responseBody) {
                Toast.makeText(getActivity(), "نظر شما با موفقیت ثبت شد.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void showMessage(String msg) {
                Log.e("onError", msg);
                HSH.showtoast(ac, msg);
                swipeContainer.setRefreshing(false);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void showMessage(int resource) {
                HSH.showtoast(ac, getString(resource));
            }
        };

        marketsFragmentVM.GetMarkets(interActor);

    }


    public void DeclareElements() {
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        ((TextView) rootView.findViewById(R.id.toolbar_title)).setText("بازارها");
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        pb = rootView.findViewById(R.id.pb);
        rv = rootView.findViewById(R.id.rv_products);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new ItemDecorationAlbumColumns2(getActivity(), ItemDecorationAlbumColumns2.VERTICAL_LIST));
        adapter = new MarketsAdapter(getActivity(), s -> {

            View view = getLayoutInflater().inflate(R.layout.dialog_rate, null);
            BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialog);
            dialog.setContentView(view);

            ((TextView) dialog.findViewById(R.id.tvTitle)).setText("امیدواریم خرید خوبی از " + s.getName() + " تجربه کرده باشید.");
            ((TextView) dialog.findViewById(R.id.tvMarketName)).setText(s.getName());
            EditText etComment = dialog.findViewById(R.id.etComment);
            CircleImageView imgMarket = dialog.findViewById(R.id.card_img);
            ProperRatingBar rate = dialog.findViewById(R.id.ratingBar);
            Button btn_register = dialog.findViewById(R.id.txt_register);
            //ProgressBar pb = dialog.findViewById(R.id.pb);
            Glide.with(getActivity())
                    .load(BuildConfig.BASEURL + s.getPic())
                    .into(imgMarket);

            rate.setListener(ratingBar -> {
                if (!btn_register.isEnabled()) {
                    btn_register.setEnabled(true);
                    btn_register.setBackground(getResources().getDrawable(R.drawable.button_round_true));
                }
            });

            btn_register.setOnClickListener(v -> {
                //pb.setVisibility(View.VISIBLE);
                dialog.dismiss();
                String s1 = etComment.getText().toString().trim();
                marketsFragmentVM.SendComment(interActor, s.getName(), String.valueOf(rate.getRating()),
                        TextUtils.isEmpty(s1) ? "نظری ندارم" : s1);
            });
            dialog.show();
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
