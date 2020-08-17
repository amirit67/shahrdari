package com.shahrdari;


import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import com.shahrdari.di.HSH;
import com.shahrdari.rest.ApiInterface;
import com.shahrdari.utils.PermissionHandler;

import org.json.JSONObject;

import java.io.File;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static android.content.Context.DOWNLOAD_SERVICE;

public class UpdateCheckerAsynkTask {

    Activity cn;
    @Inject
    Retrofit retrofit;
    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
            /* Manifest.permission.RECEIVE_SMS,*/
    };

    public UpdateCheckerAsynkTask(Activity cn) {
        this.cn = cn;
        MyApp.getmainComponent().Inject(this);
    }

    public void GetData() {

        Call<ResponseBody> call =
                retrofit.create(ApiInterface.class).UpdateApp();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful())
                    try {
                        JSONObject result = new JSONObject(response.body().string().trim());
                        String _version = cn.getPackageManager().getPackageInfo(cn.getPackageName(), 0).versionName;
                        String newVersion = result.getString("Version");
                        int a = Integer.parseInt(newVersion.replace(".", ""));
                        int b = Integer.parseInt(_version.replace(".", ""));
                        if (b < a && !newVersion.equals(""))
                            downloadUpdate(result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void downloadUpdate(JSONObject result) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(cn);
            builder.setMessage(cn.getString(R.string.dialog_message)).setTitle(cn.getString(R.string.dialog_title));

            //Setting message manually and performing action on button click
            builder.setMessage(cn.getString(R.string.dialog_message))
                    .setCancelable(false)
                    .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                if (result.getString("ApkUrl").trim().toLowerCase().contains("cafebazaar")) {
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("bazaar://details?id=" + cn.getPackageName()));
                                        intent.setPackage("com.farsitel.bazaar");
                                        cn.startActivity(intent);
                                    } catch (Exception e) {
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/app/" + cn.getPackageName() + "/?l=fa"));
                                            intent.setPackage("com.farsitel.bazaar");
                                            cn.startActivity(intent);
                                        } catch (Exception e1) {
                                        }
                                    }
                                } else {
                                    try {
                                        new PermissionHandler().checkPermission(cn, permissions, new PermissionHandler.OnPermissionResponse() {
                                            @Override
                                            public void onPermissionGranted() {
                                                try {
                                                    DownloadManager dm = (DownloadManager) cn.getSystemService(DOWNLOAD_SERVICE);
                                                    String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                                                    String fileName = "Shahrdari.apk";
                                                    destination += fileName;
                                                    final Uri uri = Uri.parse("file://" + destination);

                                                    File file = new File(destination);
                                                    if (file.exists())
                                                        file.delete();

                                                    DownloadManager.Request request = new DownloadManager.Request(
                                                            Uri.parse(result.getString("ApkUrl").trim()));
                                                    request.setDestinationUri(uri);
                                                    dm.enqueue(request);

                                                    final String finalDestination = destination;
                                                    final BroadcastReceiver onComplete = new BroadcastReceiver() {
                                                        public void onReceive(Context ctxt, Intent intent) {
                                                            try {
                                                                boolean isNonPlayAppAllowed = Settings.Secure.getInt(cn.getContentResolver(), Settings.Global.INSTALL_NON_MARKET_APPS) == 1;
                                                                if (isNonPlayAppAllowed) {
                                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                                        Uri contentUri = FileProvider.getUriForFile(ctxt, "com.shahrdari.provider", new File(finalDestination));
                                                                        Intent openFileIntent = new Intent(Intent.ACTION_VIEW);
                                                                        openFileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                                                        openFileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                        openFileIntent.setData(contentUri);
                                                                        cn.startActivity(openFileIntent);
                                                                        cn.unregisterReceiver(this);
                                                                        cn.finish();
                                                                    } else {
                                                                        Intent install = new Intent(Intent.ACTION_VIEW);
                                                                        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                        install.setDataAndType(uri,
                                                                                "application/vnd.android.package-archive");
                                                                        cn.startActivity(install);
                                                                        cn.unregisterReceiver(this);
                                                                        cn.finish();
                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                            }
                                                        }
                                                    };
                                                    cn.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                                    dialog.dismiss();
                                                } catch (Exception e) {
                                                    dialog.dismiss();
                                                }
                                                return;
                                            }

                                            @Override
                                            public void onPermissionDenied() {
                                                HSH.getInstance().showtoast(cn, "برای استفاده از امکانات مورد نظر، لازم است دسترسی های لازم را صادر نمایید.");
                                            }
                                        });
                                    } catch (Exception e) {
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    })
                    .setNegativeButton("فعلا نه", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("بروزرسانی");
            alert.show();

        } catch (Exception e) {
        }
    }
}


