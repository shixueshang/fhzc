package com.fhzc.app.dao.mybatis.util;


public class Const {

    /**
     * 聊天图片存放地址
     */
    public static String CONFIG_KEY_IMAGE_SAVE_PATH =  "chat_image_save_path";

    public static String CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH =  "system_image_save_path";

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
    }

}
