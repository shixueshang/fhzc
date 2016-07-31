package com.fhzc.app.system.service;



import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.vo.CustomerVo;
import org.springframework.web.multipart.MultipartFile;

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
     * 获得产品信息
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
     * @return
     */
	 Customer getCustomerByUid(Integer uId);

    /**
     * 通过移动号码获取客户信息
     * @param mobileNum
     * @return
     */
    CustomerVo getCustomerInfoByMobile(String mobileNum);
}
