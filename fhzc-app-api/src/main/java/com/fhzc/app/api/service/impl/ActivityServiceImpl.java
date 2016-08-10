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
import java.util.ArrayList;
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
        List<Activity> result = this.setActivityListStatus(list);
        return new PageableResult<Activity>(page, size, list.size(), result);
    }
    
    @Override
    public List<Activity> getRecommendActivityList(){
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("ctime desc");
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        criteria.andIsRecommendEqualTo(Const.YES_OR_NO.YES);
        if (activityMapper.countByExample(example) > 0) {
            List<Activity> activityList= activityMapper.selectByExample(example);
            return this.setActivityListStatus(activityList);
        }
        return null;
    }

    @Override
    public Activity getActivity(Integer id) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        if(activityMapper.countByExample(example) > 0){
            Activity activity = activityMapper.selectByPrimaryKey(id);
            activity.setStatus(this.getActivityStatus(activity));
            return activity;
        }else{
            return null;
        }
    }

    @Override
    public List<Activity> setActivityListStatus(List<Activity> list){
        List<Activity> result = new ArrayList<Activity>();
        for(Activity activity : list){
            Integer status = this.getActivityStatus(activity);
            activity.setStatus(status);
            result.add(activity);
        }
        return result;
    }

    @Override
    public Integer getActivityStatus(Activity activity){
        if(System.currentTimeMillis() < activity.getApplyBeginTime().getTime()){
            return Const.ACTIVITY_STATUS.WILL;
        }else if(System.currentTimeMillis() > activity.getApplyBeginTime().getTime() && System.currentTimeMillis() < activity.getApplyEndTime().getTime() ){
            return Const.ACTIVITY_STATUS.GOING;
        }else if(System.currentTimeMillis() > activity.getApplyEndTime().getTime()){
            return Const.ACTIVITY_STATUS.APP_OVER;
        }else if(System.currentTimeMillis() > activity.getEndTime().getTime()){
            return Const.ACTIVITY_STATUS.ACT_OVER;
        }else{
            return Const.ACTIVITY_STATUS.WILL;
        }
    }

}
