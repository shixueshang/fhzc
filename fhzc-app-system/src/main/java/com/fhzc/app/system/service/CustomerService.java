package com.fhzc.app.system.service;

import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
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
     * 获得客户的理财师
     * @param customerId
     * @param isMain
     * @return
     */
    PlannerCustomer getPlannerByCustomerId(Integer customerId, Integer isMain);

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
    
    /**
     * 获取所有客户
     */
	List<Customer> findAllCustomer();

    /**
     * 更新客户理财师关系
     * @param pc
     * @return
     */
    void updatePlannerCustomer(PlannerCustomer pc);
}
