package com.shahrdari.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.shahrdari.R;
import com.shahrdari.adapters.BannerAdapter;
import com.shahrdari.adapters.MarqueeAdapter;
import com.shahrdari.interactor.MainView;
import com.shahrdari.models.BannerModel;
import com.shahrdari.models.marqueeObject;
import com.shahrdari.remote.viewModel.MainFragmentVM;
import com.shahrdari.utils.CircleImageView;
import com.shahrdari.utils.RecyclerSnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class MainFragment extends Fragment implements MainView, View.OnClickListener {

    protected View rootView;
    RecyclerView recyclerMarquee;
    CircleImageView circleImageView;
    TextView txtName;
    RecyclerView recyclerView;
    ScrollingPagerIndicator indicator;
    MainFragmentVM mainFragmentVM = new MainFragmentVM();
    private FragmentStack fragmentStack;
    @Nullable
    private BannerAdapter mBannerAdapter;

    @Nullable
    private MarqueeAdapter marqueeAdapter;

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
        rootView.findViewById(R.id.imageView).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView5).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView3).setOnClickListener(this::onClick);
        /*txtName.setText(MyApp.getInstance().getPreferences().getString(Constants.Name, "") + "، خوش آمدی");

        setupRecyclerView();*/
    }

    private void setupRecyclerView() {
        //Marquee

        boolean flag;
        List<marqueeObject> myList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) flag = false;
            else flag = true;
            myList.add(new marqueeObject("انس طلا " + i, "۱۵۰۲.۷۴", flag));
        }


        marqueeAdapter = new MarqueeAdapter(getActivity(), myList, position -> {
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getActivity()) {
                    private static final float SPEED = 1000000f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };

        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        recyclerMarquee.setLayoutManager(layoutManager);
        recyclerMarquee.setAdapter(marqueeAdapter);
        autoScroll();


        recyclerMarquee.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = layoutManager.findFirstVisibleItemPosition();
                if (firstItemVisible != 0 && firstItemVisible % myList.size() == 0) {
                    Objects.requireNonNull(recyclerView.getLayoutManager()).scrollToPosition(0);
                }
            }
        });


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
            case R.id.textView26:
                //fragmentStack.replace(ProfileFragment.newInstance(myUser));
                break;
            case R.id.imageView:
                fragmentStack.replace(new FragmentCategoriesProduct());
                break;
            case R.id.imageView2:
                //fragmentStack.replace(new FragmentQuestion());
                break;
            case R.id.imageView3:
                fragmentStack.replace(new EducationFragment());
//                HSH.getInstance().onOpenPage(getActivity(), SignalMainActivity.class);
                Toast.makeText(getActivity(), "این قسمت در دست ساخت می باشد", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView4:
                //fragmentStack.replace(new FragmentMarket());
                Toast.makeText(getActivity(), "این قسمت در دست ساخت می باشد", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView6:
            case R.id.imageView7:
                Toast.makeText(getActivity(), "این قسمت در دست ساخت می باشد", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onGetBanner(List<BannerModel> bannerModel) {

        indicator = rootView.findViewById(R.id.indicator);
        mBannerAdapter = new BannerAdapter(bannerModel, getActivity());
//        recyclerView.setAdapter(mBannerAdapter);

        RecyclerSnapHelper snapHelper = new RecyclerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mBannerAdapter);

        indicator.attachToRecyclerView(recyclerView);
        indicator.setSelectedDotColor(getResources().getColor(R.color.white));
        indicator.setVisibleDotCount(3);

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showMessage(int resource) {

    }
}
