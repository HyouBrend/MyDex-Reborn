package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes.dex */
public class Volley {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context, BaseHttpStack baseHttpStack) {
        BasicNetwork basicNetwork;
        BasicNetwork basicNetwork2;
        String str;
        if (baseHttpStack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                basicNetwork2 = new BasicNetwork((BaseHttpStack) new HurlStack());
                return newRequestQueue(context, basicNetwork2);
            }
            try {
                String packageName = context.getPackageName();
                str = packageName + InternalZipConstants.ZIP_FILE_SEPARATOR + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                str = "volley/0";
            }
            basicNetwork = new BasicNetwork(new HttpClientStack(AndroidHttpClient.newInstance(str)));
        } else {
            basicNetwork = new BasicNetwork(baseHttpStack);
        }
        basicNetwork2 = basicNetwork;
        return newRequestQueue(context, basicNetwork2);
    }

    @Deprecated
    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        if (httpStack == null) {
            return newRequestQueue(context, (BaseHttpStack) null);
        }
        return newRequestQueue(context, new BasicNetwork(httpStack));
    }

    private static RequestQueue newRequestQueue(Context context, Network network) {
        final Context applicationContext = context.getApplicationContext();
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(new DiskBasedCache.FileSupplier() { // from class: com.android.volley.toolbox.Volley.1
            private File cacheDir = null;

            @Override // com.android.volley.toolbox.DiskBasedCache.FileSupplier
            public File get() {
                if (this.cacheDir == null) {
                    this.cacheDir = new File(applicationContext.getCacheDir(), Volley.DEFAULT_CACHE_DIR);
                }
                return this.cacheDir;
            }
        }), network);
        requestQueue.start();
        return requestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, (BaseHttpStack) null);
    }
}
