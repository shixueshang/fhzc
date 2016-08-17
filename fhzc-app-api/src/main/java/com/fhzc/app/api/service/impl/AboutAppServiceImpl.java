package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.AboutAppService;
import com.fhzc.app.dao.mybatis.inter.AboutAppMapper;
import com.fhzc.app.dao.mybatis.model.AboutApp;
import com.fhzc.app.dao.mybatis.model.AboutAppExample;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by freeman on 16/8/17.
 */
@Service
public class AboutAppServiceImpl implements AboutAppService{

    @Resource
    AboutAppMapper aboutAppMapper;

    @Override
    public AboutApp getLatestApp() {
        AboutAppExample example = new AboutAppExample();
        AboutAppExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id desc");
        criteria.andTypeEqualTo(Const.About_App.ABOUT_APP);
        if (aboutAppMapper.countByExample(example) > 0) {
            return aboutAppMapper.selectByExampleWithBLOBs(example).get(0);
        } else {
            return null;
        }
    }

    @Override
    public AboutApp getAppByVersion(String version) {
        AboutAppExample example = new AboutAppExample();
        AboutAppExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(Const.About_App.ABOUT_APP);
        criteria.andVersionEqualTo(version);
        if (aboutAppMapper.countByExample(example) > 0) {
            return aboutAppMapper.selectByExampleWithBLOBs(example).get(0);
        } else {
            return null;
        }
    }
}
