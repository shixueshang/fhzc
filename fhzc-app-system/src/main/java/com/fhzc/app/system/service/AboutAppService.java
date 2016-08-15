package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.AboutApp;

/**
 * Created by lihongde on 2016/8/15 11:54
 */
public interface AboutAppService {

    /**
     * 获得关于app
     * @return
     */
    AboutApp getAboutApp();

    void addOrUpdate(AboutApp aboutApp);
}
