package com.xbzhangshi.mvp.login.bean;

import java.util.List;

public class RegisterItemBean {

    /**
     * success : true
     * accessToken : 18162a05-c995-4548-8a0e-3df2401941c4
     * content : [{"createUserId":1,"modifyDatetime":1501227072848,"requiredVal":1,"show":2,"modifyUserId":1,"source":"","type":1,"required":2,"platform":1,"createDatetime":1462972272314,"regex":"^[\\u4E00-\\u9FA5]+(·[\\u4E00-\\u9FA5]+)*$","validateVal":1,"uniqueness":2,"name":"真实姓名","flagActive":1,"uniqueVal":1,"id":3,"showVal":2,"key":"userName","validate":2,"status":2},{"createUserId":1,"modifyDatetime":1501227267908,"requiredVal":1,"show":2,"modifyUserId":1,"source":"","type":1,"required":2,"platform":1,"createDatetime":1463391657949,"regex":"^[0-9]*$","validateVal":1,"uniqueness":2,"name":"QQ","flagActive":1,"uniqueVal":2,"id":10,"showVal":2,"key":"qq","validate":2,"status":2}]
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
         * createUserId : 1
         * modifyDatetime : 1501227072848
         * requiredVal : 1
         * show : 2
         * modifyUserId : 1
         * source :
         * type : 1
         * required : 2
         * platform : 1
         * createDatetime : 1462972272314
         * regex : ^[\u4E00-\u9FA5]+(·[\u4E00-\u9FA5]+)*$
         * validateVal : 1
         * uniqueness : 2
         * name : 真实姓名
         * flagActive : 1
         * uniqueVal : 1
         * id : 3
         * showVal : 2
         * key : userName
         * validate : 2
         * status : 2
         */

        private int createUserId;
        private long modifyDatetime;
        private int requiredVal;
        private int show;
        private int modifyUserId;
        private String source;
        private int type;
        private int required;
        private int platform;
        private long createDatetime;
        private String regex;
        private int validateVal;
        private int uniqueness;
        private String name;
        private int flagActive;
        private int uniqueVal;
        private int id;
        private int showVal;
        private String key;
        private int validate;
        private int status;
        public String value;
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public long getModifyDatetime() {
            return modifyDatetime;
        }

        public void setModifyDatetime(long modifyDatetime) {
            this.modifyDatetime = modifyDatetime;
        }

        public int getRequiredVal() {
            return requiredVal;
        }

        public void setRequiredVal(int requiredVal) {
            this.requiredVal = requiredVal;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getModifyUserId() {
            return modifyUserId;
        }

        public void setModifyUserId(int modifyUserId) {
            this.modifyUserId = modifyUserId;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getRequired() {
            return required;
        }

        public void setRequired(int required) {
            this.required = required;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public long getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(long createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getRegex() {
            return regex;
        }

        public void setRegex(String regex) {
            this.regex = regex;
        }

        public int getValidateVal() {
            return validateVal;
        }

        public void setValidateVal(int validateVal) {
            this.validateVal = validateVal;
        }

        public int getUniqueness() {
            return uniqueness;
        }

        public void setUniqueness(int uniqueness) {
            this.uniqueness = uniqueness;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFlagActive() {
            return flagActive;
        }

        public void setFlagActive(int flagActive) {
            this.flagActive = flagActive;
        }

        public int getUniqueVal() {
            return uniqueVal;
        }

        public void setUniqueVal(int uniqueVal) {
            this.uniqueVal = uniqueVal;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShowVal() {
            return showVal;
        }

        public void setShowVal(int showVal) {
            this.showVal = showVal;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValidate() {
            return validate;
        }

        public void setValidate(int validate) {
            this.validate = validate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
