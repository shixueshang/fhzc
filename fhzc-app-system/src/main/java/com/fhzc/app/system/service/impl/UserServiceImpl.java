package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.model.UserExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
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
        if(!"".equals(name)){
            criteria.andRealnameEqualTo(name);
        }

        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<User> list = decryptUser(userMapper.selectByExampleWithRowbounds(example, rowBounds));

        return new PageableResult<User>(page, size, userMapper.countByExample(example), decryptUser(list));
    }

    @Override
    public List<User> findAllUsers() {
        UserExample example = new UserExample();
        return decryptUser(userMapper.selectByExample(example));
    }

    @Override
    public List<User> getUsersByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andRealnameEqualTo(name);
        return decryptUser(userMapper.selectByExample(example));
    }

    @Override
    public User getUserByIdentity(String identity) {


        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        try {
            identity = EncryptUtils.encryptToDES(identity.substring(identity.length() - 8), identity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        criteria.andPassportCodeEqualTo(identity);
        if (userMapper.countByExample(example) > 0) {
            return userMapper.selectByExample(example).get(0);
        }
        return null;

    }

    @Override
    public void addOrUpdateUser(User user) {
        Integer pid = user.getUid();
        String passport = user.getPassportCode();
        String key = passport.substring(passport.length() - 8);
        user.setSalt(key);
        try {
            user.setPassportCode(EncryptUtils.encryptToDES(key, user.getPassportCode()));
        if(user.getMobile() != null){
            user.setMobile(EncryptUtils.encryptToDES(key, user.getMobile()));
        }
        if(user.getEmail() != null){
            user.setEmail(EncryptUtils.encryptToDES(key, user.getEmail()));
        }
        } catch (Exception e) {
            logger.error("加密失败");
            e.printStackTrace();
        }
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
        return decryptUser(userMapper.selectByPrimaryKey(uid));
    }

    /**
     * 解密
     * @param list
     * @return
     */
    private List<User> decryptUser(List<User> list){
        List<User> result = new ArrayList<User>();
        for(User user : list){
            result.add(this.decryptUser(user));
        }
        return result;
    }

    /**
     * 单个用户解密
     * @param user
     * @return
     */
    private User decryptUser(User user){
        String key = user.getSalt();
        if(key == null){
            return user;
        }
        try {
            user.setPassportCode(EncryptUtils.decryptByDES(key, user.getPassportCode()));
            if(user.getMobile() != null){
                user.setMobile(EncryptUtils.decryptByDES(key, user.getMobile()));
            }
            if(user.getEmail() != null){
                user.setEmail(EncryptUtils.decryptByDES(key, user.getEmail()));
            }
            if(user.getPhone() != null){
                user.setPhone(EncryptUtils.decryptByDES(key, user.getPhone()));
            }
            if(user.getPhoneExt() != null){
                user.setPhoneExt(EncryptUtils.decryptByDES(key, user.getPhoneExt()));
            }
        } catch (Exception e) {
            logger.error("解密失败");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByMobile(String mobileNum) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginRoleEqualTo(Const.USER_ROLE.CUSTOMER);
        List<User> users = decryptUser(userMapper.selectByExample(example));
        for(User user : users){
            if(mobileNum.equals(user.getMobile())){
                return user;
            }
        }
        return null;
    }

}
