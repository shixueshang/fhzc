package com.fhzc.app.api;

import com.fhzc.app.api.context.Base;
import com.fhzc.app.api.service.ScoreService;
import com.fhzc.app.dao.mybatis.model.ScoreHistory;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lihongde on 2016/9/5 19:18
 */
public class TestScore extends Base {

    @Resource
    private ScoreService scoreService;

    @org.junit.Test
    public void test(){
        List<ScoreHistory> list = scoreService.getAvailableScore(16, null, null);

        for(ScoreHistory history : list){
            logger.info("排序前" + history.getScore() + "@@@@@@" + history.getVaildTime());
        }

        Collections.sort(list, new Comparator<ScoreHistory>() {
            public int compare(ScoreHistory arg0, ScoreHistory arg1) {
                return arg0.getVaildTime().compareTo(arg1.getVaildTime());
            }
        });

        for(ScoreHistory history : list){
                logger.info("排序后" +history.getScore() + "@@@@@@" + history.getVaildTime());
        }

    }
}
