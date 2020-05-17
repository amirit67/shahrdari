package com.shahrdari.fragments;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.math.BigDecimal;

public class CoreFragment extends Fragment {

    //public SkeletonScreen skeletonScreen;

    public void hideSoftKeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    /*public void showDialog(String title, String content, String confirm) {
        SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText(confirm);
        dialog.show();
        dialog.setCancelable(false);
        dialog.showCancelButton(false);
    }*/

    public String Parse(String text) {
        try {
            if (text.contains(".")) {
                //text = String.valueOf(BigDecimal.valueOf(Double.valueOf(text)));
                return String.format("%,d", Long.parseLong(String.valueOf(new BigDecimal(text.substring(0, text.indexOf("."))).toBigIntegerExact())))
                        + "." + text.substring(text.indexOf(".") + 1, text.length());
            } else
                return String.format("%,d", Long.parseLong(String.valueOf(new BigDecimal(text).toBigIntegerExact())));
        } catch (Exception e) {
            Log.i("Eror", e.getMessage());
            return ";";
        }
    }

    /*public SkeletonScreen ShowSkeletonScreen(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, RecyclerView rv) {
        skeletonScreen = Skeleton.bind(rv)
                .adapter(adapter)
                .color(R.color.skelton)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .count(5)
                .load(R.layout.item_news_skelton)
                .show();
        return skeletonScreen;
    }*/

    public void setError(LinearLayout childlinearlayout, TextView txt_title, String text) {
        txt_title.setText("");
        childlinearlayout.setBackgroundColor(Color.argb(100, 255, 128, 128));
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, text.indexOf("\n"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_title.setText(spannable, TextView.BufferType.SPANNABLE);
    }
}
