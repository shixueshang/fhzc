package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Banner;

import java.util.List;

/**
 * Created by freeman on 16/8/2.
 */
public interface BannerService {
    List<Banner> get(String type);
}
