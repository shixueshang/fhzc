package com.fhzc.app.system.context;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/** 
 * @author lihongde
 * @date 2016-7-7 下午10:27:35
 */
@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
		"classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional(transactionManager = "transactionManager")
public class Base extends Assert{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

    @Resource
    protected JdbcTemplate jdbcTemplate;
	
}
