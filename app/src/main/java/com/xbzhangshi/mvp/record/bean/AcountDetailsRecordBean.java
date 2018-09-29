package com.xbzhangshi.mvp.record.bean;

import java.util.List;

public class AcountDetailsRecordBean {

    /**
     * aggsData : {}
     * currentPageNo : 1
     * hasNext : false
     * hasPre : false
     * list : []
     * nextPage : 1
     * pageSize : 20
     * prePage : 1
     * results : []
     * set : []
     * start : 0
     * totalCount : 0
     * totalPageCount : 0
     */

    private AggsDataBean aggsData;
    private int currentPageNo;
    private boolean hasNext;
    private boolean hasPre;
    private int nextPage;
    private int pageSize;
    private int prePage;
    private int start;
    private int totalCount;
    private int totalPageCount;
    private List<ListBean> list;
    private List<?> results;
    private List<?> set;
  public  static  class ListBean{

  }
    public AggsDataBean getAggsData() {
        return aggsData;
    }

    public void setAggsData(AggsDataBean aggsData) {
        this.aggsData = aggsData;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<?> getResults() {
        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }

    public List<?> getSet() {
        return set;
    }

    public void setSet(List<?> set) {
        this.set = set;
    }

    public static class AggsDataBean {
    }
}
