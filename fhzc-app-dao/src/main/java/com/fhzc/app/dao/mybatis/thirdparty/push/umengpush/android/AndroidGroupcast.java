package com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.android;

import org.json.JSONObject;

import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.AndroidNotification;

public class AndroidGroupcast extends AndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);
    }
}
