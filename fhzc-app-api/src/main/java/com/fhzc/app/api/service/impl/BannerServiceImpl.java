package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.BannerService;
import com.fhzc.app.dao.mybatis.inter.BannerMapper;
import com.fhzc.app.dao.mybatis.model.Banner;
import com.fhzc.app.dao.mybatis.model.BannerExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by freeman on 16/8/2.
 */
@Service
public class BannerServiceImpl implements BannerService{

    @Resource
    BannerMapper bannerMapper;

    @Override
    public Banner getText1() {
        BannerExample example = new BannerExample();
        BannerExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("index_text");
        List<Banner> bannerList = bannerMapper.selectByExample(example);
        return bannerList.get(0);
    }

    @Override
    public Banner getPic1() {
        BannerExample example = new BannerExample();
        BannerExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("index_pic");
        List<Banner> bannerList = bannerMapper.selectByExample(example);
        return bannerList.get(0);
    }
}
