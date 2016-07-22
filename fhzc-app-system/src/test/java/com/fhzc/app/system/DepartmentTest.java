package com.fhzc.app.system;

import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.context.Base;
import com.fhzc.app.system.service.DepartmentService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/21 19:03
 */
public class DepartmentTest extends Base {

    @Resource
    private DepartmentService departmentService;

    @org.junit.Test
    public void list(){
       PageableResult<Map<String, Object>> pageDepts = departmentService.findPageDepts(0, 10);

        for(Map<String, Object> map : pageDepts.getItems()){
            logger.info(map.get("id") + " : " + map.get("name"));
        }
    }
}
