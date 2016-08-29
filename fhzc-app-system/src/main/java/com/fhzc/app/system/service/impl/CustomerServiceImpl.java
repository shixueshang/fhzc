package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.*;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.service.CustomerService;
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
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String IMPORT_SQL = "";

    @Resource
    private ExcelImporter importer;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private PlannerCustomerMapper plannerCustomerMapper;

    @Resource
    private CustomerOrganMapper customerOrganMapper;


    @Override
    public PageableResult<Customer> findPageCustomers( int page, int size) {

        CustomerExample example = new CustomerExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Customer> list = customerMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Customer>(page, size, customerMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdateCustomer(Customer customer) {
        Integer pid = customer.getCustomerId();
        if(pid == null){
            customerMapper.insertSelective(customer);
        }else{
            customerMapper.updateByPrimaryKeySelective(customer);
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
    public Customer getCustomer(Integer pid) {
        return customerMapper.selectByPrimaryKey(pid);
    }

    @Override
    public boolean isNameExists(String name) {
//        CustomerExample example = new CustomerExample();
//        CustomerExample.Criteria criteria = example.createCriteria();
//        criteria.andNameEqualTo(name);
//        if(customerMapper.countByExample(example) > 0){
//            return true;
//        }
        return false;
    }


    /**
     * 获得客户
     * @param uId
     * @return
     */
    @Override
    public Customer getCustomerByUid(Integer uId, String customerType) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uId);
        if(customerType != null){
            criteria.andCustomerTypeEqualTo(customerType);
        }
        if(customerMapper.countByExample(example) > 0){
            return customerMapper.selectByExample(example).get(0);
        }
        return null;
    }

    @Override
    public PlannerCustomer getPlannerByCustomerId(Integer customerId, Integer isMain) {
        PlannerCustomerExample example = new PlannerCustomerExample();
        PlannerCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        if(isMain != null){
            criteria.andIsMainEqualTo(Byte.valueOf(isMain.toString()));
        }
        if(plannerCustomerMapper.countByExample(example) > 0){
            return plannerCustomerMapper.selectByExample(example).get(0);
        }
        return null;
    }


    @Override
    public List<CustomerOrgan> findOrganCustomer(Integer customerId) {
        CustomerOrganExample example = new CustomerOrganExample();
        CustomerOrganExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        return customerOrganMapper.selectByExample(example);
    }

    @Override
    public void addOrUpdateOrganCustomer(CustomerOrgan customerOrgan) {
        Integer id = customerOrgan.getId();
        if(id == null){
            customerOrganMapper.insert(customerOrgan);
        }else{
            customerOrganMapper.updateByPrimaryKey(customerOrgan);
        }
    }

    @Override
    public CustomerOrgan getRightsEnjoyPerson(Integer id) {
        return customerOrganMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        customerOrganMapper.deleteByPrimaryKey(id);
    }
    
	@Override
	public List<Customer> findAllCustomer() {
		CustomerExample example = new CustomerExample();
	    return customerMapper.selectByExample(example);
	}

    @Override
    public void updatePlannerCustomer(PlannerCustomer pc) {
       plannerCustomerMapper.updateByPrimaryKey(pc);
    }
}
