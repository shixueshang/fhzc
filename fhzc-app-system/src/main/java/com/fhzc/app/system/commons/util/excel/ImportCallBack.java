package com.fhzc.app.system.commons.util.excel;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 导入回调借口 <br />
 * 注：目前由于@Transactional并不起作用，所以选择HibernateDao进行事务处理，后期调整
 * 
 * @author Guoyan
 */
@Transactional
public interface ImportCallBack {

	/**
	 * 导入之前需要进行的数据库操作
	 * 
	 * @param sqlSessionTemplate
	 *            {@link org.mybatis.spring.SqlSessionTemplate}
	 * @param data
	 *            原始Excel中的数据，按行进行分割
     * @return
	 */
	void preOperation(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data);

	/**
	 * 导入之后需要进行的数据库操作
	 * 
	 * @param sqlSessionTemplate
	 *            {@link SqlSessionTemplate}
	 * @param data
	 *            即将导入的数据，即进行转换后的数据
	 */
	void postOperation(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data);
}
