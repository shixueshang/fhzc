package com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.android;

import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
