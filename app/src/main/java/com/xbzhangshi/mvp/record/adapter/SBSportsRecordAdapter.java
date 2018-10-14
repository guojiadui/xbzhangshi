package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.HGSportsRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 沙巴体育
 */
public class SBSportsRecordAdapter extends BaseQuickAdapter<HGSportsRecordBean.RowsBean, BaseViewHolder> {
    //沙巴体育： resStatus 3未中奖 1 等待开奖 4 撤单 5 派奖回滚成功 2 已中奖 6 回滚异常 7 开奖异常
    //gameTimeType: 2       1:滚球   2:今日  3:早盘
    //plate: "H"   H:亚洲盘  I:印尼盘 E:欧洲盘 M:马来西亚盘
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SBSportsRecordAdapter(@Nullable List<HGSportsRecordBean.RowsBean> data) {
        super(R.layout.sports_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HGSportsRecordBean.RowsBean item) {
        helper.setText(R.id.competition_name, item.getLeague());

        String s = "投注金额:" + "<font color=\"red\">" + item.getBettingMoney() + "</font>元";
        TextView t = helper.getView(R.id.sum);
        t.setText(Html.fromHtml(s));
        if(item.getDataType()!=6){//混合
            helper.getView(R.id.team_layout).setVisibility(View.VISIBLE);
            helper.setText(R.id.team_name1, item.getHomeTeam());
            helper.setText(R.id.team_name2, item.getGuestTeam());
        }else {
            helper.getView(R.id.team_layout).setVisibility(View.GONE);
        }
        StringBuilder str = new StringBuilder();
        if (item.getGameTimeType() == 1) {
            str.append("滚球-");
        } else if (item.getGameTimeType() == 2) {
            str.append("今日-");
        } else if (item.getGameTimeType() == 3) {
            str.append("早盘-");
        }

        if ("H".equals(item.getPlate())) {
            str.append("亚洲盘");
        } else if ("I".equals(item.getPlate())) {
            str.append("印尼盘");
        } else if ("E".equals(item.getPlate())) {
            str.append("欧洲盘");
        } else if ("M".equals(item.getPlate())) {
            str.append("马来西亚盘");
        }
        if(item.getDataType()!=6){//非混合
            str.append("-赔率（" + item.getOdds() + ")");
        }

        helper.setText(R.id.odds, str.toString());

        if (item.getSportType() == 1) {
            helper.setText(R.id.way, "足球（" + item.getTypeNames() + ")");
        } else if (item.getSportType() == 2) {
            helper.setText(R.id.way, "篮球（" + item.getTypeNames() + ")");
        }
        String dateString = formatter.format(item.getBettingDate());
        helper.setText(R.id.time, "时间:" + dateString);

        if (item.getBettingStatus() == 3 || item.getBettingStatus() == 4) {
            helper.setText(R.id.state, "注单取消");
            return;
        }
        if (item.getBalance() == 1) {
            helper.setText(R.id.state, "等待开奖");
            return;
        }
        if (item.getBalance() == 4) {
            helper.setText(R.id.state, "赛事腰斩");
            return;
        }
        if (item.getBalance() == 2||item.getBalance() == 5||item.getBalance() == 6) {
            if(item.getBettingResult()>0){
                helper.setText(R.id.state, "派彩:"+item.getBettingResult());
                return;
            }
            helper.setText(R.id.state, "输");
            return;
        }
    }
}
