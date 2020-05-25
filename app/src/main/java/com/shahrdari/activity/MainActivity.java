package com.shahrdari.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahrdari.MyApp;
import com.shahrdari.R;
import com.shahrdari.di.HSH;
import com.shahrdari.fragments.FragmentStack;
import com.shahrdari.fragments.MainFragment;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MainActivity extends BaseActivity implements
        View.OnClickListener {

    protected TextView txtName;
    protected TextView txtUsername;
    protected ImageView imgUserAvatar;
    @Inject
    Retrofit retrofit;
//    @BindView(R.id.nave_view)
//    NavigationView navigationView;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawer;

//    @BindView(R.id.navHeader)
//    ConstraintLayout navHeader;

//    @BindView(R.id.navHeader)
//    ConstraintLayout navHeader;

    BottomNavigationView bottomNavigationView3;

    BottomSheetDialog dialog;
    CardView bottomSheet;
    MainFragment mainFragment;
    private BottomSheetBehavior sheetBehavior;
    private int TIME_INTERVAL = 2000;
    private long mBackPressed = 0;
    private FragmentStack fragmentStack;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.getmainComponent().Inject(this);
        //getProfile();
        initView();
        BottomSheetNavigation();
        mainFragment = new MainFragment();
        fragmentStack = new FragmentStack(this, getSupportFragmentManager(), R.id.fragment_container);
        fragmentStack.replace(mainFragment);
        bottomNavigationView3.getMenu().findItem(R.id.action_home).setChecked(true);
        bottomNavigationView3.setOnNavigationItemSelectedListener(menuItem -> {
            //check if bottom sheet is open close it
           /* if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            } else */
            if (menuItem.getItemId() == R.id.action_home) {
                closeKeyboard();
                fragmentStack.replace(mainFragment);
                return true;
            } else if (menuItem.getItemId() == R.id.action_market) {
                closeKeyboard();
                this.menuItem = menuItem;
                //sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                HSH.showtoast(this, "این قسمت در دست ساخت می باشد");
                return true;
            } else if (menuItem.getItemId() == R.id.action_navigation) {
                closeKeyboard();
                dialog.show();
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            try {
                String tag = getSupportFragmentManager()
                        .getBackStackEntryAt(getSupportFragmentManager()
                                .getBackStackEntryCount() - 2).getName();

                getSupportFragmentManager().popBackStack();
            } catch (Exception e) {
                exit();
            }
        }

    }


    private void exit() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            System.exit(0);
            finish();
        } else {
            HSH.showtoast(MainActivity.this, "برای خروج بروی دکمه بازگشت دوباره کلیک کنید");
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.profile_cons) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            // fragmentStack.replace(ProfileFragment.newInstance(myUser));
        }
    }

    private void initView() {
        bottomNavigationView3 = findViewById(R.id.bottomNavigationView3);
        //profileCons.setOnClickListener(this);
        bottomSheet = findViewById(R.id.bottom_sheet);
    }


    public void BottomSheetNavigation() {
        View view = getLayoutInflater().inflate(R.layout.dialog_navigation, null);
        dialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialog);
        dialog.setContentView(view);

        dialog.findViewById(R.id.tv_recent_visit).setOnClickListener(v -> {
            /*MyPayeFragment fra = new MyPayeFragment();
            Bundle bnd = new Bundle();
            bnd.putString("FavoriteOrRecent", "History");
            fra.setArguments(bnd);
            fragmentStack.replace(fra);*/
        });

        sheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

                switch (i) {

                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        dialog.show();
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //setScrim(v);
                dialog.show();
            }
        });
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //ConstraintLayout c1 = view.findViewById(R.id.constraintLayout1);
    }



   /* @Override
    public void onGetParentCode(String parentCode) {
        FragmentFilterQuestion fra = (FragmentFilterQuestion) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fra.setiParentCode(this::onGetParentCode);
    }*/
}
