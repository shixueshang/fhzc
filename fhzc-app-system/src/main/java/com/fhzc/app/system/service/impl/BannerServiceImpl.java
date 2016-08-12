package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.BannerMapper;
import com.fhzc.app.dao.mybatis.model.Banner;
import com.fhzc.app.dao.mybatis.model.BannerExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/12 15:34
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public PageableResult<Banner> findPageBanners(int page, int size) {
        BannerExample example = new BannerExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Banner> list = bannerMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Banner>(page, size, bannerMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdateBanner(Banner banner) {
        Integer id = banner.getId();
        if(id == null){
            bannerMapper.insert(banner);
        }else{
            bannerMapper.updateByPrimaryKey(banner);
        }
    }

    @Override
    public Banner getBanner(Integer id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        bannerMapper.deleteByPrimaryKey(id);
    }
}
