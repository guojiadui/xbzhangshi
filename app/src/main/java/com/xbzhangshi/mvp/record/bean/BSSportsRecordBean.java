package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class BSSportsRecordBean {

    /**
     * total : 0
     * aggsData : {}
     * rows : []
     */

    private int total;
    private AggsDataBean aggsData;
    private List<?> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public AggsDataBean getAggsData() {
        return aggsData;
    }

    public void setAggsData(AggsDataBean aggsData) {
        this.aggsData = aggsData;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public static class AggsDataBean {
        public double getBettingMoneyCount() {
            return bettingMoneyCount;
        }

        public void setBettingMoneyCount(double bettingMoneyCount) {
            this.bettingMoneyCount = bettingMoneyCount;
        }

        public double getWinMoneyCount() {
            return winMoneyCount;
        }

        public void setWinMoneyCount(double winMoneyCount) {
            this.winMoneyCount = winMoneyCount;
        }

        private  double bettingMoneyCount;//投注
        private  double winMoneyCount;//中奖
    }
}
