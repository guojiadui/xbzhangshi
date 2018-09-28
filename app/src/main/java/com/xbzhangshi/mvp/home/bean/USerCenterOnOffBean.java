package com.xbzhangshi.mvp.home.bean;

public class USerCenterOnOffBean {
    /**
     * isLotteryRecord  //彩票投注记录开关
     isSixRecord  //六合投注记录开关
     isRealRecord"//真人投注记录开关
     isElectronicRecord//电子投注记录开关
     isSportRecord//体育投注记录开关
     isChangeMoney//帐变记录开关
     isRealOnOff//帐变记录开关
     isDzOnOff// 电子游艺开关
     isTsOnOff//第三方体育开关
     isTlOnOff//第三方彩票开关
     isChessOnOff//棋牌开关

     */
    /**{
     "isSsOnOff":"on",
     "isEsOnOff":"on",
     "isRealOnOff":"on",
     "isLhcOnOff":"on",
     "isTsOnOff":"on",
     "isTyOnOff":"on",
     "isDzOnOff":"on",
     "isChangeMoney":"on",
     "isChessOnOff":"on",
     "isTlOnOff":"on",
     "isCpOnOff":"on"
     }
     *
     */

    public  String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String name;
}
