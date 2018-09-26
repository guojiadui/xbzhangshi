package com.xbzhangshi.mvp.record;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.record.baseview.ILotteryBaseView;
import com.xbzhangshi.mvp.record.presenter.LotteryRecordPresenter;
import com.xbzhangshi.view.dialog.SelectListDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 彩票投注记录
 */
public class LotteryRecordActivity extends BaseActivity implements ILotteryBaseView {


    public static void start(Context context) {
        Intent intent = new Intent(context, LotteryRecordActivity.class);
        context.startActivity(intent);
    }


    PopupWindow selectPopupWindow;//查询选中
    View topwindow;
    LotteryRecordPresenter lotteryRecordPresenter;

    @Override
    protected int getlayout() {
        return R.layout.note_record_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        lotteryRecordPresenter = LotteryRecordPresenter.newInstance(this);

    }

    @Override
    protected void initdata() {
           lotteryRecordPresenter.initData(this);
    }

    /**
     * 弹出的顶部的
     */
    private void TopPopuWindow() {

        if (selectPopupWindow == null) {
            // PopupWindow浮动下拉框布局
            topwindow = (View) this.getLayoutInflater().inflate(R.layout.note_record_select_layout, null);
            TextView data1 = topwindow.findViewById(R.id.start_time);
            TextView data2 = topwindow.findViewById(R.id.end_time);
            TextView lotteryType = topwindow.findViewById(R.id.lottery_type);
            TextView lotteryState = topwindow.findViewById(R.id.lottery_state);
            data1.setText(Calendar.getInstance().get(Calendar.YEAR) + "/" +
                    Calendar.getInstance().get(Calendar.MONTH) + "/" +
                    Calendar.getInstance().get(Calendar.YEAR));
            data1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateDialog((TextView) v);
                }
            });
            lotteryType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSelectDialog((TextView) v);
                }
            });
            lotteryState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSelectDialog((TextView) v);
                }
            });
            data2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDateDialog((TextView) v);
                }
            });
            selectPopupWindow = new PopupWindow(topwindow, getMobileWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, true);
            selectPopupWindow.setOutsideTouchable(true);
            selectPopupWindow.setFocusable(false);
            selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
       // selectPopupWindow.showAsDropDown(toolbar, 0, dpToPx(this, 5));
    }

    /**
     * 显示日期选择
     */
    public void showDateDialog(TextView textView) {
        String data = textView.getText().toString();
        if (TextUtils.isEmpty(data)) {
            DatePickerDialog dp = new DatePickerDialog(LotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "/" + month + "/" + dayOfMonth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        } else {
            String[] strs = data.split("/");
            DatePickerDialog dp = new DatePickerDialog(LotteryRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    textView.setText(year + "/" + month + "/" + dayOfMonth);
                }
            }, Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
            dp.getDatePicker().setMaxDate((new Date()).getTime());
            dp.show();
        }


    }

    /**
     * 显示种类
     */
    public void showSelectDialog(TextView textView) {
        List<String> list = new ArrayList<>();
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("sssssssss");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("dddddddddddddd");
        list.add("ssssssss");
        list.add("dddddddd");
        list.add("dddddddd");
        String s = textView.getText().toString();
        if (!TextUtils.isEmpty(s) && !s.equals("全部")) {
            SelectListDialog selectListDialog = new SelectListDialog(this, s, list, new SelectListDialog.SelectListener() {
                @Override
                public void onSelectListener(String string) {
                    textView.setText(string);
                }
            });
            selectListDialog.show();
        } else {
            SelectListDialog selectListDialog = new SelectListDialog(this, null, list, new SelectListDialog.SelectListener() {
                @Override
                public void onSelectListener(String string) {
                    textView.setText(string);
                }
            });
            selectListDialog.show();
        }

    }
}
