package com.myor.mydex.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/* loaded from: classes2.dex */
public class ImageCompressor {
    /* JADX WARN: Code restructure failed: missing block: B:11:0x00ab, code lost:
    
        throw new java.lang.RuntimeException(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00a5, code lost:
    
        r4 = move-exception;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap compressAndScaleImage(android.graphics.Bitmap r4, int r5, int r6, int r7, java.lang.String r8) {
        /*
            r3 = this;
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            int r1 = r3.calculateInSampleSize(r0, r5, r6)
            r2 = 0
            r0.inJustDecodeBounds = r2
            r0.inSampleSize = r1
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            r0.inPreferredConfig = r1
            r3.scaleImage(r4, r5, r6)
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "Width "
            r6.append(r0)
            int r0 = r4.getWidth()
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "Compress"
            android.util.Log.i(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Height "
            r6.append(r1)
            int r1 = r4.getWidth()
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Size "
            r6.append(r1)
            int r1 = r4.getByteCount()
            int r1 = r1 / 1024
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r0, r6)
            r6 = 95
        L68:
            r5.reset()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r4.compress(r1, r6, r5)
            int r6 = r6 + (-5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Size in KB Loop "
            r1.append(r2)
            byte[] r2 = r5.toByteArray()
            int r2 = r2.length
            int r2 = r2 / 1024
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            byte[] r1 = r5.toByteArray()
            int r1 = r1.length
            int r1 = r1 / 1024
            if (r1 > r7) goto L68
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> La5
            r6.<init>(r8)     // Catch: java.lang.Exception -> La5
            r5.writeTo(r6)     // Catch: java.lang.Exception -> La5
            r6.flush()     // Catch: java.lang.Exception -> La5
            r6.close()     // Catch: java.lang.Exception -> La5
            return r4
        La5:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myor.mydex.util.ImageCompressor.compressAndScaleImage(android.graphics.Bitmap, int, int, int, java.lang.String):android.graphics.Bitmap");
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 > i2 || i4 > i) {
            return Math.min(Math.round(i3 / i2), Math.round(i4 / i));
        }
        return 1;
    }

    public Bitmap scaleImage(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
