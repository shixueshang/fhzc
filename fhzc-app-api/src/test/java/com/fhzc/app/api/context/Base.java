package com.fhzc.app.api.context;

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

@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)

@Transactional(transactionManager = "transactionManager")
public class Base extends Assert{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

    @Resource
    protected JdbcTemplate jdbcTemplate;
	
}
