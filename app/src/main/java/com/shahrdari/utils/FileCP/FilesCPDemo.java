package com.shahrdari.utils.FileCP;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.shahrdari.utils.Utils.getFilePath;

public class FilesCPDemo extends Activity {
    private static final String AUTHORITY = "com.shahrdari";

    static private void copy(InputStream in, File dst) throws IOException {
        FileOutputStream out = new FileOutputStream(dst);
        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        String s = getFilePath(this, "10207-581-4.pdf").replace("file://", "//").replace("user/0", "data");
        File f = new File(s);

        if (!f.exists()) {
            AssetManager assets = getAssets();

            try {
                copy(assets.open("10207-581-4.pdf"), f);
            } catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
        }

        Intent i =
                new Intent(Intent.ACTION_VIEW,
                        FileProvider.getUriForFile(this, AUTHORITY, f));

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(i);
        finish();
    }
}