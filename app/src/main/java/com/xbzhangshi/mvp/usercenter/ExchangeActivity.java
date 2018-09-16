package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 积分兑换
 */
public class ExchangeActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;


    @Override
    protected int getlayout() {
        return R.layout.exchange_activity_layout;
    }
    Spinner spinner_car;
    @Override
    protected void initView(Bundle savedInstanceState) {
        spinner_car = findViewById(R.id.spinner_car);
        myadapter();
    }

    @Override
    protected void initdata() {

    }

    List<String> cars = new ArrayList<>();
    public void myadapter() {
        cars.add("现金兑换积分");
        cars.add("积分兑换现金");
        cars.add("-请选择类型-");
        Myadapter myadapter = new Myadapter(this,R.layout.support_simple_spinner_dropdown_item,cars);
        spinner_car.setAdapter(myadapter);
        //默认选中最后一项
        spinner_car.setSelection(cars.size() - 1, true);
    }
    class Myadapter<T> extends ArrayAdapter{
        List<T> list;
        public Myadapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
            super(context, resource, objects);
            this.list=objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 修改Spinner选择后结果的字体颜色
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
            }

            //此处text1是Spinner默认的用来显示文字的TextView
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(list.get(position).toString());
            return convertView;
        }

        @Override
        public int getCount() {
            //返回数据的统计数量，大于0项则减去1项，从而不显示最后一项
            int i = super.getCount();
            return i>0?i-1:i;
        }
    }
}
