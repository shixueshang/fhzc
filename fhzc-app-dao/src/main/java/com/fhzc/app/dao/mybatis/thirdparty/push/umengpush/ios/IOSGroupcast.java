package com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.ios;

import org.json.JSONObject;

import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.IOSNotification;

public class IOSGroupcast extends IOSNotification {
	public IOSGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
