package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Banner;
import com.fhzc.app.dao.mybatis.page.PageableResult;

/**
 * Created by lihongde on 2016/8/12 15:33
 */
public interface BannerService {

    /**
     * @param page
     * @param size
     * @return
     */
    PageableResult<Banner> findPageBanners(int page, int size);

    void addOrUpdateBanner(Banner banner);

    Banner getBanner(Integer id);

    void delete(Integer id);
}
