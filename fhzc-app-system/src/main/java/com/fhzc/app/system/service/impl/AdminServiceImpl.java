package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.AdminMapper;
import com.fhzc.app.dao.mybatis.model.Admin;
import com.fhzc.app.dao.mybatis.model.AdminExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.service.AdminService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/6 20:06
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByLoginName(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginEqualTo(username);
        if(adminMapper.countByExample(example) > 0){
           return adminMapper.selectByExample(example).get(0);
        }
        return  null;
    }

    @Override
    public Admin findAdminById(Integer uid) {
        return null;
    }

    @Override
    public PageableResult<Admin> findPageAdmins(int page, int size) {
        AdminExample example = new AdminExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Admin> list = adminMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Admin>(page, size, list.size(), list);
    }

    @Override
    public void updatePwdById(Integer uid, String pwd) {

    }
}
