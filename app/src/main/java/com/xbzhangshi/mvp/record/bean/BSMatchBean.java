package com.xbzhangshi.mvp.record.bean;

public class BSMatchBean {

    /**
     * leagueName : 日本职业联赛杯
     * playContent : 小
     * awayTeam : 鹿岛鹿角
     * odds : 1.84
     * playName : 大小盤
     * homeTeam : 横滨水手
     */

    private String leagueName;
    private String playContent;
    private String awayTeam;
    private double odds;
    private String playName;
    private String homeTeam;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getPlayContent() {
        return playContent;
    }

    public void setPlayContent(String playContent) {
        this.playContent = playContent;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
}
