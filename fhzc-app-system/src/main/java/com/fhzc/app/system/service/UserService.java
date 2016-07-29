package com.fhzc.app.system.service;



import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Double_J on 2016/7/7 15:34
 */
public interface UserService {

    /**
     * 查询用户列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<User> findPageUsers(int page, int size);

    /**
     * 添加用户
     * @param user
     */
    void addOrUpdateUser(User user);

    /**
     * 用户导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;

    /**
     * 获得用户信息
     * @param uid
     * @return
     */
    User getUser(Integer uid);
    
}
