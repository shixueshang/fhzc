package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.UserExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.EncryptUtils;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.UserMapper;
import com.fhzc.app.system.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by Double_J on 2016/7/7 15:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String IMPORT_SQL = "";
	
    @Resource
    private ExcelImporter importer;

    @Resource
    private UserMapper userMapper;
    
    @Override
    public PageableResult<User> findPageUsers(String name, int page, int size) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andRealnameEqualTo(name);

        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<User> list = userMapper.selectByExampleWithRowbounds(example, rowBounds);

        return new PageableResult<User>(page, size, userMapper.countByExample(example), decryptUser(list));
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

    /**
     * 解密
     * @param list
     * @return
     */
    private List<User> decryptUser(List<User> list){
        List<User> result = new ArrayList<User>();
        for(User user : list){
            String key = user.getSalt();
            try {
                if(user.getPassportCode() != null){
                    user.setPassportCode(EncryptUtils.decryptByDES(key, user.getPassportCode()));
                }
                if(user.getMobile() != null){
                    user.setMobile(EncryptUtils.decryptByDES(key, user.getMobile()));
                }
                if(user.getEmail() != null){
                    user.setEmail(EncryptUtils.decryptByDES(key, user.getEmail()));
                }
                result.add(user);
            } catch (Exception e) {
                logger.error("解密失败");
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public User getUserByMobile(String mobileNum) {
        List<User> users = userMapper.selectUserByMobile(mobileNum);
        if (users != null && users.size() > 0){
            return users.get(0);
        }

        return null;
    }
}
