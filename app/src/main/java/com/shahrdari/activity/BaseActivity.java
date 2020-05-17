package com.shahrdari.activity;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shahrdari.R;


public class BaseActivity extends AppCompatActivity {

    public Uri uri;
    public FragmentManager _frgManager;
    public Context mContext;
    public String[] permissions2 = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            /* Manifest.permission.RECEIVE_SMS,*/
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this._frgManager = getSupportFragmentManager();

       /* new PermissionHandler().checkPermission(this, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                return;
            }

            @Override
            public void onPermissionDenied() {
            }
        });*/
    }

    public void setError(LinearLayout childlinearlayout, TextView txt_title, String text) {
        txt_title.setText("");
        childlinearlayout.setBackgroundColor(Color.argb(100, 255, 128, 128));
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, text.indexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_title.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    public void addFragment_in_fragment(Fragment frg, int containerId, boolean addToBackStack) {
        FragmentTransaction ft = this._frgManager.beginTransaction().setCustomAnimations(R.anim.fade_in,
                R.anim.fade_out, R.anim.fade_in,
                R.anim.fade_out).add(containerId, frg);
        if (addToBackStack) {
            ft.addToBackStack(frg.getClass().getName());
        }
        ft.commit();
    }

    public void addFragment(Fragment frg, int containerId, boolean addToBackStack) {
        //FragmentTransaction ft = this._frgManager.beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out).add(containerId, frg);

        String fragmentTag = frg.getClass().getSimpleName();
        boolean fragmentPopped = _frgManager
                .popBackStackImmediate(fragmentTag, 0);

        FragmentTransaction ftx = _frgManager.beginTransaction();

        if (addToBackStack && (!fragmentPopped
                && _frgManager.findFragmentByTag(fragmentTag) == null)
                || fragmentTag.contains("Search"))
            ftx.addToBackStack(frg.getClass().getSimpleName());

        ftx.setCustomAnimations(R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right);
        ftx.replace(containerId, frg, fragmentTag);
        ftx.commit();


       /* if (addToBackStack) {
            ft.addToBackStack(frg.getClass().getName());
        }
        ft.commit();*/
    }

    public void closeKeyboard() {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
        }
    }
}
