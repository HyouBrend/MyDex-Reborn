package com.myor.mydex.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.myor.mydex.R;
import com.myor.mydex.entity.MonitoringD;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ViewDoDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MonitoringD> itemList;

    public ViewDoDetailAdapter(List<MonitoringD> list, Context context) {
        new ArrayList();
        this.itemList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_detail_do, viewGroup, false);
        this.context = viewGroup.getContext();
        return new ViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition == 0) {
            viewHolder.textViewNo.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.textViewPcode.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.textViewPcodename.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.textViewQty.setTypeface(Typeface.defaultFromStyle(1));
            return;
        }
        setContentBg(viewHolder.textViewNo);
        setContentBg(viewHolder.textViewPcode);
        setContentBg(viewHolder.textViewPcodename);
        setContentBg(viewHolder.textViewQty);
        MonitoringD monitoringD = this.itemList.get(adapterPosition - 1);
        viewHolder.textViewNo.setText(String.valueOf(adapterPosition));
        viewHolder.textViewPcode.setText(monitoringD.getPcode());
        viewHolder.textViewPcodename.setText(monitoringD.getPcodeName());
        viewHolder.textViewQty.setText(monitoringD.getXqty());
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
        TextView textViewNo;
        TextView textViewPcode;
        TextView textViewPcodename;
        TextView textViewQty;

        public ViewHolder(View view) {
            super(view);
            this.textViewNo = (TextView) view.findViewById(R.id.textViewNo);
            this.textViewPcode = (TextView) view.findViewById(R.id.textViewPcode);
            this.textViewPcodename = (TextView) view.findViewById(R.id.textViewPcodename);
            this.textViewQty = (TextView) view.findViewById(R.id.textViewQty);
        }
    }

    private void setContentBg(View view) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg);
    }
}
