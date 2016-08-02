package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.inter.PlannerCustomerMapper;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.CustomerMapper;
import com.fhzc.app.system.service.*;
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
     * 获得机构id
     * @param uId
     * @return
     */
    @Override
    public Customer getCustomerByUid(Integer uId) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uId);
        return customerMapper.selectByExample(example).get(0);
    }

    @Override
    public PlannerCustomer getPlannerByCustomerId(Integer customerId) {
        PlannerCustomerExample example = new PlannerCustomerExample();
        PlannerCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        criteria.andIsMainEqualTo(Byte.valueOf(Const.YES_OR_NO.YES.toString()));
        return plannerCustomerMapper.selectByExample(example).get(0);
    }
}
