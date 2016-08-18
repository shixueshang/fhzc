package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AdminRoleMapper;
import com.fhzc.app.dao.mybatis.model.AdminRole;
import com.fhzc.app.dao.mybatis.model.AdminRoleExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.service.AdminRoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:55
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Override
    public PageableResult<AdminRole> findPageRole(int page, int size) {
        AdminRoleExample example = new AdminRoleExample();
        AdminRoleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Const.Data_Status.DATA_NORMAL);
        RowBounds rowBounds = new RowBounds((page - 1 ) * size, size);
        List<AdminRole> list = adminRoleMapper.selectByExample(example);
        return new PageableResult<AdminRole>(page, size, adminRoleMapper.countByExample(example), list);
    }

    @Override
    public List<AdminRole> getAllRoles() {
        AdminRoleExample example  = new AdminRoleExample();
        AdminRoleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Const.Data_Status.DATA_NORMAL);
        return adminRoleMapper.selectByExample(example);
    }

    @Override
    public AdminRole findRoleById(Integer id) {
        return adminRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addOrUpdateRole(AdminRole role) {
        Integer id = role.getRoleId();
        if(id == null){
            adminRoleMapper.insert(role);
        }else{
            adminRoleMapper.updateByPrimaryKey(role);
        }
    }

    @Override
    public void delete(Integer id) {
        AdminRole role = this.findRoleById(id);
        role.setStatus(Const.Data_Status.DATA_DELETE);
        adminRoleMapper.updateByPrimaryKey(role);
    }

	@Override
	public boolean isRoleNameExists(String roleName) {
	     AdminRoleExample example = new AdminRoleExample();
	     AdminRoleExample.Criteria criteria = example.createCriteria();
	        criteria.andRoleNameEqualTo(roleName);
	        if(adminRoleMapper.countByExample(example) > 0){
	            return true;
	        }
	        return false;
	}
}
