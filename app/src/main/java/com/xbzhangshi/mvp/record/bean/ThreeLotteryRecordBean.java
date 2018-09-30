package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class ThreeLotteryRecordBean {

    /**
     * total : 2
     * aggsData : {"bettingMoneyCount":10,"realBettingMoneyCount":10,"winMoneyCount":0}
     * rows : [{"gameType":"VR木星赛车","bettingMoney":2,"winMoney":0,"orderId":"vr_lottery_10928134659114079402","thirdExtInfo":"01,06,02,08,04,05,09,07,03,10","platformType":"VR","md5Str":"7ce26f3995a45abb9cdf2616ff7a02e4","type":97,"serverId":13,"gameCategory":35,"currency":"CNY","id":6129727,"thirdMemberAccount":"xbml123456","realBettingMoney":2,"stationId":23,"tableCode":"201809280287","bettingTime":1538113619000,"createDatetime":1538113671865,"gameTypeCode":"大小单双","accountId":2240,"bettingContent":"大小单双-,,,,,,,,大,","bettingCode":"10928134659114079402","round":"201809280287","playType":",,,,,,,,大,","bettingTimeGmt4":1538041619000,"thirdMemberId":57384,"gameCode":"201809280287","account":"ml123456","parents":",367,"},{"gameType":"VR六合彩","bettingMoney":8,"winMoney":0,"orderId":"vr_lottery_10928134618880416170","thirdExtInfo":"33,28,24,48,07,18,49","platformType":"VR","md5Str":"0d489b8ad4b0d62549234006dd020464","type":97,"serverId":13,"gameCategory":16,"currency":"CNY","id":6129687,"thirdMemberAccount":"xbml123456","realBettingMoney":8,"stationId":23,"tableCode":"20180928048","bettingTime":1538113578000,"createDatetime":1538113611901,"gameTypeCode":"特码-单码","accountId":2240,"bettingContent":"特码-单码-17,20","bettingCode":"10928134618880416170","round":"20180928048","playType":"17,20","bettingTimeGmt4":1538041578000,"thirdMemberId":57384,"gameCode":"20180928048","account":"ml123456","parents":",367,"}]
     */

    private int total;
    private AggsDataBean aggsData;
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class AggsDataBean {
        /**
         * bettingMoneyCount : 10.0
         * realBettingMoneyCount : 10.0
         * winMoneyCount : 0.0
         */

        private double bettingMoneyCount;
        private double realBettingMoneyCount;
        private double winMoneyCount;

        public double getBettingMoneyCount() {
            return bettingMoneyCount;
        }

        public void setBettingMoneyCount(double bettingMoneyCount) {
            this.bettingMoneyCount = bettingMoneyCount;
        }

        public double getRealBettingMoneyCount() {
            return realBettingMoneyCount;
        }

        public void setRealBettingMoneyCount(double realBettingMoneyCount) {
            this.realBettingMoneyCount = realBettingMoneyCount;
        }

        public double getWinMoneyCount() {
            return winMoneyCount;
        }

        public void setWinMoneyCount(double winMoneyCount) {
            this.winMoneyCount = winMoneyCount;
        }
    }

    public static class RowsBean {
        /**
         * gameType : VR木星赛车
         * bettingMoney : 2.0
         * winMoney : 0.0
         * orderId : vr_lottery_10928134659114079402
         * thirdExtInfo : 01,06,02,08,04,05,09,07,03,10
         * platformType : VR
         * md5Str : 7ce26f3995a45abb9cdf2616ff7a02e4
         * type : 97
         * serverId : 13
         * gameCategory : 35
         * currency : CNY
         * id : 6129727
         * thirdMemberAccount : xbml123456
         * realBettingMoney : 2.0
         * stationId : 23
         * tableCode : 201809280287
         * bettingTime : 1538113619000
         * createDatetime : 1538113671865
         * gameTypeCode : 大小单双
         * accountId : 2240
         * bettingContent : 大小单双-,,,,,,,,大,
         * bettingCode : 10928134659114079402
         * round : 201809280287
         * playType : ,,,,,,,,大,
         * bettingTimeGmt4 : 1538041619000
         * thirdMemberId : 57384
         * gameCode : 201809280287
         * account : ml123456
         * parents : ,367,
         */

        private String gameType;
        private double bettingMoney;
        private double winMoney;
        private String orderId;
        private String thirdExtInfo;
        private String platformType;
        private String md5Str;
        private int type;
        private int serverId;
        private int gameCategory;
        private String currency;
        private int id;
        private String thirdMemberAccount;
        private double realBettingMoney;
        private int stationId;
        private String tableCode;
        private long bettingTime;
        private long createDatetime;
        private String gameTypeCode;
        private int accountId;
        private String bettingContent;
        private String bettingCode;
        private String round;
        private String playType;
        private long bettingTimeGmt4;
        private int thirdMemberId;
        private String gameCode;
        private String account;
        private String parents;

        public String getGameType() {
            return gameType;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public double getBettingMoney() {
            return bettingMoney;
        }

        public void setBettingMoney(double bettingMoney) {
            this.bettingMoney = bettingMoney;
        }

        public double getWinMoney() {
            return winMoney;
        }

        public void setWinMoney(double winMoney) {
            this.winMoney = winMoney;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getThirdExtInfo() {
            return thirdExtInfo;
        }

        public void setThirdExtInfo(String thirdExtInfo) {
            this.thirdExtInfo = thirdExtInfo;
        }

        public String getPlatformType() {
            return platformType;
        }

        public void setPlatformType(String platformType) {
            this.platformType = platformType;
        }

        public String getMd5Str() {
            return md5Str;
        }

        public void setMd5Str(String md5Str) {
            this.md5Str = md5Str;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getServerId() {
            return serverId;
        }

        public void setServerId(int serverId) {
            this.serverId = serverId;
        }

        public int getGameCategory() {
            return gameCategory;
        }

        public void setGameCategory(int gameCategory) {
            this.gameCategory = gameCategory;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getThirdMemberAccount() {
            return thirdMemberAccount;
        }

        public void setThirdMemberAccount(String thirdMemberAccount) {
            this.thirdMemberAccount = thirdMemberAccount;
        }

        public double getRealBettingMoney() {
            return realBettingMoney;
        }

        public void setRealBettingMoney(double realBettingMoney) {
            this.realBettingMoney = realBettingMoney;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getTableCode() {
            return tableCode;
        }

        public void setTableCode(String tableCode) {
            this.tableCode = tableCode;
        }

        public long getBettingTime() {
            return bettingTime;
        }

        public void setBettingTime(long bettingTime) {
            this.bettingTime = bettingTime;
        }

        public long getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(long createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getGameTypeCode() {
            return gameTypeCode;
        }

        public void setGameTypeCode(String gameTypeCode) {
            this.gameTypeCode = gameTypeCode;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBettingContent() {
            return bettingContent;
        }

        public void setBettingContent(String bettingContent) {
            this.bettingContent = bettingContent;
        }

        public String getBettingCode() {
            return bettingCode;
        }

        public void setBettingCode(String bettingCode) {
            this.bettingCode = bettingCode;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public String getPlayType() {
            return playType;
        }

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public long getBettingTimeGmt4() {
            return bettingTimeGmt4;
        }

        public void setBettingTimeGmt4(long bettingTimeGmt4) {
            this.bettingTimeGmt4 = bettingTimeGmt4;
        }

        public int getThirdMemberId() {
            return thirdMemberId;
        }

        public void setThirdMemberId(int thirdMemberId) {
            this.thirdMemberId = thirdMemberId;
        }

        public String getGameCode() {
            return gameCode;
        }

        public void setGameCode(String gameCode) {
            this.gameCode = gameCode;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getParents() {
            return parents;
        }

        public void setParents(String parents) {
            this.parents = parents;
        }
    }
}
