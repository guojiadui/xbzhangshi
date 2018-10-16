package com.xbzhangshi.mvp.record.bean;

import java.io.Serializable;
import java.util.List;

public class SBSportsRecordBean {


    /**
     * total : 3
     * aggsData : {"bettingMoneyCount":30,"realBettingMoneyCount":20,"winMoneyCount":21.3}
     * rows : [{"gameType":44,"bettingMoney":10,"winMoney":0,"awayTeam":"Phettalung Phitakthangluang","orderId":"ibc_sp_105118760923","platformType":"IBC","md5Str":"aa4db4c6de59f9bc4a387f63845f6e1b","orderStatus":1,"oddsType":1,"type":10,"serverId":13,"fetchId":254479,"gameName":"泰拳","odds":0.88,"homeTeam":"Jaojor Dabsungtangluang","currency":"CNY","id":36335,"thirdMemberAccount":"xbml123456","realBettingMoney":0,"resStatus":1,"matchId":27228144,"info":"主隊 0.0","stationId":23,"league":"泰拳 - 第七频道拳击馆 (CH 7)","playName":"讓球","bettingTime":1539496473713,"matchTime":1539502199000,"createDatetime":1539496483543,"accountId":2240,"bettingCode":"105118760923","playType":1,"lastModifyTime":1539496473713,"thirdMemberId":61755,"account":"ml123456","parents":",367,"},{"bettingMoney":10,"winMoney":0,"awayTeam":"","orderId":"ibc_sp_105118756050","platformType":"IBC","md5Str":"d357526e7e2bbcdf752716ab712dd5a7","orderStatus":1,"oddsType":3,"type":10,"serverId":13,"fetchId":254534,"gameName":"","odds":5.66,"homeTeam":"","currency":"CNY","id":36333,"thirdMemberAccount":"xbml123456","realBettingMoney":10,"resStatus":3,"matchId":29,"info":"1","stationId":23,"league":"","playName":"混合过关三串一 (1 个注单)","bettingTime":1539496399913,"matchTime":1539489600000,"parlayData":"[{\"leagueName\":\"日本职业联赛杯\",\"playContent\":\"小\",\"awayTeam\":\"鹿岛鹿角\",\"odds\":1.8400,\"playName\":\"大小盤\",\"homeTeam\":\"横滨水手\"},{\"leagueName\":\"日本J2联赛\",\"playContent\":\"大\",\"awayTeam\":\"松本山雅FC \",\"odds\":1.7700,\"playName\":\"大小盤\",\"homeTeam\":\"金泽\"},{\"leagueName\":\"日本J2联赛\",\"playContent\":\"小\",\"awayTeam\":\"熊本\",\"odds\":1.7400,\"playName\":\"大小盤\",\"homeTeam\":\"赞岐\"}]","createDatetime":1539496404433,"accountId":2240,"bettingCode":"105118756050","playType":29,"lastModifyTime":1539496399913,"thirdMemberId":61755,"account":"ml123456","parents":",367,"},{"gameType":1,"bettingMoney":10,"winMoney":21.3,"awayTeam":"播磨阿尔比恩 (女)","orderId":"ibc_sp_105118744704","platformType":"IBC","md5Str":"f52a22198d7502bd789b743327071853","orderStatus":1,"resScore":"1:0","oddsType":1,"type":10,"serverId":13,"score":"0:0","fetchId":254499,"gameName":"足球","odds":1.13,"homeTeam":"寺扶达世田谷 (女)","currency":"CNY","id":36332,"thirdMemberAccount":"xbml123456","realBettingMoney":10,"resStatus":2,"matchId":27221599,"info":"主隊 0.0","stationId":23,"league":"日本 J-2 女子联赛","playName":"讓球","bettingTime":1539496220035,"matchTime":1539491340000,"createDatetime":1539496223637,"accountId":2240,"bettingCode":"105118744704","playType":1,"lastModifyTime":1539496220035,"thirdMemberId":61755,"account":"ml123456","parents":",367,"}]
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
         * bettingMoneyCount : 30.0
         * realBettingMoneyCount : 20.0
         * winMoneyCount : 21.3
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

    public static class RowsBean implements Serializable {
        /**
         * gameType : 44
         * bettingMoney : 10.0
         * winMoney : 0.0
         * awayTeam : Phettalung Phitakthangluang
         * orderId : ibc_sp_105118760923
         * platformType : IBC
         * md5Str : aa4db4c6de59f9bc4a387f63845f6e1b
         * orderStatus : 1
         * oddsType : 1
         * type : 10
         * serverId : 13
         * fetchId : 254479
         * gameName : 泰拳
         * odds : 0.88
         * homeTeam : Jaojor Dabsungtangluang
         * currency : CNY
         * id : 36335
         * thirdMemberAccount : xbml123456
         * realBettingMoney : 0.0
         * resStatus : 1
         * matchId : 27228144
         * info : 主隊 0.0
         * stationId : 23
         * league : 泰拳 - 第七频道拳击馆 (CH 7)
         * playName : 讓球
         * bettingTime : 1539496473713
         * matchTime : 1539502199000
         * createDatetime : 1539496483543
         * accountId : 2240
         * bettingCode : 105118760923
         * playType : 1
         * lastModifyTime : 1539496473713
         * thirdMemberId : 61755
         * account : ml123456
         * parents : ,367,
         * parlayData : [{"leagueName":"日本职业联赛杯","playContent":"小","awayTeam":"鹿岛鹿角","odds":1.8400,"playName":"大小盤","homeTeam":"横滨水手"},{"leagueName":"日本J2联赛","playContent":"大","awayTeam":"松本山雅FC ","odds":1.7700,"playName":"大小盤","homeTeam":"金泽"},{"leagueName":"日本J2联赛","playContent":"小","awayTeam":"熊本","odds":1.7400,"playName":"大小盤","homeTeam":"赞岐"}]
         * resScore : 1:0
         * score : 0:0
         */

        private int gameType;
        private double bettingMoney;
        private double winMoney;
        private String awayTeam;
        private String orderId;
        private String platformType;
        private String md5Str;
        private int orderStatus;
        private int oddsType;
        private int type;
        private int serverId;
        private int fetchId;
        private String gameName;
        private double odds;
        private String homeTeam;
        private String currency;
        private int id;
        private String thirdMemberAccount;
        private double realBettingMoney;
        private int resStatus;
        private int matchId;
        private String info;
        private int stationId;
        private String league;
        private String playName;
        private long bettingTime;
        private long matchTime;
        private long createDatetime;
        private int accountId;
        private String bettingCode;
        private int playType;
        private long lastModifyTime;
        private int thirdMemberId;
        private String account;
        private String parents;
        private String parlayData;
        private String resScore;
        private String score;

        public int getGameType() {
            return gameType;
        }

        public void setGameType(int gameType) {
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

        public String getAwayTeam() {
            return awayTeam;
        }

        public void setAwayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
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

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getOddsType() {
            return oddsType;
        }

        public void setOddsType(int oddsType) {
            this.oddsType = oddsType;
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

        public int getFetchId() {
            return fetchId;
        }

        public void setFetchId(int fetchId) {
            this.fetchId = fetchId;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public void setHomeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
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

        public int getResStatus() {
            return resStatus;
        }

        public void setResStatus(int resStatus) {
            this.resStatus = resStatus;
        }

        public int getMatchId() {
            return matchId;
        }

        public void setMatchId(int matchId) {
            this.matchId = matchId;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public String getLeague() {
            return league;
        }

        public void setLeague(String league) {
            this.league = league;
        }

        public String getPlayName() {
            return playName;
        }

        public void setPlayName(String playName) {
            this.playName = playName;
        }

        public long getBettingTime() {
            return bettingTime;
        }

        public void setBettingTime(long bettingTime) {
            this.bettingTime = bettingTime;
        }

        public long getMatchTime() {
            return matchTime;
        }

        public void setMatchTime(long matchTime) {
            this.matchTime = matchTime;
        }

        public long getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(long createDatetime) {
            this.createDatetime = createDatetime;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBettingCode() {
            return bettingCode;
        }

        public void setBettingCode(String bettingCode) {
            this.bettingCode = bettingCode;
        }

        public int getPlayType() {
            return playType;
        }

        public void setPlayType(int playType) {
            this.playType = playType;
        }

        public long getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(long lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public int getThirdMemberId() {
            return thirdMemberId;
        }

        public void setThirdMemberId(int thirdMemberId) {
            this.thirdMemberId = thirdMemberId;
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

        public String getParlayData() {
            return parlayData;
        }

        public void setParlayData(String parlayData) {
            this.parlayData = parlayData;
        }

        public String getResScore() {
            return resScore;
        }

        public void setResScore(String resScore) {
            this.resScore = resScore;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
