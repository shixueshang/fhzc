package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.Dictionary;

import java.util.List;

/**
 * Created by lihongde on 2016/7/22 14:30
 */
public interface DictionaryService {

    /**
     * 根据分类查询字典数据
     * @param cat
     * @return
     */
    List<Dictionary> findDicByType(String cat);

    /**
     * 添加或修改
     * @param dictionary
     */
    void addOrUpdate(Dictionary dictionary);

    void delete(Integer id);

    /**
     * 获取客户的level
     * @param levelValue
     * @return
     */
    Dictionary findCustomerLevel(String levelValue);
    
    /**
     * 获取客户的风险等级
     * @param levelValue
     * @return
     */
    Dictionary findRiskLevel(String levelValue);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Dictionary getDictionary(Integer id);
    
    /**
     * 判断数据字典的key或value是否存在
     * @param cat
     * @param type
     * @param kv
     * @return
     */
    boolean isKeyOrValueExists(String cat, String type, String kv);
    
    /**
     * 根据分类和value获取字典数据
     * @param cat
     * @return
     */
    List<Dictionary> findDicByTypeAndValue(String cat, String value);

}
