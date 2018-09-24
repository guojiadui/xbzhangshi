package com.xbzhangshi.mvp.usercenter.bean;

import java.util.List;

public class ExchangeConfigBean {

    /**
     * success : true
     * accessToken : af22a20a-78d0-46ac-8623-5f009e6bc880
     * content : {"configs":[{"maxVal":1000,"minVal":100,"id":4,"type":2,"numerator":100,"denominator":10,"status":2},{"maxVal":1000,"minVal":100,"id":13,"type":1,"numerator":10,"denominator":100,"status":2}],"score":0}
     */

    private boolean success;
    private String accessToken;
    private ContentBean content;

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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * configs : [{"maxVal":1000,"minVal":100,"id":4,"type":2,"numerator":100,"denominator":10,"status":2},{"maxVal":1000,"minVal":100,"id":13,"type":1,"numerator":10,"denominator":100,"status":2}]
         * score : 0
         */

        private int score;
        private List<ConfigsBean> configs;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<ConfigsBean> getConfigs() {
            return configs;
        }

        public void setConfigs(List<ConfigsBean> configs) {
            this.configs = configs;
        }

        public static class ConfigsBean {
            /**
             * maxVal : 1000
             * minVal : 100
             * id : 4
             * type : 2
             * numerator : 100
             * denominator : 10
             * status : 2
             */

            private int maxVal;
            private int minVal;
            private int id;
            private int type;
            private int numerator;
            private int denominator;
            private int status;

            public int getMaxVal() {
                return maxVal;
            }

            public void setMaxVal(int maxVal) {
                this.maxVal = maxVal;
            }

            public int getMinVal() {
                return minVal;
            }

            public void setMinVal(int minVal) {
                this.minVal = minVal;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getNumerator() {
                return numerator;
            }

            public void setNumerator(int numerator) {
                this.numerator = numerator;
            }

            public int getDenominator() {
                return denominator;
            }

            public void setDenominator(int denominator) {
                this.denominator = denominator;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
