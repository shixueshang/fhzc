package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Focus;

import java.util.List;

/**
 * Created by freeman on 16/7/28.
 */
public interface FocusService {
    void addOrUpdateFocus(Focus focus);

    Focus getFocus(Integer id);

    Focus getFocusByCond(Integer uid, Integer fid, String ftype);

    List<Focus> getFocusList(Integer userId);
}
