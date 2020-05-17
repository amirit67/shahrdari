package com.shahrdari.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.shahrdari.BuildConfig;
import com.shahrdari.Constants;
import com.shahrdari.MyApp;
import com.shahrdari.R;
import com.shahrdari.adapters.BannerAdapter;
import com.shahrdari.adapters.MarqueeAdapter;
import com.shahrdari.models.marqueeObject;
import com.shahrdari.utils.CircleImageView;
import com.shahrdari.utils.RecyclerSnapHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class MainFragment extends Fragment {

    protected View rootView;
    RecyclerView recyclerMarquee;
    CircleImageView circleImageView;
    TextView txtName;
    RecyclerView recyclerView;
    ScrollingPagerIndicator indicator;
    private FragmentStack fragmentStack;
    //private UserObject myUser;
    private String imgProfileUrl = "";


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
        }
        return rootView;
    }

    private void initView() {
        /*txtName.setText(MyApp.getInstance().getPreferences().getString(Constants.Name, "") + "، خوش آمدی");
        fragmentStack = new FragmentStack(getActivity(), getFragmentManager(), R.id.fragment_container);
        setupRecyclerView();*/
    }

    private void setupRecyclerView() {

        List<String> list = new ArrayList<>();

        list.add("https://ak0.picdn.net/shutterstock/videos/32481220/thumb/1.jpg?ip=x480");
        //   list.add("https://s3-alpha-sig.figma.com/img/6c10/7450/f7b6c0c25bff41bd7bcaa94f496d1992?Expires=1569801600&Signature=SutKiS6JC1N-LE6nr1BXbtYlQ1vyF0E97gZyPubA-D00AAmOtSrGc-MbiEioDsTYUX1hSkMOj3Q-3E5AfyypjB-gI2pCGNZYo9CorQP44ecizfKA3Wph308YQ0DkIPXHI-BsvVUYYAEiCKR-Mc-6doxDQ-GXKgzOARpdP5XRwYxdKy4w7Zl5h~3D8d~vG2vo-vxIKbyJx2Eoi7rGUNs7DDU0clT9jZZ1hwcWufZGT4tsykfZJpEDxJZ5d86B2e9it0xRB~rYt~M~FO4YmxTKKX-XZXh48Szyt3MAd8WOzZjL41SfOiU8I1HnKeTRbx~yeLsdnllNUUQORYXls-DOng__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA");
        list.add("https://img.etimg.com/thumb/height-450,width-800,msid-70615273,imgsize-250586/67stock.jpg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI-8w1Rl4CyC2rBJxKcWacAxUwPLMJSPa_u94hyh1u4Gt-w8zh");
        list.add("https://www.usnews.com/dims4/USNEWS/77bf699/2147483647/thumbnail/640x420/quality/85/?url=http%3A%2F%2Fcom-usnews-beam-media.s3.amazonaws.com%2F6b%2Ff1%2Fcfba5ca940ed9a02d88a2352eb99%2F190115-stockmarketdata-stock.jpg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8t8PVXRnvJ9cEvRIrth12C9O1h1N-0q3fSRgcmk0hyu2GFGON");

        mBannerAdapter = new BannerAdapter(list, getActivity());
        recyclerView.setAdapter(mBannerAdapter);

        RecyclerSnapHelper snapHelper = new RecyclerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        indicator.attachToRecyclerView(recyclerView);
        indicator.setSelectedDotColor(getResources().getColor(R.color.white));
        indicator.setVisibleDotCount(3);


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

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                //fragmentStack.replace(new CreditFragment());
                break;
            case R.id.textView26:
                //fragmentStack.replace(ProfileFragment.newInstance(myUser));
                break;
            case R.id.imageView1:
                //fragmentStack.replace(new FragmentCategoriesFilter());
                break;
            case R.id.imageView2:
                //fragmentStack.replace(new FragmentQuestion());
                break;
            case R.id.imageView3:
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
}
