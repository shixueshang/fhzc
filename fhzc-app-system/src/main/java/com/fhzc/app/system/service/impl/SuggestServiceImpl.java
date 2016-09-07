package com.fhzc.app.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.fhzc.app.dao.mybatis.inter.SuggestMapper;
import com.fhzc.app.dao.mybatis.model.Suggest;
import com.fhzc.app.dao.mybatis.model.SuggestExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.SuggestService;

/**
 * Created by Double_J on 2016/9/7
 */
@Service
public class SuggestServiceImpl implements SuggestService {
	
	@Resource
	private SuggestMapper suggestMapper;
	
	@Override
	public PageableResult<Suggest> findPageSuggests(int page, int size) {
		SuggestExample example = new SuggestExample();
		RowBounds rowBounds = new RowBounds((page - 1) * size, size);
		List<Suggest> list = suggestMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
		return new PageableResult<Suggest>(page, size, suggestMapper.countByExample(example), list);
	}

}
