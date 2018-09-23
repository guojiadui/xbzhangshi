package com.xbzhangshi.mvp.login.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;
import com.xbzhangshi.R;
import com.xbzhangshi.app.Url;
import com.xbzhangshi.http.HttpManager;
import com.xbzhangshi.mvp.login.bean.RegisterItemBean;

import java.util.List;

public class RegisterAdapter extends BaseQuickAdapter<RegisterItemBean.ContentBean, BaseViewHolder> {
    Context context;

    public RegisterAdapter(Context context, @Nullable List<RegisterItemBean.ContentBean> data) {
        super(R.layout.register_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RegisterItemBean.ContentBean item) {
        helper.setText(R.id.content_text, item.getName());
        if (helper.getAdapterPosition() == 0) {
            TextView textView = helper.getView(R.id.content);
            textView.setHint("用户名为5-11个英文字母或数字");
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else if (helper.getAdapterPosition() == 1) {
            TextView textView1 = helper.getView(R.id.content);
            textView1.setVisibility(View.GONE);
            TextView textView2 = helper.getView(R.id.content_pwd);
            textView2.setVisibility(View.VISIBLE);
            textView2.setHint("密码为6-16个英文字母或数字");
            textView2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else if (helper.getAdapterPosition() == 2) {
            TextView textView1 = helper.getView(R.id.content);
            textView1.setVisibility(View.GONE);
            TextView textView2 = helper.getView(R.id.content_pwd);
            textView2.setVisibility(View.VISIBLE);
            textView2.setHint("请再次输入密码");
            textView2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else if (helper.getAdapterPosition() == getData().size() - 1) {
            TextView textView = helper.getView(R.id.content);
            textView.setHint("请输入验证码");
            ImageView code = helper.getView(R.id.img_code);
            code.setVisibility(View.VISIBLE);
            code.setBackgroundResource(R.drawable.bg_rectangle_black);
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HttpManager.getBitmap(context, Url.BASE_URL + Url.regVerifycode, null, new BitmapCallback() {
                        @Override
                        public void onSuccess(Response<Bitmap> response) {
                            ((ImageView) v).setImageBitmap(response.body());
                        }
                    });
                }
            });
            HttpManager.getBitmap(context, Url.BASE_URL + Url.regVerifycode, null, new BitmapCallback() {
                @Override
                public void onSuccess(Response<Bitmap> response) {
                    code.setImageBitmap(response.body());
                }
            });

        } else {
            TextView textView = helper.getView(R.id.content);
            textView.setHint("请输入" + item.getName());
            textView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
/* */