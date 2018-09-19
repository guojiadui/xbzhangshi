package com.xbzhangshi.mvp.home.bean;

import java.util.List;

public class ServiceTimeBean {

    /**
     * lot : [{"ago":120,"balls":7,"code":"LHC","id":2588,"identify":2,"modelStatus":2,"name":"六合彩","sortNo":165,"stationId":23,"status":2,"type":6,"viewGroup":6}]
     * year : 2018
     * LHC : {"actionNo":1,"actionTime":1537450200000,"lastHaoMa":"11,18,32,23,48,09,26","lastQiHao":"2018106","lotCode":"LHC","qiHao":"2018107"}
     * serverTime : 1537337895284
     */

    private int year;
    private LHCBean LHC;
    private long serverTime;
    private List<LotBean> lot;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LHCBean getLHC() {
        return LHC;
    }

    public void setLHC(LHCBean LHC) {
        this.LHC = LHC;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public List<LotBean> getLot() {
        return lot;
    }

    public void setLot(List<LotBean> lot) {
        this.lot = lot;
    }

    public static class LHCBean {
        /**
         * actionNo : 1
         * actionTime : 1537450200000
         * lastHaoMa : 11,18,32,23,48,09,26
         * lastQiHao : 2018106
         * lotCode : LHC
         * qiHao : 2018107
         */

        private int actionNo;
        private long actionTime;
        private String lastHaoMa;
        private String lastQiHao;
        private String lotCode;
        private String qiHao;

        public int getActionNo() {
            return actionNo;
        }

        public void setActionNo(int actionNo) {
            this.actionNo = actionNo;
        }

        public long getActionTime() {
            return actionTime;
        }

        public void setActionTime(long actionTime) {
            this.actionTime = actionTime;
        }

        public String getLastHaoMa() {
            return lastHaoMa;
        }

        public void setLastHaoMa(String lastHaoMa) {
            this.lastHaoMa = lastHaoMa;
        }

        public String getLastQiHao() {
            return lastQiHao;
        }

        public void setLastQiHao(String lastQiHao) {
            this.lastQiHao = lastQiHao;
        }

        public String getLotCode() {
            return lotCode;
        }

        public void setLotCode(String lotCode) {
            this.lotCode = lotCode;
        }

        public String getQiHao() {
            return qiHao;
        }

        public void setQiHao(String qiHao) {
            this.qiHao = qiHao;
        }
    }

    public static class LotBean {
        /**
         * ago : 120
         * balls : 7
         * code : LHC
         * id : 2588
         * identify : 2
         * modelStatus : 2
         * name : 六合彩
         * sortNo : 165
         * stationId : 23
         * status : 2
         * type : 6
         * viewGroup : 6
         */

        private int ago;
        private int balls;
        private String code;
        private int id;
        private int identify;
        private int modelStatus;
        private String name;
        private int sortNo;
        private int stationId;
        private int status;
        private int type;
        private int viewGroup;

        public int getAgo() {
            return ago;
        }

        public void setAgo(int ago) {
            this.ago = ago;
        }

        public int getBalls() {
            return balls;
        }

        public void setBalls(int balls) {
            this.balls = balls;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdentify() {
            return identify;
        }

        public void setIdentify(int identify) {
            this.identify = identify;
        }

        public int getModelStatus() {
            return modelStatus;
        }

        public void setModelStatus(int modelStatus) {
            this.modelStatus = modelStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSortNo() {
            return sortNo;
        }

        public void setSortNo(int sortNo) {
            this.sortNo = sortNo;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getViewGroup() {
            return viewGroup;
        }

        public void setViewGroup(int viewGroup) {
            this.viewGroup = viewGroup;
        }
    }
}
