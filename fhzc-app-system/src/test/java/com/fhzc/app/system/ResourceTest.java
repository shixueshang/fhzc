package com.fhzc.app.system;

import com.fhzc.app.system.context.Base;
import com.fhzc.app.system.service.ResourceService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/25 12:27
 */
public class ResourceTest extends Base {

    @Resource
    private ResourceService resourceService;

    @Test
    public  void list(){
        List<Map<String, Object>> list = resourceService.findAllResource();
        logger.info(list);
    }
}
