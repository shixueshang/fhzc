package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.AboutApp;

/**
 * Created by freeman on 16/8/17.
 */
public interface AboutAppService {
    AboutApp getLatestApp();
    AboutApp getAppByVersion(String version);
}
