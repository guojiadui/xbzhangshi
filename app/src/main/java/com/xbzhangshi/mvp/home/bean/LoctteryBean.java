package com.xbzhangshi.mvp.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class LoctteryBean   {

    /**
     * success : true
     * accessToken : ede36c81-4f20-47b3-a04a-5852b41c8619
     * content : [{"ballonNums":10,"code":"BJSC","czCode":"8","duration":30,"name":"北京赛车","status":2,"sys":false},{"ballonNums":10,"code":"XYFT","czCode":"8","duration":60,"name":"幸运飞艇","status":2,"sys":false},{"ballonNums":8,"code":"CQXYNC","czCode":"12","duration":120,"name":"重庆幸运农场","status":2,"sys":false},{"ballonNums":5,"code":"CQSSC","czCode":"9","duration":70,"name":"重庆时时彩","status":2,"sys":false},{"ballonNums":3,"code":"JSSB3","czCode":"10","duration":120,"name":"江苏骰宝(快3)","status":2,"sys":false},{"ballonNums":3,"code":"PCEGG","czCode":"11","duration":90,"name":"PC蛋蛋","status":2,"sys":false},{"ballonNums":8,"code":"HNKLSF","czCode":"12","duration":90,"name":"湖南快乐十分","status":2,"sys":false},{"ballonNums":5,"code":"TJSSC","czCode":"9","duration":120,"name":"天津时时彩","status":2,"sys":false},{"ballonNums":5,"code":"XJSSC","czCode":"9","duration":70,"name":"新疆时时彩","status":2,"sys":false},{"ballonNums":8,"code":"GDKLSF","czCode":"12","duration":90,"name":"广东快乐十分","status":2,"sys":false},{"ballonNums":7,"code":"SFLHC","czCode":"66","duration":60,"name":"十分六合彩","status":2,"sys":false},{"ballonNums":7,"code":"LHC","czCode":"6","duration":120,"name":"六合彩","status":2,"sys":false},{"ballonNums":5,"code":"FFC","czCode":"9","duration":10,"name":"分分彩","status":2,"sys":false},{"ballonNums":5,"code":"EFC","czCode":"9","duration":30,"name":"二分彩","status":2,"sys":false},{"ballonNums":5,"code":"WFC","czCode":"9","duration":30,"name":"五分彩","status":2,"sys":false},{"ballonNums":3,"code":"HBK3","czCode":"10","duration":120,"name":"湖北快三","status":2,"sys":false},{"ballonNums":3,"code":"AHK3","czCode":"10","duration":120,"name":"安徽快三","status":2,"sys":false},{"ballonNums":3,"code":"SHHK3","czCode":"10","duration":120,"name":"上海快三","status":2,"sys":false},{"ballonNums":3,"code":"HEBK3","czCode":"10","duration":120,"name":"河北快三","status":2,"sys":false},{"ballonNums":3,"code":"GXK3","czCode":"10","duration":120,"name":"广西快三","status":2,"sys":false},{"ballonNums":3,"code":"FC3D","czCode":"15","duration":600,"name":"福彩3D","status":2,"sys":false},{"ballonNums":3,"code":"PL3","czCode":"15","duration":600,"name":"排列三","status":2,"sys":false},{"ballonNums":5,"code":"GD11X5","czCode":"14","duration":90,"name":"广东11选5","status":2,"sys":false},{"ballonNums":5,"code":"SD11X5","czCode":"14","duration":90,"name":"山东11选5","status":2,"sys":false},{"ballonNums":5,"code":"JX11X5","czCode":"14","duration":90,"name":"江西11选5","status":2,"sys":false},{"ballonNums":5,"code":"SH11X5","czCode":"14","duration":90,"name":"上海11选5","status":2,"sys":false},{"ballonNums":10,"code":"SFSC","czCode":"8","duration":10,"name":"澳门赛车","status":2,"sys":false},{"ballonNums":3,"code":"BJK3","czCode":"10","duration":120,"name":"北京快三","status":2,"sys":false},{"ballonNums":3,"code":"JXK3","czCode":"10","duration":120,"name":"江西快三","status":2,"sys":false},{"ballonNums":3,"code":"GSK3","czCode":"10","duration":120,"name":"甘肃快三","status":2,"sys":false},{"ballonNums":3,"code":"FFK3","czCode":"10","duration":10,"name":"极速快三","status":2,"sys":false},{"ballonNums":3,"code":"WFK3","czCode":"10","duration":30,"name":"幸运快三","status":2,"sys":false},{"ballonNums":3,"code":"JLK3","czCode":"10","duration":120,"name":"吉林快三","status":2,"sys":false}]
     */

    private boolean success;
    private String accessToken;
    private List<ContentBean> content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean  {
        /**
         * ballonNums : 10
         * code : BJSC
         * czCode : 8
         * duration : 30
         * name : 北京赛车
         * status : 2
         * sys : false
         */

        private int ballonNums;
        private String code;
        private String czCode;
        private int duration;
        private String name;
        private int status;
        private boolean sys;
        private long activeTime;
        private String lastQihao;
        private String qiHao;
        private long serverTime;


        public int getIsThird() {
            return isThird;
        }

        public void setIsThird(int isThird) {
            this.isThird = isThird;
        }

        private int isThird;

        public String getLastHaoMa() {
            return lastHaoMa;
        }

        public void setLastHaoMa(String lastHaoMa) {
            this.lastHaoMa = lastHaoMa;
        }

        private  String lastHaoMa;

        public long getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(long activeTime) {
            this.activeTime = activeTime;
        }

        public String getLastQihao() {
            return lastQihao;
        }

        public void setLastQihao(String lastQihao) {
            this.lastQihao = lastQihao;
        }

        public String getQiHao() {
            return qiHao;
        }

        public void setQiHao(String qiHao) {
            this.qiHao = qiHao;
        }

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }




        public int getBallonNums() {
            return ballonNums;
        }

        public void setBallonNums(int ballonNums) {
            this.ballonNums = ballonNums;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCzCode() {
            return czCode;
        }

        public void setCzCode(String czCode) {
            this.czCode = czCode;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isSys() {
            return sys;
        }

        public void setSys(boolean sys) {
            this.sys = sys;
        }



        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.ballonNums = in.readInt();
            this.code = in.readString();
            this.czCode = in.readString();
            this.duration = in.readInt();
            this.name = in.readString();
            this.status = in.readInt();
            this.sys = in.readByte() != 0;
        }


    }

    public LoctteryBean() {
    }



    protected LoctteryBean(Parcel in) {
        this.success = in.readByte() != 0;
        this.accessToken = in.readString();
        this.content = new ArrayList<ContentBean>();
        in.readList(this.content, ContentBean.class.getClassLoader());
    }


}
