package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Double_J on 2016/7/7 15:34
 */
public interface UserService {

    /**
     * 根据用户名查询用户列表
     *
     * @param name
     * @param page
     * @param size
     * @return
     */
    PageableResult<User> findPageUsers(String name, String mobile, int page, int size);

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

    /**
     * 获得用户信息
     * @param mobileNum
     * @return
     */
    User getUserByMobile(String mobileNum);

    /**
     * 获得所有用户信息
     * @return
     */
	List<User> findAllUsers();

    /**
     * 根据真实姓名获得用户
     * @param name
     * @return
     */
    List<User> getUsersByName(String name);

    /**
     * 根据加密的证件号获取用户
     * @param identity
     * @return
     */
    User getUserByIdentity(String identity);

}
