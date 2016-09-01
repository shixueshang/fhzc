package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.ActivityApplyMapper;
import com.fhzc.app.dao.mybatis.inter.ActivityMapper;
import com.fhzc.app.dao.mybatis.model.Activity;
import com.fhzc.app.dao.mybatis.model.ActivityApply;
import com.fhzc.app.dao.mybatis.model.ActivityApplyExample;
import com.fhzc.app.dao.mybatis.model.ActivityApplyQuery;
import com.fhzc.app.dao.mybatis.model.ActivityExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.DateUtil;
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

    @Resource
    private ActivityApplyMapper activityApplyMapper;

    @Override
    public PageableResult<Activity> findPageActivies(List<Integer> departments, int page, int size) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        if(departments.size() > 0){
            criteria.andDepartmentIdIn(departments);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Activity> list = activityMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Activity>(page, size, activityMapper.countByExample(example), list);
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

    @Override
    public PageableResult<ActivityApply> findPageActivityApplies(ActivityApplyQuery query, int page, int size) {
        ActivityApplyExample example = new ActivityApplyExample();
        ActivityApplyExample.Criteria criteria = example.createCriteria();
        if(null != query.getActivityId()){
        	criteria.andActivityIdEqualTo(query.getActivityId());
        }
        if(query.getStartDate() != null && query.getEndDate() != null){
            criteria.andCtimeBetween(DateUtil.getStartTimeOfDate(query.getStartDate()), DateUtil.getEndTimeOfDate(query.getEndDate()));
        }
        if(query.getStartDate() != null && query.getEndDate() == null){
        	criteria.andCtimeGreaterThanOrEqualTo(DateUtil.getStartTimeOfDate(query.getStartDate()));
        }
        if(query.getStartDate() == null && query.getEndDate() != null){
        	criteria.andCtimeLessThanOrEqualTo(DateUtil.getEndTimeOfDate(query.getEndDate()));
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<ActivityApply> list = activityApplyMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<ActivityApply>(page, size, activityApplyMapper.countByExample(example), list);
    }

    @Override
    public List<Activity> getAllActivities() {
        ActivityExample example = new ActivityExample();
        return activityMapper.selectByExampleWithBLOBs(example);
    }

	@Override
	public List<ActivityApply> findSuccessOrdersById(Integer id, Integer result) {
		ActivityApplyExample example = new ActivityApplyExample();
		ActivityApplyExample.Criteria criteria = example.createCriteria();
	        criteria.andActivityIdEqualTo(id);
	        criteria.andResultEqualTo(result);
	        return activityApplyMapper.selectByExample(example);
	}

    @Override
    public Activity findActivityByName(String name) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        if(activityMapper.countByExample(example) > 0){
            return activityMapper.selectByExample(example).get(0);
        }
        return null;
    }

	@Override
	public List<Activity> getActivityByType(String typeId) {
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
	    criteria.andCidEqualTo(Integer.parseInt(typeId));
	    return activityMapper.selectByExample(example);
	}
}
