package com.shahrdari.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.shahrdari.R;

public class EducationFragment extends Fragment implements View.OnClickListener {

    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_education, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        /*txtName.setText(MyApp.getInstance().getPreferences().getString(Constants.Name, "") + "، خوش آمدی");
        fragmentStack = new FragmentStack(getActivity(), getFragmentManager(), R.id.fragment_container);
        setupRecyclerView();*/
    }

    @Override
    public void onClick(View view) {
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
