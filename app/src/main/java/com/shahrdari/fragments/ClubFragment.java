package com.shahrdari.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.shahrdari.R;
import com.shahrdari.di.HSH;
import com.shahrdari.interactor.ClubView;
import com.shahrdari.remote.viewModel.ClubFragmentVM;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubFragment extends Fragment {

    EditText etName;
    EditText etFamily;
    EditText etMobile;
    TextView btRegister, txtError;
    ImageView imgBack, imgName, imgUsername, imgEmail;
    ProgressBar progressBar;
    ClubFragmentVM clubFragmentVM = new ClubFragmentVM();
    View rootView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_club, container, false);
        init();

        btRegister.setOnClickListener(v -> {

            if (etName.getText().length() < 3) {
                txtError.setText("نام شما حداقل 3 حرف می باشد");
                //imgName.setBackgroundResource(R.drawable.ic_error);
            } else if (etFamily.getText().length() < 3) {
                txtError.setText("نام خانوادگی حداقل 3 حرف می باشد");
                //imgUsername.setBackgroundResource(R.drawable.ic_error);
            } else if (etMobile.getText().toString().length() != 11
                    || !etMobile.getText().toString().startsWith("09")) {
                txtError.setText("شماره موبایل معتبر وارد نمایید");
                //imgEmail.setBackgroundResource(R.drawable.ic_error);
            } else {
                btRegister.setVisibility(View.INVISIBLE);
                btRegister.setEnabled(false);
                String name = etName.getText().toString().trim();
                String family = etFamily.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                ClubView interActor = new ClubView() {
                    @Override
                    public void onRegisterClub(ResponseBody responseBody) {
                        HSH.showtoast(getActivity(), "ثبت نام شما با موفقیت انجام شد");
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void showMessage(String msg) {
                        progressBar.setVisibility(View.GONE);
                        HSH.showtoast(getActivity(), msg);
                        btRegister.setEnabled(true);
                        btRegister.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void showMessage(int resource) {
                        progressBar.setVisibility(View.GONE);
                        HSH.showtoast(getActivity(), getString(resource));
                        btRegister.setEnabled(true);
                        btRegister.setVisibility(View.VISIBLE);
                    }
                };
                clubFragmentVM.GetFestivals(interActor, name, family, mobile);
            }

        });

        return rootView;
    }

    private void init() {
        rootView.findViewById(R.id.img_back).setOnClickListener(v -> getFragmentManager().popBackStack());
        etName = rootView.findViewById(R.id.et_name);
        etFamily = rootView.findViewById(R.id.et_family);
        etMobile = rootView.findViewById(R.id.et_mobile);
        btRegister = rootView.findViewById(R.id.bt_register);
        txtError = rootView.findViewById(R.id.txt_error);
        imgBack = rootView.findViewById(R.id.img_back);
        imgName = rootView.findViewById(R.id.img_name);
        imgUsername = rootView.findViewById(R.id.img_username);
        imgEmail = rootView.findViewById(R.id.img_email);
        progressBar = rootView.findViewById(R.id.progressBar);
    }
}
