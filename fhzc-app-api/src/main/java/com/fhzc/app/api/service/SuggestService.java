package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Suggest;

/**
 * Created by freeman on 16/8/17.
 */
public interface SuggestService {
    int add(Suggest suggest);
}
