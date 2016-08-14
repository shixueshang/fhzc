package com.fhzc.app.api.service;

/**
 * Created by freeman on 16/8/14.
 */
public interface AchievementService {
    Integer getMonthSale(Integer plannerId, Integer year,Integer month);
    Integer getYearSale(Integer plannerId, Integer year);
}
