package com.myor.mydex.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.MonitoringPhoto;
import java.io.File;
import java.util.List;

/* loaded from: classes2.dex */
public class ImageAdapterTakePhoto extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MonitoringPhoto> datas;
    public ReloadDataService reloadDataService;

    /* loaded from: classes2.dex */
    public interface ReloadDataService {
        void onReloadData();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public ImageAdapterTakePhoto(List<MonitoringPhoto> list, ReloadDataService reloadDataService) {
        this.datas = list;
        this.reloadDataService = reloadDataService;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        RoundedImageView imageView;
        ReloadDataService reloadDataService;

        public ViewHolder(View view, ReloadDataService reloadDataService) {
            super(view);
            this.reloadDataService = reloadDataService;
        }

        void setImage(String str) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(ImageAdapterTakePhoto.this.context);
            circularProgressDrawable.setStrokeWidth(5.0f);
            circularProgressDrawable.setCenterRadius(30.0f);
            circularProgressDrawable.start();
            Glide.with(ImageAdapterTakePhoto.this.context).load(new File(DatabaseHelper.PHOTO_PATH_SDCARD + str).getAbsoluteFile()).fitCenter().placeholder(circularProgressDrawable).into(this.imageView);
        }
    }
}
