package com.shahrdari.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahrdari.R;
import com.shahrdari.adapters.BannerAdapter;
import com.shahrdari.di.HSH;
import com.shahrdari.interactor.MainView;
import com.shahrdari.models.BannerModel;
import com.shahrdari.models.WeatherModel;
import com.shahrdari.remote.viewModel.MainFragmentVM;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class MainFragment extends Fragment implements MainView, View.OnClickListener {

    protected View rootView;
    RecyclerView recyclerMarquee;
    SliderView recyclerView;
    MainFragmentVM mainFragmentVM = new MainFragmentVM();
    private FragmentStack fragmentStack;
    @Nullable
    private BannerAdapter mBannerAdapter;
    private ImageView btnSrch;
    private ImageView imgClear, imgSearch;
    private EditText editSrch;
    private LinearLayout searchBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            initView();
            mainFragmentVM.GetBanners(this);
        }
        return rootView;
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.recycler_banner);
        fragmentStack = new FragmentStack(getActivity(), getFragmentManager(), R.id.fragment_container);
        rootView.findViewById(R.id.imageView1).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView2).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView4).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView5).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView3).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView7).setOnClickListener(this::onClick);
        searchBar = rootView.findViewById(R.id.search_bar);
        btnSrch = rootView.findViewById(R.id.btn_srch);
        editSrch = rootView.findViewById(R.id.edit_srch);
        imgClear = rootView.findViewById(R.id.btn_clear);
        imgSearch = rootView.findViewById(R.id.imgSearch);
        btnSrch.setOnClickListener(v ->
        {
            closeKeyboard();
            searchBar.setVisibility(View.INVISIBLE);
            fragmentStack.replace(ProductsFragment.newInstance("-" + editSrch.getText().toString().trim() + "-"));
        });
        editSrch.setOnEditorActionListener((v, actionId, event) -> {
            closeKeyboard();
            searchBar.setVisibility(View.INVISIBLE);
            fragmentStack.replace(ProductsFragment.newInstance(editSrch.getText().toString().trim()));
            return true;
        });
        imgClear.setOnClickListener(v -> HSH.hide(getActivity(), searchBar));
        imgSearch.setOnClickListener(v -> HSH.display(getActivity(), searchBar));
        /*setupRecyclerView();*/
    }

    public void closeKeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
        }
    }

    public void autoScroll() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    recyclerMarquee.scrollBy(-1, 0);
                    handler.postDelayed(this, 0);
                } catch (Exception e) {
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView5:
                fragmentStack.replace(new RateFragment());
                break;
            case R.id.imageView1:
                fragmentStack.replace(new ClubFragment());
                break;
            case R.id.imageView:
                fragmentStack.replace(new FragmentCategoriesProduct());
                break;
            case R.id.imageView2:
                fragmentStack.replace(new FestivalFragment());
                break;
            case R.id.imageView3:
                fragmentStack.replace(new EducationFragment());
//                HSH.getInstance().onOpenPage(getActivity(), SignalMainActivity.class);
                break;
            case R.id.imageView4:
                fragmentStack.replace(new MarketsFragment());
                break;
            case R.id.imageView6:
            case R.id.imageView7:
                mainFragmentVM.GetWeather(this);
                break;
        }
    }

    @Override
    public void onGetBanner(List<BannerModel> bannerModel) {

        SliderView sliderView = rootView.findViewById(R.id.recycler_banner);

        mBannerAdapter = new BannerAdapter(getActivity());

        mBannerAdapter.renewItems(bannerModel);
        sliderView.setSliderAdapter(mBannerAdapter);

        //sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    @Override
    public void onGetWeather(List<WeatherModel> weatherModels) {
        View view = getLayoutInflater().inflate(R.layout.dialog_weather, null);
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialog);
        dialog.setContentView(view);

        WeatherModel model = weatherModels.get(0);
        String date = model.getDateY() + "/" + model.getDateM() + "/" + model.getDateR();
        ((TextView) dialog.findViewById(R.id.tvTitle)).setText("وضعیت هوا در تاریخ " + date);
        ((TextView) dialog.findViewById(R.id.txt_number)).setText(String.valueOf(model.getNumberWeather()));
        ((TextView) dialog.findViewById(R.id.tvMarketName)).setText(model.getTxtWeather().trim());
        int i = model.getNumberWeather();
        ((TextView) dialog.findViewById(R.id.tvMarketName)).setTextColor(i < 50 ? Color.parseColor("#00A659") :
                i < 100 ? Color.parseColor("#fed700") :
                        i < 150 ? Color.parseColor("#f29b12") :
                                i < 200 ? Color.parseColor("#fd0100") :
                                        i < 300 ? Color.parseColor("#FF673AB7") :
                                                Color.parseColor("#FFDA3400"));
        //EditText etComment = dialog.findViewById(R.id.etComment);
        //CircleImageView imgMarket = dialog.findViewById(R.id.card_img);
        //ProperRatingBar rate = dialog.findViewById(R.id.ratingBar);
        //ProgressBar pb = dialog.findViewById(R.id.pb);
        /*Glide.with(getActivity())
                .load(BuildConfig.BASEURL + s.getPic())
                .into(imgMarket);*/

        dialog.show();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showMessage(int resource) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FFCF1161"));
        }
    }
}
