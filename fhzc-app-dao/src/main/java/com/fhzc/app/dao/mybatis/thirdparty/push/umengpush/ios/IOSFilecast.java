package com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.ios;

import com.fhzc.app.dao.mybatis.thirdparty.push.umengpush.IOSNotification;

public class IOSFilecast extends IOSNotification {
	public IOSFilecast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "filecast");	
	}
	
	public void setFileId(String fileId) throws Exception {
    	setPredefinedKeyValue("file_id", fileId);
    }
}
