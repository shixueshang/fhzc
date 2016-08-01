package com.fhzc.app.dao.mybatis.util;

public class Const {

    /**
     * 聊天图片存放地址
     */
    public static final String CONFIG_KEY_IMAGE_SAVE_PATH =  "chat_image_save_path";

    public static final String CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH =  "system_image_save_path";

    /**
     * 顶级机构id
     */
    public static final Integer ROOT_DEPT_ID = 1;

    /**
     * 超级管理员
     */
    public static final String ADMIN_USER = "admin";

    /**
     * 默认分页起始页
     */
    public static final Integer DEFAULT_PAGE = 1;
    /**
     * 默认页面大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 25;

    /**
     * 是否
     */
    public static class YES_OR_NO{
        public static final Integer YES = 1;

        public static final Integer NO = 0;

    }

    /**
     * 字典类型
     */
    public static class DIC_CAT{
        /**
         *  产品类型
         */
        public static  final String PRODUCT_TYPE = "product_type";

        /**
         *  报告类型
         */
        public static  final String REPORT_CATEGORY = "report_category";

        /**
         *  权益类型
         */
        public static  final String RIGHTS_CATEGORY = "rights_category";

        /**
         *  活动类型
         */
        public static  final String ACTIVITY_CATEGORY = "activity_category";

        /**
         *  产品状态
         */
        public static  final String PRODUCT_STATUS = "product_status";

        /**
         *  产品发行模式
         */
        public static  final String PRODUCT_ISSUE_TYPE = "product_issue_type";

        /**
         *  客户等级
         */
        public static  final String CUSTOMER_LEVEL = "customer_level";

        /**
         *  风险评级
         */
        public static  final String RISK_LEVEL = "risk_level";

        /**
         *  是否
         */
        public static  final String YES_NO = "yes_no";

        /**
         * 证件类型
         */
        public static final String PASSPORT = "passport";
    }

    /**
     * 数据状态
     */
    public static  class Data_Status{
        /**
         * 正常状态
         */
        public static final Integer DATA_NORMAL = 0;

        /**
         * 删除状态
         */
        public static final Integer DATA_DELETE = 1;
    }

    /**
     * 数据库读写权限
     */
    public static class READ_WRITE{
        /**
         * 只读
         */
        public static final String READ_ONLY = "r";

        /**
         * 读写
         */
        public static final String READ_AND_WRITE = "rw";
    }
}
