package com.xbzhangshi.mvp.home.bean;

import java.util.List;

public class OpenPrizeBean {

    /**
     * data : [{"lotCode":"BJSC","qihao":"704810","haoma":"?,?,?,?,?,?,?,?,?,?","lotName":"北京赛车","lotType":8},{"lotCode":"XYFT","qihao":"20180919072","haoma":"05,04,08,01,03,09,10,07,02,06","lotName":"幸运飞艇","lotType":8},{"lotCode":"CQXYNC","qihao":"2018091968","haoma":"11,15,01,19,18,09,10,05","lotName":"重庆幸运农场","lotType":12},{"lotCode":"CQSSC","qihao":"20180919078","haoma":"9,8,8,5,7","lotName":"重庆时时彩","lotType":9},{"lotCode":"JSSB3","qihao":"20180919063","haoma":"2,3,6","lotName":"江苏骰宝(快3)","lotType":10},{"lotCode":"PCEGG","qihao":"910788","haoma":"4,0,9","lotName":"PC蛋蛋","lotType":11},{"lotCode":"HNKLSF","qihao":"20180919060","haoma":"04,03,06,09,02,13,05,01","lotName":"湖南快乐十分","lotType":12},{"lotCode":"TJSSC","qihao":"20180919061","haoma":"?,?,?,?,?","lotName":"天津时时彩","lotType":9},{"lotCode":"XJSSC","qihao":"20180919054","haoma":"4,7,9,6,7","lotName":"新疆时时彩","lotType":9},{"lotCode":"GDKLSF","qihao":"20180919060","haoma":"04,06,15,08,14,09,03,13","lotName":"广东快乐十分","lotType":12},{"lotCode":"SFLHC","qihao":"20180919115","haoma":"39,34,31,29,07,05,36","lotName":"十分六合彩","lotType":66},{"lotCode":"LHC","qihao":"2018106","haoma":"11,18,32,23,48,09,26","lotName":"六合彩","lotType":6},{"lotCode":"FFC","qihao":"201809191147","haoma":"1,0,3,9,6","lotName":"分分彩","lotType":9},{"lotCode":"EFC","qihao":"20180919573","haoma":"7,4,5,7,8","lotName":"二分彩","lotType":9},{"lotCode":"WFC","qihao":"20180919229","haoma":"5,0,1,8,5","lotName":"五分彩","lotType":9},{"lotCode":"HBK3","qihao":"20180919060","haoma":"1,4,5","lotName":"湖北快三","lotType":10},{"lotCode":"AHK3","qihao":"20180919062","haoma":"4,5,6","lotName":"安徽快三","lotType":10},{"lotCode":"SHHK3","qihao":"20180919061","haoma":"2,2,3","lotName":"上海快三","lotType":10},{"lotCode":"HEBK3","qihao":"20180919063","haoma":"2,5,6","lotName":"河北快三","lotType":10},{"lotCode":"GXK3","qihao":"20180919058","haoma":"?,?,?","lotName":"广西快三","lotType":10},{"lotCode":"FC3D","qihao":"2018254","haoma":"6,5,7","lotName":"福彩3D","lotType":15},{"lotCode":"PL3","qihao":"2018254","haoma":"0,2,1","lotName":"排列三","lotType":15},{"lotCode":"GD11X5","qihao":"2018091960","haoma":"04,11,09,02,07","lotName":"广东11选5","lotType":14},{"lotCode":"SD11X5","qihao":"2018091964","haoma":"06,09,07,02,11","lotName":"山东11选5","lotType":14},{"lotCode":"JX11X5","qihao":"2018091960","haoma":"02,01,03,07,11","lotName":"江西11选5","lotType":14},{"lotCode":"SH11X5","qihao":"2018091961","haoma":"03,02,07,01,08","lotName":"上海11选5","lotType":14},{"lotCode":"SFSC","qihao":"20180919383","haoma":"07,01,06,03,05,02,09,10,04,08","lotName":"澳门赛车","lotType":8},{"lotCode":"BJK3","qihao":"120730","haoma":"2,4,5","lotName":"北京快三","lotType":10},{"lotCode":"JXK3","qihao":"20180919061","haoma":"?,?,?","lotName":"江西快三","lotType":10},{"lotCode":"GSK3","qihao":"20180919054","haoma":"?,?,?","lotName":"甘肃快三","lotType":10},{"lotCode":"FFK3","qihao":"201809191148","haoma":"6,3,3","lotName":"极速快三","lotType":10},{"lotCode":"WFK3","qihao":"20180919230","haoma":"1,1,3","lotName":"幸运快三","lotType":10},{"lotCode":"JLK3","qihao":"20180919071","haoma":"?,?,?","lotName":"吉林快三","lotType":10}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lotCode : BJSC
         * qihao : 704810
         * haoma : ?,?,?,?,?,?,?,?,?,?
         * lotName : 北京赛车
         * lotType : 8
         */

        private String lotCode;
        private String qihao;
        private String haoma;
        private String lotName;
        private int lotType;

        public String getLotCode() {
            return lotCode;
        }

        public void setLotCode(String lotCode) {
            this.lotCode = lotCode;
        }

        public String getQihao() {
            return qihao;
        }

        public void setQihao(String qihao) {
            this.qihao = qihao;
        }

        public String getHaoma() {
            return haoma;
        }

        public void setHaoma(String haoma) {
            this.haoma = haoma;
        }

        public String getLotName() {
            return lotName;
        }

        public void setLotName(String lotName) {
            this.lotName = lotName;
        }

        public int getLotType() {
            return lotType;
        }

        public void setLotType(int lotType) {
            this.lotType = lotType;
        }
    }
}
