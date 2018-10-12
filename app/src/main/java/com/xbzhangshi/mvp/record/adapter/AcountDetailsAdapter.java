package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.AcountDetailsRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class AcountDetailsAdapter extends BaseQuickAdapter<AcountDetailsRecordBean.ListBean, BaseViewHolder> {
    int type = 0;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AcountDetailsAdapter(@Nullable List<AcountDetailsRecordBean.ListBean> data, int type) {
        super(R.layout.acount_details_adapter_item, data);
        this.type = type;
    }

    /**
     * lockFlag=1 待处理
     * Status 1    3 其他 ：
     *
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, AcountDetailsRecordBean.ListBean item) {
        if (type == 1) {
            //充值记录
            String t = setWay(item.getType());
            helper.setText(R.id.lottery_type, "充值方式:" + t);
            String s = "充值金额:" + "<font color=\"red\">" + item.getMoney() + "</font>";
            TextView money = helper.getView(R.id.money);
            money.setText(Html.fromHtml(s));
            helper.setText(R.id.time, "充值时间:" + formatter.format(item.getCreateDatetime()));
        } else if (type == 2) {
            //取款记录
            String t = setWay(item.getType());
            helper.setText(R.id.lottery_type, "取款方式:" + t);
            String s = "取款金额:" + "<font color=\"red\">" + item.getDrawMoney() + "</font>";
            TextView money = helper.getView(R.id.money);
            money.setText(Html.fromHtml(s));
            helper.setText(R.id.time, "取款时间:" + formatter.format(item.getCreateDatetime()));
        }

        if (item.getLockFlag() == 1) {
            helper.setText(R.id.result, "待处理");
            helper.setTextColor(R.id.result, 0xff408cf2);
        } else {
            if (item.getStatus() == 1) {
                helper.setText(R.id.result, "处理中");
                helper.setTextColor(R.id.result, 0xff408cf2);
            } else if (item.getStatus() == 2) {
                helper.setText(R.id.result, "处理成功");
                helper.setTextColor(R.id.result, 0xff00aa51);
            } else if (item.getStatus() == 3) {
                helper.setText(R.id.result, "处理失败");
                helper.setTextColor(R.id.result, 0xffff2525);
            } else {
                helper.setText(R.id.result, "未知错误");
                helper.setTextColor(R.id.result, 0xffec2829);
            }
        }


    }


    public String setWay(int type) {
        switch (type) {
            case 1:
                return "人工加款";
            case 2:
                return "人工扣款";
            case 3:
                return "在线取款失败";
            case 4:
                return "在线取款";
            case 5:
                return "在线支付";
            case 6:
                return "快速入款";
            case 7:
                return "一般入款";
            case 8:
                return "体育投注";
            case 9:
                return "二级代理反水加钱";
            case 10:
                return "二级代理反水扣钱";
            case 11:
                return "二级代理反点加钱";
            case 12:
                return "二级代理反点扣钱";
            case 13:
                return "多级代理反点加钱";
            case 14:
                return "一多级代理反点扣钱";
            case 15:
                return "三方额度转入系统额度";
            case 16:
                return "系统额度转入三方额度";
            case 130:
                return "彩票投注";
        }
        return "";
    }


}
