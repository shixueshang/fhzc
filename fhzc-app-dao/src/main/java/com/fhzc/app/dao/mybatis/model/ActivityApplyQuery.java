package com.fhzc.app.dao.mybatis.model;

import java.util.Date;

/**
 * Created by menghq on 2016/7/26.
 */
public class ActivityApplyQuery {

    private Integer activityId;

    private String activityName;

    private Date startDate;

    private Date endDate;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
