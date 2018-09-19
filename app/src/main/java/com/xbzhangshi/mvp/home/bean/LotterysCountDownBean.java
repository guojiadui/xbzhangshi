package com.xbzhangshi.mvp.home.bean;

import java.util.List;

public class LotterysCountDownBean {

    /**
     * success : true
     * accessToken : a8dca4e6-006a-4112-9720-aac0817ba223
     * content : [{"activeTime":1537183110000,"ago":90,"haoMa":"17,04,07,15,19,09,16,01","lastQihao":"20180917060","lotCode":"GDKLSF","lotName":"广东快乐十分","qiHao":"20180917062","serverTime":1537182523828},{"activeTime":1537182540000,"ago":60,"haoMa":"46,26,16,01,43,09,32","lastQihao":"20180917115","lotCode":"SFLHC","lotName":"十分六合彩","qiHao":"20180917116","serverTime":1537182523849},{"activeTime":1537182530000,"ago":10,"haoMa":"2,5,5,0,8","lastQihao":"201809171148","lotCode":"FFC","lotName":"分分彩","qiHao":"201809171149","serverTime":1537182523864},{"activeTime":1537277280000,"ago":120,"haoMa":"06,04,48,11,37,33,41","lastQihao":"2018105","lotCode":"LHC","lotName":"六合彩","qiHao":"2018106","serverTime":1537182523870},{"activeTime":1537182780000,"ago":60,"haoMa":"05,04,08,01,03,10,02,06,07,09","lastQihao":"20180917072","lotCode":"XYFT","lotName":"幸运飞艇","qiHao":"20180917074","serverTime":1537182523888},{"activeTime":1537182570000,"ago":30,"haoMa":"7,8,2,0,6","lastQihao":"20180917574","lotCode":"EFC","lotName":"二分彩","qiHao":"20180917575","serverTime":1537182523968}]
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

    public static class ContentBean {
        /**
         * activeTime : 1537183110000
         * ago : 90
         * haoMa : 17,04,07,15,19,09,16,01
         * lastQihao : 20180917060
         * lotCode : GDKLSF
         * lotName : 广东快乐十分
         * qiHao : 20180917062
         * serverTime : 1537182523828
         */

        private long activeTime;
        private int ago;
        private String haoMa;
        private String lastQihao;
        private String lotCode;
        private String lotName;
        private String qiHao;
        private long serverTime;





        public long getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(long activeTime) {
            this.activeTime = activeTime;
        }

        public int getAgo() {
            return ago;
        }

        public void setAgo(int ago) {
            this.ago = ago;
        }

        public String getHaoMa() {
            return haoMa;
        }

        public void setHaoMa(String haoMa) {
            this.haoMa = haoMa;
        }

        public String getLastQihao() {
            return lastQihao;
        }

        public void setLastQihao(String lastQihao) {
            this.lastQihao = lastQihao;
        }

        public String getLotCode() {
            return lotCode;
        }

        public void setLotCode(String lotCode) {
            this.lotCode = lotCode;
        }

        public String getLotName() {
            return lotName;
        }

        public void setLotName(String lotName) {
            this.lotName = lotName;
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
    }
}
