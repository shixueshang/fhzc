package com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.ios;

import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
