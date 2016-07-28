package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.FocusService;
import com.fhzc.app.dao.mybatis.inter.FocusMapper;
import com.fhzc.app.dao.mybatis.model.Focus;
import com.fhzc.app.dao.mybatis.model.FocusExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by freeman on 16/7/28.
 */
@Service
public class FocusServiceImpl implements FocusService{

    @Resource
    private FocusMapper focusMapper;

    public void addOrUpdateFocus(Focus focus){
        Integer id= focus.getId();
        if(id == null){
            focusMapper.insert(focus);
        }else{
            focusMapper.updateByPrimaryKey(focus);
        }
    }

    @Override
    public Focus getFocus(Integer id){
        return focusMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Focus> getFocusByCond(Integer uid, Integer fid, String ftype){
        FocusExample example = new FocusExample();
        FocusExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andFidEqualTo(fid);
        criteria.andFtypeEqualTo(ftype);
        return focusMapper.selectByExample(example);
    }

    @Override
    public List<Focus> getFocusList(Integer customer_id){
        FocusExample example = new FocusExample();
        FocusExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(customer_id);
        return focusMapper.selectByExample(example);
    }
}
