package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AboutAppMapper;
import com.fhzc.app.dao.mybatis.model.AboutApp;
import com.fhzc.app.dao.mybatis.model.AboutAppExample;
import com.fhzc.app.system.service.AboutAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/8/15 11:56
 */
@Service
public class AboutAppServiceImpl implements AboutAppService {

    @Resource
    private AboutAppMapper aboutAppMapper;

    @Override
    public AboutApp getAboutApp() {
        AboutAppExample example = new AboutAppExample();
        if(aboutAppMapper.countByExample(example) > 0){
            return aboutAppMapper.selectByExampleWithBLOBs(example).get(0);
        }
        return null;
    }

    @Override
    public void addOrUpdate(AboutApp aboutApp) {
        Integer id = aboutApp.getId();
        if(id == null){
            aboutAppMapper.insert(aboutApp);
        }else{
            aboutAppMapper.updateByPrimaryKeyWithBLOBs(aboutApp);
        }
    }
}
