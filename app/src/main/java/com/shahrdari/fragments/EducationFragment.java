package com.shahrdari.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.shahrdari.R;

public class EducationFragment extends Fragment implements View.OnClickListener {

    protected View rootView;
    private FragmentStack fragmentStack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_education, container, false);
            init();

        }
        return rootView;
    }

    private void init() {
        rootView.findViewById(R.id.imageView1).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView2).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView3).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView4).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView6).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.imageView7).setOnClickListener(this::onClick);
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        fragmentStack = new FragmentStack(getActivity(), getFragmentManager(), R.id.fragment_container);
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
    public void onClick(View view) {
        fragmentStack.replace(EducationListFragment.newInstance(view.getTag().toString(), view.getContentDescription().toString()));
    }
}
