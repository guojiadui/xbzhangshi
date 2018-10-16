package com.xbzhangshi.mvp.record.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xbzhangshi.R;
import com.xbzhangshi.mvp.record.bean.SBSportsRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 沙巴体育
 */
public class SBSportsRecordAdapter extends BaseQuickAdapter<SBSportsRecordBean.RowsBean, BaseViewHolder> {
    //沙巴体育： resStatus 3未中奖 1 等待开奖 4 撤单 5 派奖回滚成功 2 已中奖 6 回滚异常 7 开奖异常
    //gameTimeType: 2       1:滚球   2:今日  3:早盘
    //plate: "H"   H:亚洲盘  I:印尼盘 E:欧洲盘 M:马来西亚盘
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SBSportsRecordAdapter(@Nullable List<SBSportsRecordBean.RowsBean> data) {
        super(R.layout.sb_sports_record_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SBSportsRecordBean.RowsBean item) {
        helper.setText(R.id.competition_name, item.getLeague());




        String s = "投注金额:" + "<font color=\"red\">" + item.getBettingMoney() + "</font>元";
        TextView t = helper.getView(R.id.sum);
        t.setText(Html.fromHtml(s));
        if (TextUtils.isEmpty(item.getParlayData())) {//混合
            helper.getView(R.id.way).setVisibility(View.VISIBLE);
            helper.getView(R.id.team_layout).setVisibility(View.VISIBLE);
            helper.setText(R.id.team_name1, item.getHomeTeam());
            helper.setText(R.id.team_name2, item.getAwayTeam());
            helper.setText(R.id.way, item.getGameName());
            if("BBIN".equals(item.getPlatformType())){
                helper.getView(R.id.team_layout).setVisibility(View.GONE);
                helper.getView(R.id.odds).setVisibility(View.GONE);
            }else {
                helper.getView(R.id.team_layout).setVisibility(View.VISIBLE);
                helper.getView(R.id.odds).setVisibility(View.VISIBLE);
            }
        } else {
            helper.getView(R.id.way).setVisibility(View.GONE);
            helper.getView(R.id.team_layout).setVisibility(View.GONE);
        }


        helper.setText(R.id.odds, "--赔率（" + item.getOdds() + ")");


        String dateString = formatter.format(item.getCreateDatetime());
        helper.setText(R.id.time, "时间:" + dateString);
//3未中奖 1 等待开奖 4 撤单 5 派奖回滚成功 2 已中奖 6 回滚异常 7 开奖异常
        switch (item.getResStatus()) {
            case 3:
                helper.setText(R.id.state, "未中奖");
                break;
            case 1:
                helper.setText(R.id.state, "等待开奖");
                break;
            case 4:
                helper.setText(R.id.state, "撤单");
                break;
            case 5:
                helper.setText(R.id.state, "派奖回滚成功");
                break;
            case 2:
                helper.setText(R.id.state, "已中奖");
                break;
            case 6:
                helper.setText(R.id.state, "回滚异常");
                break;
            case 7:
                helper.setText(R.id.state, "开奖异常");
                break;
        }
    }
}
