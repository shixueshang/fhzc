package com.fhzc.app.system;

import com.fhzc.app.system.context.Base;
import com.fhzc.app.system.service.ScoreService;
import org.junit.*;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by lihongde on 2016/9/5 18:00
 */
public class ScoreTest extends Base {

    @Resource
    private ScoreService scoreService;

    @org.junit.Test
    public void test(){
        Integer uid = 16;

        Integer total = scoreService.getTotalScore(uid);

        Integer willExpire = scoreService.getWillExpiredScore(uid);

        logger.info("userId="+ uid +" 的即将过期积分：" + willExpire);
    }

    @Test
    public void list(){
        System.out.println("userId="+ 17 +" 的可用积分：" + scoreService.getAvailableScore(17));
    }
}
