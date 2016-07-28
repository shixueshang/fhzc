package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ActivityService;
import com.fhzc.app.dao.mybatis.inter.ActivityMapper;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/19 14:35
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public PageableResult<Activity> findPageActivies(int page, int size) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Activity> list = activityMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Activity>(page, size, list.size(), list);
    }
    
    @Override
    public List<Activity> getRecommendActivityList(){
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        criteria.andIsRecommendEqualTo(Const.YES_OR_NO.YES);
        return activityMapper.selectByExample(example);
    }

    @Override
    public Activity getActivity(Integer id) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        if(activityMapper.countByExample(example) > 0){
            return activityMapper.selectByPrimaryKey(id);
        }else{
            return null;
        }

    }


}
