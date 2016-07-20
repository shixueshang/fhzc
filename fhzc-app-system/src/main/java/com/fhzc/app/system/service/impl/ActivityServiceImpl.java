package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ActivityMapper;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.ActivityService;
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
    public void addOrUpdateActivity(Activity activity) {
        Integer id = activity.getId();
        if(id == null){
            activityMapper.insert(activity);
        }else{
            activityMapper.updateByPrimaryKey(activity);
        }
    }

    @Override
    public Activity getActivity(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }


}
