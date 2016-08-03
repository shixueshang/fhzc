package com.fhzc.app.api.service;


import com.fhzc.app.dao.mybatis.model.AssetsHistory;

import java.util.List;

/**
 * Created by freeman on 16/8/3.
 */
public interface AssetsService {
    List<AssetsHistory> getHistory(Integer customer_id);
}
