package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.UserExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.system.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by Double_J on 2016/7/7 15:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String IMPORT_SQL = "";
	
    @Resource
    private ExcelImporter importer;

    @Resource
    private UserMapper userMapper;
    
    @Override
    public PageableResult<User> findPageUsers(int page, int size) {
        UserExample example = new UserExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<User> list = userMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<User>(page, size, list.size(), list);
    }

    @Override
    public void addOrUpdateUser(User user) {
        Integer pid = user.getUid();
        if(pid == null){
            userMapper.insertSelective(user);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception {
       Map<String, Object> importResult = importer.setImportConfig(new ImportConfig() {
        @Override
        public String validation(Workbook xwb) {
            return null;
        }

        @Override
        public String getImportSQL() {
            return IMPORT_SQL;
        }

        @Override
        public List<Object[]> getImportData(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {
        	return data;
        }

        @Override
        public ImportCallBack getImportCallBack() {
            return new ImportCallBack() {
                @Override
                public void preOperation(SqlSessionTemplate sqlSessionTemplate, List<Object[]> data) {

                }

                @Override
                public void postOperation (SqlSessionTemplate sqlSessionTemplate, List < Object[]>data){

                }
            };

        }
        }).importExcelFile(multipartFile);

        return importResult;
    }

    @Override
    public User getUser(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

}
