package com.myor.mydex.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.myor.mydex.R;
import com.myor.mydex.entity.MonitoringD;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ViewDoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MonitoringD> itemList;
    private OnClickDetail onClickDetail;

    /* loaded from: classes2.dex */
    public interface OnClickDetail {
        void onClickDetail(String str);
    }

    public ViewDoAdapter(List<MonitoringD> list, Context context, OnClickDetail onClickDetail) {
        new ArrayList();
        this.itemList = list;
        this.context = context;
        this.onClickDetail = onClickDetail;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_do, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition == 0) {
            viewHolder.textViewNo.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.textViewDo.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.textViewDetail.setTypeface(Typeface.defaultFromStyle(1));
            return;
        }
        setContentBg(viewHolder.textViewNo);
        setContentBg(viewHolder.textViewDo);
        setContentBg(viewHolder.textViewDetail);
        final MonitoringD monitoringD = this.itemList.get(adapterPosition - 1);
        viewHolder.textViewDo.setText(monitoringD.getDoNo());
        viewHolder.textViewNo.setText(String.valueOf(adapterPosition));
        viewHolder.textViewDetail.setText(this.context.getString(R.string.detail));
        viewHolder.textViewDetail.setTextColor(SupportMenu.CATEGORY_MASK);
        viewHolder.textViewDetail.setPaintFlags(viewHolder.textViewDetail.getPaintFlags() | 8);
        viewHolder.textViewDetail.setOnClickListener(new View.OnClickListener() { // from class: com.myor.mydex.adapter.ViewDoAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewDoAdapter.this.onClickDetail.onClickDetail(monitoringD.getDoNo());
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MonitoringD> list = this.itemList;
        if (list != null) {
            return list.size() + 1;
        }
        return 0;
    }

    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDetail;
        TextView textViewDo;
        TextView textViewNo;

        public ViewHolder(View view) {
            super(view);
            this.textViewNo = (TextView) view.findViewById(R.id.textViewNo);
            this.textViewDo = (TextView) view.findViewById(R.id.textViewDo);
            this.textViewDetail = (TextView) view.findViewById(R.id.textViewDetail);
        }
    }

    private void setContentBg(View view) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }
}
