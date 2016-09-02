package com.fhzc.app.dao.mybatis.util;

public class Const {

    public static final String UMENG_PUSH_APPKEY = "57a0140a67e58e982a0020b4";
    public static final String UMENG_PUSH_APP_MASTER_SECRET = "jqyndid67kfluu56oan1y71dg2qldb63";

    public static final String UMENG_PUSH_APPKEY_ANDROID = "57b16f9467e58eb94900180b";
    public static final String UMENG_PUSH_APP_MASTER_SECRET_ANDROID = "ap7lwshihbysiuidlovhxwumauxakxzq";

    public static final String SYSTEM_NAME = "复华资产";

    /**
     * 聊天图片存放地址
     */
    public static final String CONFIG_KEY_IMAGE_SAVE_PATH =  "chat_image_save_path";

    public static final String CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH =  "system_image_save_path";

    /**
     * 消息轮询时间
     */
    public static final String MESSAGE_POLLING_TIME =  "message_polling_time";

    /**
     * 设备类型
     */
    public static class DEVICE_TYPE{

        public static final String ANDROID = "android";

        public static final String IOS = "ios";
    }

    /**
     * 发短信的参数
     */
    public static class SMS_PARAM{

        public static final String SMS_USERNAME = "sms_username";

        public static final String SMS_PASSWORD = "sms_password";

        public static final String SMS_APPIKEY = "sms_appikey";
    }


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
     * 分隔符
     */
    public static final String SEPRATOR = "$_$";

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

        /**
         * 订单状态
         */
        public static final String ASSETS_STATUS = "assets_status";

        /**
         * 积分状态
         */
        public static final String SCORE_STATUS = "score_status";

        /**
         * 积分来源类型
         */
        public static final String SCORE_FROM_TYPE = "score_from_type";

        /**
         * 客户资产推荐配置
         */
        public static final String ASSET_CONFIG = "asset_config";

        /**
         * 推送渠道
         */
        public static final String PUSH_CHANNEL = "push_channel";

        /**
         * 预约状态
         */
        public static final String RESERVATION_STATUS = "reservation_status";
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

    public static class Score{
        public static final long LONGDAY = 30;

        public static final String ADD = "add";

        public static final String CONSUME = "consume";

        public static final String FROZEN = "frozen";

        public static final String EXPIRE = "expire";

        public static final int IS_APPROVE = 1;

        public static final int NOT_APPROVE = 0;
    }

    public static class CUSTOMER_TYPE{
        /**
         * 单人客户
         */
        public static final String SINGLE_CUSTOMER = "single";

        public static final String SINGLE_CUSTOMER_ZH = "个人";

        /**
         * 机构客户
         */
        public static final String ORGAN_CUSTOMER = "organ";

        public static final String ORGAN_CUSTOMER_ZH = "机构";
    }

    public static class ASSETS_TYPE{
        /**
         * 成立
         */
        public static final String FOUND = "found";

        /**
         * 派息
         */
        public static final String DIVIDEND = "dividend";

        /**
         * 购买
         */
        public static final String PURCHASE = "purchase";

        /**
         * 存续
         */
        public static final String RENEW = "renew";

        /**
         * 兑付
         */
        public static final String REDEMPTION= "redemption";
    }

    public static class USER_ROLE{
        public static final String PLANNER = "planner";

        public static final String CUSTOMER= "customer";
    }

    public static class BANNER_TYPE{
        public static final String TEXT= "index_text";

        public static final String PIC= "index_pic";
    }

    /**
     * 积分审批状态
     */
    public static class APPROVE_STATUS{
        /**
         * 待审批
         */
        public static final Integer WATTING_APPROVE = 0;

        /**
         * 已审批
         */
        public static final Integer APPROVED = 1;
        
        /**
         * 打回审批
         */
        public static final Integer FAILED_APPROVED = 2;
    }

    public static class RISK{

        public static final String low= "low";

    }

     public static class GENDER{

        public static final String MALE= "male";

        public static final String MALE_ZH= "男";

         public static final String FEMALE= "female";

         public static final String FEMALE_ZH= "女";

    }

    /**
     * 活动状态
     */
    public static class ACTIVITY_STATUS{

        public static final Integer WILL= 0;    //待开始

        public static final Integer GOING= 1;   //报名中

        public static final Integer APP_OVER= 2;//报名结束

        public static final Integer ACT_OVER= 3;//活动结束

    }

    /**
     * 活动报名状态
     */
    public static class ACTIVITY_APPLY_STATUS{

        public static final Integer JOIN= 1;

        public static final Integer NOT_JOIN= 0;

    }

    /**
     * 关注类型
     */
    public static class FOCUS_TYPE{

        public static final String PRODUCT= "product";

        public static final String ACTIVITY= "activity";

        public static final String REPORT= "report";

        public static final String RIGHTS= "rights";

    }

    /**
     * 关注状态
     */
    public static class FOCUS_STATUS{
        /**
         * 已关注
         */
        public static final Integer ON= 1;

        /**
         * 不关注
         */
        public static final Integer OFF= 0;
    }

    public static class FROM_TYPE{
        /**
         * 产品
         */
        public static final String PRODUCT= "product";
        /**
         * 活动
         */
        public static final String ACTIVITY= "activity";
        /**
         * 权益
         */
        public static final String RIGHTS= "rights";
        /**
         * 其他
         */
        public static final String OTHER= "other";
    }

    public static class SCORE_VAILD{
        /**
         * 有效
         */
        public static final Integer IS_VAILD = 1;

        /**
         * 无效
         */
        public static final Integer NOT_VAILD = 0;

    }

    public static class About_App{

        public static final String ABOUT_APP = "about_app";

        public static final String CONTACT_US = "contact_us";
    }

    public static class ASSET_TYPE {

        /**
         * 派息
         */
        public static final String DIVIDEND = "dividend";
        /**
         * 购买
         */
        public static final String PURCHASE = "purchase";
        /**
         * 存续
         */
        public static final String RENEW = "renew";
        /**
         * 赎回
         */
        public static final String REDEMPTION = "redemption";
    }

    /**
     * 推送渠道
     */
    public static class PUSH_CHANNEL{

        /**
         * 系统
         */
        public static final Integer SYSTEM = 1;

        /**
         * 短信
         */
        public static final Integer SMS = 2;

        /**
         * 消息
         */
        public static final Integer MESSAGE = 3;

        /**
         * 邮件
         */
        public static final Integer EMAIL = 4;
    }

    /**
     * 推送状态
     */
    public static class PUSH_STATUS{
        public static final Integer NOT_PUSH = 0;

        public static final Integer WAITTING_PUSH = 1;

        public static final Integer PUSHED = 2;
    }

    /**
     * 记录日志类型
     */
    public static class LOG_TYPE{

        /**
         * controller层
         */
        public static final String CONTROLLER = "controller";

        /**
         * service层
         */
        public static final String SERVICE = "service";
    }

    /**
     * 日志级别
     */
    public static class LOG_LEVEL{

        /**
         * 正常信息
         */
        public static final String NORMAL = "normal";

        /**
         * 警告
         */
        public static final String WARNING = "warning";

        /**
         * 错误
         */
        public static final String ERROR = "error";

        /**
         * 严重错误
         */
        public static final String SERIOUS_ERROR = "serious_error";
    }


    public static class ORDER_RESULT{

        public static final String Cancel = "cancel";

        public static final String Success = "success";

        public static final String Failed = "failed";

        public static final String Wait = "wait";
    }

    /**
     * 理财师状态
     */
    public static class PLANNER_STATUS{

        public static final String ON = "on";

        public static final String OFF = "off";
    }


    /**
     * 权益状态
     */
    public static class RIGHTS_STATUS{
        /**
         * 预约中
         */
        public static int ORDER_ING = 0;

        /**
         * 预约成功
         */
        public static int ORDER_SUCCESS = 1;

        /**
         * 预约失败
         */
        public static int ORDER_FAILED = 2;

        /**
         * 客户取消预约
         */
        public static int ORDER_CANCEL = 3;

        /**
         * 客户消费
         */
        public static int CUSTOMER_SPAEND = 4;

        /**
         * 客户缺席
         */
        public static int CUSTOMER_ABSEND = 5;

    }
}

