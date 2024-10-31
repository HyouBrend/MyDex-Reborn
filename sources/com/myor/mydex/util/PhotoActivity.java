package com.myor.mydex.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.myor.mydex.database.DatabaseHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class PhotoActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Context context;
    File mPhotoFile;
    private String imageFilePath = "";
    private final double DEFAULT_RESIZE = 1024.0d;

    public PhotoActivity(Context context) {
        this.context = context;
    }

    public File createImageFile(String str) throws IOException {
        File file = new File(new File(DatabaseHelper.PHOTO_PATH_SDCARD), str + ".jpg");
        this.imageFilePath = file.getAbsolutePath();
        file.getName();
        return file;
    }

    public void setPic(String str, String str2, String str3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int min = Math.min(options.outWidth / 640, options.outHeight / 480);
        options.inJustDecodeBounds = false;
        options.inSampleSize = min;
        options.inPurgeable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, null);
        saveBitmap(writeTextOnDrawable(decodeFile, str2, str3).getBitmap(), str);
        try {
            setPicNew(str, str2, str3);
        } catch (Exception e) {
            e.getMessage();
        }
        decodeFile.recycle();
    }

    public void setPicNonExifNewWatermark(String str, String str2, String str3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int min = Math.min(options.outWidth / 640, options.outHeight / 480);
        options.inJustDecodeBounds = false;
        options.inSampleSize = min;
        options.inPurgeable = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = writeTextOnDrawableNonExif(BitmapFactory.decodeFile(str, options), str2, str3).getBitmap();
        ImageCompressor imageCompressor = new ImageCompressor();
        try {
            new FileInputStream(new File(str));
            Log.i("Compress", "Original Size " + (str2.getBytes().length / 1024));
            imageCompressor.compressAndScaleImage(bitmap, 640, 480, 150, str).recycle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBitmap(Bitmap bitmap, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Log.i("Compress", "Size saveBitmap " + (bitmap.getByteCount() / 1024));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void setPicNew(String str, String str2, String str3) throws IOException {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        if (height >= width) {
            if (height >= 1024) {
                width = (int) ((1024.0d / height) * width);
                height = 1024;
            }
            scaleInsertExif(decodeFile, width, height, str);
            return;
        }
        if (width >= 1024) {
            height = (int) ((1024.0d / width) * height);
            width = 1024;
        }
        scaleInsertExif(decodeFile, width, height, str);
    }

    private void scaleInsertExif(Bitmap bitmap, int i, int i2, String str) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            ExifInterface exifInterface = new ExifInterface(Uri.parse("file://" + str).getPath());
            int attributeInt = exifInterface.getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")) {
                TransformationUtils.rotateImage(createScaledBitmap, 90);
            } else if (exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")) {
                TransformationUtils.rotateImage(createScaledBitmap, 270);
            } else if (exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION).equalsIgnoreCase(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_3D)) {
                TransformationUtils.rotateImage(createScaledBitmap, 180);
            } else if (exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")) {
                TransformationUtils.rotateImage(createScaledBitmap, 90);
            }
            File file = new File(str);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, String.valueOf(attributeInt));
            exifInterface.saveAttributes();
        } catch (Exception unused) {
        }
    }

    private BitmapDrawable writeTextOnDrawable(Bitmap bitmap, String str, String str2) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Typeface create = Typeface.create("Helvetica", 1);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setTypeface(create);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50.0f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        paint2.setTypeface(create);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(50.0f);
        paint.getTextBounds(str2, 0, str2.length(), new Rect());
        Canvas canvas = new Canvas(copy);
        int width = (canvas.getWidth() / 2) - 2;
        float f = width;
        canvas.drawText(str, f, canvas.getHeight() - 200, paint);
        canvas.drawText(str2, f, r3 + 100, paint2);
        return new BitmapDrawable(this.context.getResources(), copy);
    }

    private BitmapDrawable writeTextOnDrawableNonExif(Bitmap bitmap, String str, String str2) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Typeface create = Typeface.create("Helvetica", 1);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setTypeface(create);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20.0f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        paint2.setTypeface(create);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(17.0f);
        paint.getTextBounds(str2, 0, str2.length(), new Rect());
        Canvas canvas = new Canvas(copy);
        int width = (canvas.getWidth() / 2) - 2;
        float f = width;
        canvas.drawText(str, f, canvas.getHeight() - 48, paint);
        canvas.drawText(str2, f, r4 + 24, paint2);
        return new BitmapDrawable(this.context.getResources(), copy);
    }

    private BitmapDrawable writeTextOnDrawableRatingToko(Bitmap bitmap, String str, String str2) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Typeface create = Typeface.create("Helvetica", 1);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setTypeface(create);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(90.0f);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        paint2.setTypeface(create);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(60.0f);
        paint.getTextBounds(str2, 0, str2.length(), new Rect());
        Canvas canvas = new Canvas(copy);
        int width = (canvas.getWidth() / 2) - 2;
        float f = width;
        canvas.drawText(str, f, canvas.getHeight() - 180, paint);
        canvas.drawText(str2, f, r4 + 65, paint2);
        return new BitmapDrawable(this.context.getResources(), copy);
    }

    public void setPicNewWatermark(String str, String str2, String str3) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str, null);
        saveBitmapNew(writeTextOnDrawable(decodeFile, str2, str3).getBitmap(), str, str2, str3);
        decodeFile.recycle();
    }

    public void setPicNewWatermarkRating(String str, String str2, String str3) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str, null);
        saveBitmapNew(writeTextOnDrawableRatingToko(decodeFile, str2, str3).getBitmap(), str, str2, str3);
        decodeFile.recycle();
    }

    public void saveBitmapNew(Bitmap bitmap, String str, String str2, String str3) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            try {
                setPicNew(str, str2, str3);
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public boolean saveImage(Bitmap bitmap, File file, String str) {
        File file2 = new File(file, str);
        file2.getAbsolutePath();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
