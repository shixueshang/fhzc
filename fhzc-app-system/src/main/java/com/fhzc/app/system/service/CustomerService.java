package com.fhzc.app.system.service;



import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.vo.CustomerVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Double_J on 2016/7/7 15:34
 */
public interface CustomerService {

    /**
     * 根据姓名或者证件号查询客户列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Customer> findPageCustomers(int page, int size);

    /**
     * 添加
     * @param customer
     */
    void addOrUpdateCustomer(Customer customer);

    /**
     * 导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;

    /**
     * 获得客户信息
     * @param customerId
     * @return
     */
    Customer getCustomer(Integer customerId);

    /**
     * 查询客户名称是否存在
     * @param name
     * @return
     */
    boolean isNameExists(String name);
    
	 /**
     * 通过uid查询客户
     * @param uId
      * @param customerType
     * @return
     */
	 Customer getCustomerByUid(Integer uId, String customerType);

    /**
     * 获得客户的主理财师
     * @param customerId
     * @return
     */
    PlannerCustomer getPlannerByCustomerId(Integer customerId);

    /**
     * 通过移动号码获取客户信息
     * @param mobileNum
     * @return
     */
    CustomerVo getCustomerInfoByMobile(String mobileNum);

    /**
     * 根据客户id查询客户权益享用人
     * @return
     */
    List<CustomerOrgan> findOrganCustomer(Integer customerId);

    /**
     * 添加或修改机构客户权益享用人
     * @param customerOrgan
     */
    void addOrUpdateOrganCustomer(CustomerOrgan customerOrgan);

    /**
     * 根据id获得权益享用人
     * @param id
     * @return
     */
    CustomerOrgan getRightsEnjoyPerson(Integer id);

    /**
     * 删除权益享用人
     * @param id
     */
    void delete(Integer id);
}
