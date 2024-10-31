package com.myor.mydex.apiclient;

import android.content.Context;
import com.myor.mydex.preferences.SettingPreferences;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.util.InternalZipConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* loaded from: classes2.dex */
public class ApiClient {
    private static Retrofit toWsMobile;
    private static Retrofit toWsMobileImage;
    private static Retrofit toWsMobileSendData;
    private static Retrofit webServiceWebMix;
    private Context context;
    private SettingPreferences settingPreferences = new SettingPreferences();

    public ApiClient(Context context) {
        this.context = context;
    }

    public void setDefaultSetting() {
        SettingPreferences.setKeyIp(this.context, "logistix.mayora.co.id");
        SettingPreferences.setKeyIpImg(this.context, "logistix.mayora.co.id");
        SettingPreferences.setKeyPortIp(this.context, "443");
        SettingPreferences.setKeyPortipsenddatap(this.context, "443");
        SettingPreferences.setKeyPortipsendimg(this.context, "443");
        SettingPreferences.setKeyBaseContextIp(this.context, "mobile/mydex/api");
        SettingPreferences.setKeyBaseContextIpImg(this.context, "mobile/mydex/api");
        SettingPreferences.setKeyBaseContextIpImgFtp(this.context, "mydex-ftp/api");
    }

    public Retrofit getClientToWsMobile(Context context) {
        toWsMobile = null;
        String str = "https://" + SettingPreferences.getKeyIp(context) + ":" + SettingPreferences.getKeyPortIp(context) + InternalZipConstants.ZIP_FILE_SEPARATOR + SettingPreferences.getKeyBaseContextIp(context) + InternalZipConstants.ZIP_FILE_SEPARATOR;
        if (toWsMobile == null) {
            toWsMobile = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().connectTimeout(5L, TimeUnit.MINUTES).addInterceptor(new HeaderInterceptor()).connectTimeout(2L, TimeUnit.MINUTES).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(5L, TimeUnit.MINUTES).build()).build();
        }
        return toWsMobile;
    }

    public Retrofit getClientSendVisit(Context context) {
        toWsMobileSendData = null;
        String str = "https://" + SettingPreferences.getKeyIp(context) + ":" + SettingPreferences.getKeyPortipsenddata(context) + InternalZipConstants.ZIP_FILE_SEPARATOR + SettingPreferences.getKeyBaseContextIp(context) + InternalZipConstants.ZIP_FILE_SEPARATOR;
        if (toWsMobileSendData == null) {
            toWsMobileSendData = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().connectTimeout(5L, TimeUnit.MINUTES).addInterceptor(new HeaderInterceptor()).connectTimeout(5L, TimeUnit.MINUTES).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(5L, TimeUnit.MINUTES).build()).build();
        }
        return toWsMobileSendData;
    }

    public Retrofit getClientImage(Context context) {
        toWsMobileImage = null;
        String str = "https://" + SettingPreferences.getKeyIpImg(context) + ":" + SettingPreferences.getKeyPortipsendimg(context) + InternalZipConstants.ZIP_FILE_SEPARATOR + SettingPreferences.setKeyBaseContextIpImg(context) + InternalZipConstants.ZIP_FILE_SEPARATOR;
        if (toWsMobileImage == null) {
            toWsMobileImage = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().connectTimeout(5L, TimeUnit.MINUTES).addInterceptor(new HeaderInterceptor()).connectTimeout(5L, TimeUnit.MINUTES).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(5L, TimeUnit.MINUTES).build()).build();
        }
        return toWsMobileImage;
    }

    public Retrofit getClientWebMix(Context context) {
        webServiceWebMix = null;
        webServiceWebMix = new Retrofit.Builder().baseUrl("http://159.138.88.47:8082/web/mt/api/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().connectTimeout(5L, TimeUnit.MINUTES).addInterceptor(new HeaderInterceptor()).connectTimeout(5L, TimeUnit.MINUTES).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(5L, TimeUnit.MINUTES).build()).build();
        return webServiceWebMix;
    }

    public Retrofit getClientImageRating(Context context) {
        toWsMobileImage = null;
        String str = "http://" + SettingPreferences.getKeyIpImg(context) + ":" + SettingPreferences.getKeyPortipsendimg(context) + InternalZipConstants.ZIP_FILE_SEPARATOR + SettingPreferences.setKeyBaseContextIpImg(context) + InternalZipConstants.ZIP_FILE_SEPARATOR;
        if (toWsMobileImage == null) {
            toWsMobileImage = new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient().newBuilder().connectTimeout(45L, TimeUnit.SECONDS).addInterceptor(new HeaderInterceptor()).connectTimeout(45L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(10L, TimeUnit.MINUTES).build()).build();
        }
        return toWsMobileImage;
    }
}
