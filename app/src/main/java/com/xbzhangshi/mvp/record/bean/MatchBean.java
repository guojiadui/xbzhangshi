package com.xbzhangshi.mvp.record.bean;

public class MatchBean {

    /**
     * firstTeam : 科治
     * lastTeam : 锡尔克堡
     * result : 科治
     * con : vs.
     * gid : 3418524
     * half : false
     * league : 丹麦甲组联赛
     * odds : 2.5
     * startTime : 1539474300000
     */

    private String firstTeam;
    private String lastTeam;
    private String result;
    private String con;
    private int gid;
    private boolean half;
    private String league;
    private double odds;
    private long startTime;

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getLastTeam() {
        return lastTeam;
    }

    public void setLastTeam(String lastTeam) {
        this.lastTeam = lastTeam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public boolean isHalf() {
        return half;
    }

    public void setHalf(boolean half) {
        this.half = half;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
