package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.DictionaryMapper;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.DictionaryExample;
import com.fhzc.app.system.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lihongde on 2016/7/22 14:47
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> findDicByType(String cat) {
        DictionaryExample example = new DictionaryExample();
        DictionaryExample.Criteria criteria = example.createCriteria();
        criteria.andCatEqualTo(cat);
        return dictionaryMapper.selectByExample(example);
    }

    @Override
    public void addOrUpdate(Dictionary dictionary) {
        Integer id = dictionary.getId();
        if(id == null){
            dictionary.setDateCreated(new Date());
            dictionary.setLastUpdated(new Date());
            dictionaryMapper.insert(dictionary);
        }else{
            dictionary.setLastUpdated(new Date());
            dictionaryMapper.updateByPrimaryKey(dictionary);
        }
    }

    @Override
    public void delete(Integer id) {
        dictionaryMapper.deleteByPrimaryKey(id);
    }
}
