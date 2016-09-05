package com.fhzc.app.api.tools;

public class APIConstants {


	public static final String MESSAGE_NEED_LOGIN = "请先登录";

    public static final String FH_ASSIST_SESSIONID = "e572abb3-1b95-4695-b10a-698b8688af9d";
    public static final Integer FH_ASSIST_USERID = 1;

    /**
     * 服务器返回状态
     * @author lihongde
     *
     */
    public static class API_JSON_RESULT{
        /**
         * 成功
         */
        public static int OK = 200;

        /**
         * 客户端请求无效
         */
        public static int BAD_REQUEST = 400;

        /**
         * token失效或非法，需要重新登录
         */
        public static int NEED_LOGIN = 401;

        /**
         * 普通失败，message中带原因
         */
        public static int FAILED = 403;

        /**
         * 未找到请求页面
         */
        public static int NOT_FOUND = 404;

        /**
         * 服务器错误
         */
        public static int SERVER_ERROR = 500;

    }

    /**
     * 消息类型
     * @author lihongde
     */
    public static class Message_Type{
        public static final String Text = "text";

        public static final String Audio = "audio";

        public static final String Image = "image";
    }

    /**
     * Created by jiajitao on 2016/7/21.
     * 表product_reservation 中的result 产品预约结果
     */
    public static class OrderResult{
        //cancel,success,failed,wait
        public static final String Cancel = "cancel";

        public static final String Success = "success";

        public static final String Failed = "failed";

        public static final String Wait = "wait";
    }

    public static class ActivityApply{
        // '是否参加 0否|1是', 活动申请结果状态
        public static int RESULT_NO = 0;

        public static int RESULT_YES = 1;

        public static String TYPE_SELF = "self";

        public static String TYPE_INVITE = "invite";

    }

    /**
     * 用户角色
     */
    public static class USER_ROIE{
        /**
         * 理财师
         */
        public static final String PLANNER = "planner";

        /**
         * 客户
         */
        public static final String CUSTOMER = "customer";

    }

    public static class FocusStatus {
        public static final int On = 1;

        public static final int Off = 0;

    }

    public static class FocusType {
        public static final String Product = "product";

        public static final String Activity = "activity";

        public static final String Rights = "rights";

        public static final String Report = "report";
    }

}
