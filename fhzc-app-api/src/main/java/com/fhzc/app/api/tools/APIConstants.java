package com.fhzc.app.api.tools;

public class APIConstants {

	public static String UMENG_PUSH_APPKEY = "568e2fd0e0f55af072000aa6";
	public static String UMENG_PUSH_APP_MASTER_SECRET = "kqtyxz5j9mvxvr0tbcjae15epaci8xp4";
	
	public static String UMENG_PUSH_APPKEY_ANDROID = "569f50fce0f55a65e900106a";
	public static String UMENG_PUSH_APP_MASTER_SECRET_ANDROID = "5hj1s7a94o8mgsqvyg6rpccdcwuydozv";
	
	public static String PARAMETER_KEY_X_ACCESS_TOKEN = "X-Access-Token";
	
	public static String MESSAGE_NEED_LOGIN = "未登录或登录信息错误";
	public static String MESSAGE_QUESTION_INVALID = "问题信息不存在";
	
	public static String CONFIG_KEY_NEED_CHECK_ACCESS_TOKEN = "need_check_access_token";

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
	
	
}
