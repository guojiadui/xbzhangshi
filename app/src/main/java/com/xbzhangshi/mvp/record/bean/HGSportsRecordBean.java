package com.xbzhangshi.mvp.record.bean;

import java.io.Serializable;
import java.util.List;

public class HGSportsRecordBean {

    /**
     * total : 1
     * aggsData : {"totalBetMoney":10}
     * rows : [{"bettingMoney":10,"gid":3390584,"project":"-0.5","memberName":"zhang","plate":"H","remark":"{\"firstTeam\":\"北京中赫国安\",\"lastTeam\":\"广州富力\",\"result\":\"广州富力\",\"con\":\"0.5\",\"gid\":3390584,\"half\":false,\"homeStrong\":false,\"league\":\"中国足协杯\",\"odds\":1.05,\"startTime\":1537918500000}","betItemType":1,"typeNames":"全场 - 让球","balance":1,"leagueId":129,"gameTimeType":2,"odds":1.05,"homeTeam":"广州富力","guestTeam":"北京中赫国安","stationName":"信博展示站","id":395,"gameKey":"FT_TD_MN","mix":1,"matchId":242,"bettingStatus":2,"stationId":23,"memberId":2364,"dataVersion":0,"sportType":1,"dataType":1,"league":"中国足协杯","betType":3,"itemKey":"ior_RH","bettingDate":1537948360876,"bettingCode":"S18092600051"}]
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
         * totalBetMoney : 10.0
         */

        private double totalBetMoney;

        public double getTotalBetResult() {
            return totalBetResult;
        }

        public void setTotalBetResult(double totalBetResult) {
            this.totalBetResult = totalBetResult;
        }

        private double totalBetResult;//中奖

        public double getTotalBetMoney() {
            return totalBetMoney;
        }

        public void setTotalBetMoney(double totalBetMoney) {
            this.totalBetMoney = totalBetMoney;
        }
    }

    public static class RowsBean  implements Serializable {
        /**
         * bettingMoney : 10.0
         * gid : 3390584
         * project : -0.5
         * memberName : zhang
         * plate : H
         * remark : {"firstTeam":"北京中赫国安","lastTeam":"广州富力","result":"广州富力","con":"0.5","gid":3390584,"half":false,"homeStrong":false,"league":"中国足协杯","odds":1.05,"startTime":1537918500000}
         * betItemType : 1
         * typeNames : 全场 - 让球
         * balance : 1
         * leagueId : 129
         * gameTimeType : 2
         * odds : 1.05
         * homeTeam : 广州富力
         * guestTeam : 北京中赫国安
         * stationName : 信博展示站
         * id : 395
         * gameKey : FT_TD_MN
         * mix : 1
         * matchId : 242
         * bettingStatus : 2
         * stationId : 23
         * memberId : 2364
         * dataVersion : 0
         * sportType : 1
         * dataType : 1
         * league : 中国足协杯
         * betType : 3
         * itemKey : ior_RH
         * bettingDate : 1537948360876
         * bettingCode : S18092600051
         */

        private double bettingMoney;
        private int gid;
        private String project;
        private String memberName;
        private String plate;
        private String remark;
        private int betItemType;
        private String typeNames;
        private int balance;
        private int leagueId;
        private int gameTimeType;
        private double odds;
        private String homeTeam;
        private String guestTeam;
        private String stationName;
        private int id;
        private String gameKey;
        private int mix;
        private int matchId;
        private int bettingStatus;
        private int stationId;
        private int memberId;
        private int dataVersion;
        private int sportType;
        private int dataType;
        private String league;
        private int betType;
        private String itemKey;
        private long bettingDate;
        private String bettingCode;

        public int getBettingResult() {
            return bettingResult;
        }

        public void setBettingResult(int bettingResult) {
            this.bettingResult = bettingResult;
        }

        private int bettingResult;

        public double getBettingMoney() {
            return bettingMoney;
        }

        public void setBettingMoney(double bettingMoney) {
            this.bettingMoney = bettingMoney;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getPlate() {
            return plate;
        }

        public void setPlate(String plate) {
            this.plate = plate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getBetItemType() {
            return betItemType;
        }

        public void setBetItemType(int betItemType) {
            this.betItemType = betItemType;
        }

        public String getTypeNames() {
            return typeNames;
        }

        public void setTypeNames(String typeNames) {
            this.typeNames = typeNames;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getLeagueId() {
            return leagueId;
        }

        public void setLeagueId(int leagueId) {
            this.leagueId = leagueId;
        }

        public int getGameTimeType() {
            return gameTimeType;
        }

        public void setGameTimeType(int gameTimeType) {
            this.gameTimeType = gameTimeType;
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

        public String getGuestTeam() {
            return guestTeam;
        }

        public void setGuestTeam(String guestTeam) {
            this.guestTeam = guestTeam;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGameKey() {
            return gameKey;
        }

        public void setGameKey(String gameKey) {
            this.gameKey = gameKey;
        }

        public int getMix() {
            return mix;
        }

        public void setMix(int mix) {
            this.mix = mix;
        }

        public int getMatchId() {
            return matchId;
        }

        public void setMatchId(int matchId) {
            this.matchId = matchId;
        }

        public int getBettingStatus() {
            return bettingStatus;
        }

        public void setBettingStatus(int bettingStatus) {
            this.bettingStatus = bettingStatus;
        }

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getDataVersion() {
            return dataVersion;
        }

        public void setDataVersion(int dataVersion) {
            this.dataVersion = dataVersion;
        }

        public int getSportType() {
            return sportType;
        }

        public void setSportType(int sportType) {
            this.sportType = sportType;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }

        public String getLeague() {
            return league;
        }

        public void setLeague(String league) {
            this.league = league;
        }

        public int getBetType() {
            return betType;
        }

        public void setBetType(int betType) {
            this.betType = betType;
        }

        public String getItemKey() {
            return itemKey;
        }

        public void setItemKey(String itemKey) {
            this.itemKey = itemKey;
        }

        public long getBettingDate() {
            return bettingDate;
        }

        public void setBettingDate(long bettingDate) {
            this.bettingDate = bettingDate;
        }

        public String getBettingCode() {
            return bettingCode;
        }

        public void setBettingCode(String bettingCode) {
            this.bettingCode = bettingCode;
        }
    }
}
