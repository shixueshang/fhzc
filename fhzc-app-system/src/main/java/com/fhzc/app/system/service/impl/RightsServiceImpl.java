package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.RightsMapper;
import com.fhzc.app.dao.mybatis.inter.RightsReservationMapper;
import com.fhzc.app.dao.mybatis.model.Rights;
import com.fhzc.app.dao.mybatis.model.RightsExample;
import com.fhzc.app.dao.mybatis.model.RightsReserQuery;
import com.fhzc.app.dao.mybatis.model.RightsReservation;
import com.fhzc.app.dao.mybatis.model.RightsReservationExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.DateUtil;
import com.fhzc.app.system.service.RightsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/19 19:53
 */
@Service
public class RightsServiceImpl implements RightsService {

    @Resource
    private RightsMapper rightsMapper;

    @Resource
    private RightsReservationMapper rightsReservationMapper;

    @Override
    public PageableResult<Rights> findPageRights(int page, int size) {
        RightsExample example = new RightsExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Rights> list = rightsMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Rights>(page, size, rightsMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdateRights(Rights rights) {
        Integer id = rights.getId();
        if(id == null){
            rightsMapper.insert(rights);
        }else{
            rightsMapper.updateByPrimaryKey(rights);
        }
    }

    @Override
    public Rights getRights(Integer id) {
        return rightsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addRightsReservation(RightsReservation reservation) {
        return rightsReservationMapper.insertSelective(reservation);
    }

    @Override
    public PageableResult<RightsReservation> listRightReservations(int page, int size) {
        RightsReservationExample example = new RightsReservationExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<RightsReservation> rightsReservations = rightsReservationMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<RightsReservation>(page, size, rightsReservationMapper.countByExample(example), rightsReservations);
    }

    @Override
    public RightsReservation getReservationById(Integer id) {
        return rightsReservationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateReservation(RightsReservation reservation) {
        return rightsReservationMapper.updateByPrimaryKey(reservation);
    }

    @Override
    public List<Rights> getAllRights() {
        RightsExample example = new RightsExample();
        return rightsMapper.selectByExample(example);
    }

	@Override
	public List<RightsReservation> findSuccessOrdersById(Integer id, Integer status) {
		RightsReservationExample example = new RightsReservationExample();
		RightsReservationExample.Criteria criteria = example.createCriteria();
	        criteria.andRightsIdEqualTo(id);
	        criteria.andStatusEqualTo(status);
	        return rightsReservationMapper.selectByExample(example);
	}

	@Override
	public List<Rights> getRightsByType(String typeId) {
		RightsExample example = new RightsExample();
		RightsExample.Criteria criteria = example.createCriteria();
	    criteria.andCidEqualTo(Integer.parseInt(typeId));
	    return rightsMapper.selectByExample(example);
	}
	
    @Override
    public boolean isNumExists(String rightsNum) {
    	RightsExample example = new RightsExample();
    	RightsExample.Criteria criteria = example.createCriteria();
        criteria.andRightsNumEqualTo(rightsNum);
        if(rightsMapper.countByExample(example) > 0){
            return true;
        }
        return false;
    }

	@Override
	public Rights getRightsByName(String rightsName) {
		RightsExample example = new RightsExample();
		RightsExample.Criteria criteria = example.createCriteria();
	    criteria.andNameLike(rightsName);
	    if(rightsMapper.countByExample(example) > 0){
	    	 return rightsMapper.selectByExample(example).get(0);
	    }
	   return null;
	}
	
    @Override
    public PageableResult<RightsReservation> findPageRightsReservations(RightsReserQuery query, int page, int size) {
    	RightsReservationExample example = new RightsReservationExample();
    	RightsReservationExample.Criteria criteria = example.createCriteria();
        if(null != query.getRightsId()){
        	criteria.andRightsIdEqualTo(query.getRightsId());
        }
        if(query.getStartDate() != null && query.getEndDate() != null){
            criteria.andCtimeBetween(DateUtil.getStartTimeOfDate(query.getStartDate()), DateUtil.getEndTimeOfDate(query.getEndDate()));
        }
        if(query.getStartDate() != null && query.getEndDate() == null){
        	criteria.andCtimeGreaterThanOrEqualTo(DateUtil.getStartTimeOfDate(query.getStartDate()));
        }
        if(query.getStartDate() == null && query.getEndDate() != null){
        	criteria.andCtimeLessThanOrEqualTo(DateUtil.getEndTimeOfDate(query.getEndDate()));
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<RightsReservation> list = rightsReservationMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<RightsReservation>(page, size, rightsReservationMapper.countByExample(example), list);
    }
}
