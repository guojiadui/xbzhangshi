package com.xbzhangshi.mvp.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.xbzhangshi.R;
import com.xbzhangshi.mvp.base.BaseActivity;
import com.xbzhangshi.mvp.usercenter.BaseView.IExchangeBaseView;
import com.xbzhangshi.mvp.usercenter.presenter.ExchangePresenter;
import com.xbzhangshi.view.CustomToolbar;
import com.xbzhangshi.view.dialog.ExchangeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 积分兑换
 */
public class ExchangeActivity extends BaseActivity implements IExchangeBaseView {
    public static void start(Context context) {
        Intent intent = new Intent(context, ExchangeActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.customtoolbar)
    CustomToolbar customtoolbar;
    @BindView(R.id.acount)
    TextView acount;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.integral)
    TextView integral;
    @BindView(R.id.exchange_tip)
    TextView exchangeTip;
    @BindView(R.id.value)
    EditText value;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.tip_layout)
    FrameLayout tipLayout;
    @BindView(R.id.spinner_car)
    Spinner spinner_car;
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    ExchangePresenter exchangePresenter;
    ExchangeDialog exchangeDialog;

    @Override
    protected int getlayout() {
        return R.layout.exchange_activity_layout;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        exchangePresenter = ExchangePresenter.newInstance(this);
        ltMainTitle.setText("积分兑换");
        ltMainTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exchangePresenter != null) {
                    exchangePresenter.commit(ExchangeActivity.this, value.getText().toString());
                }
            }
        });
        spinner_car.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (exchangePresenter != null) {
                    exchangePresenter.select(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        myadapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exchangePresenter != null) {
            exchangePresenter.onDestory();
        }
    }

    @Override
    protected void initdata() {
        exchangePresenter.init(this);
    }

    List<String> cars = new ArrayList<>();

    public void myadapter() {
        cars.add("现金兑换积分");
        cars.add("积分兑换现金");
        cars.add("-请选择类型-");
        Myadapter myadapter = new Myadapter(this, R.layout.support_simple_spinner_dropdown_item, cars);
        spinner_car.setAdapter(myadapter);
        //默认选中最后一项
        spinner_car.setSelection(cars.size() - 1, true);
    }

    @Override
    public void updateBalance(String msg) {
        balance.setText(msg);
    }

    @Override
    public void upDateAcount(String msg) {
        acount.setText(msg);
    }

    @Override
    public void upDateIntegral(String msg) {
        integral.setText(msg);
    }

    @Override
    public void upTip(String msg) {
        tipLayout.setVisibility(View.VISIBLE);
        exchangeTip.setText(msg);
    }

    @Override
    public void success() {
        if (exchangeDialog == null) {
             exchangeDialog = new ExchangeDialog(this);
        }
        exchangeDialog.show();
        value.setText("");
    }


    class Myadapter<T> extends ArrayAdapter {
        List<T> list;

        public Myadapter(@NonNull Context context, int resource, @NonNull List<T> objects) {
            super(context, resource, objects);
            this.list = objects;
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
            return i > 0 ? i - 1 : i;
        }
    }
}
