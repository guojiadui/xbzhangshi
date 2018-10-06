package com.xbzhangshi.mvp.webview.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.adapter.LotteryDetailsAdapter;

import java.util.List;

public class DateAdpater extends RecyclerView.Adapter<DateAdpater.DateHoder> {

    private int Year, Month;
    private List<String> days;


    private List<String> signDays;


    public DateAdpater(Context context, int Year, int Month, List<String> days) {
        this.context = context;
        this.Year = Year;
        this.Month = Month;
        this.days = days;
    }

    Context context;


    @NonNull
    @Override
    public DateHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.date_adapter_item, viewGroup, false);
        DateAdpater.DateHoder dateHoder = new DateAdpater.DateHoder(view);
        return dateHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull DateHoder dateHoder, int i) {
        if (i < days.size()) {
            if ("0".equals(days.get(i))) {
                dateHoder.date.setText("");
                dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_white);
            } else {
                dateHoder.date.setText(days.get(i));
                //签到背景
                if (signDays != null && signDays.size() > 0) {
                    if (days.get(i).length() <= 1) {
                        String s = "0" + days.get(i);
                        if (signDays.contains(s)) {
                            dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_brown);
                        } else {
                            dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_white);
                        }
                    } else {
                        String s = days.get(i);
                        if (signDays.contains(s)) {
                            dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_brown);
                        } else {
                            dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_white);
                        }
                    }
                } else {
                    dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_white);
                }

            }
        } else {
            dateHoder.date.setText("");
            dateHoder.item.setBackgroundResource(R.drawable.bg_rectangle_white);
        }

    }

    @Override
    public int getItemCount() {
        return 42;
    }

    public static class DateHoder extends RecyclerView.ViewHolder {
        FrameLayout item;
        TextView date;

        public DateHoder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            date = itemView.findViewById(R.id.date);
        }
    }

    public List<String> getSignDays() {

        return signDays;
    }

    public void setSignDays(List<String> signDays) {

        this.signDays = signDays;
    }
}
