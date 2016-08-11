package com.fhzc.app.system.service.impl;

import com.fhzc.app.dao.mybatis.bo.ProductReservationBo;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.dao.mybatis.inter.ProductMapper;
import com.fhzc.app.dao.mybatis.inter.ProductReservationMapper;
import com.fhzc.app.system.service.ProductService;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lihongde on 2016/7/7 15:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final String IMPORT_SQL = "INSERT INTO product(code, name, found_day, expiry_day, issue_type, product_type, renew_deadline, dividend_day, fund_management_fee, fund_subscription_fee, fund_manager,custodian, invest_term_min, invest_term_max) " +
            "VALUES(?,?,?,?,?,?,?,?, 0, 0, 0, 'qq', 0, 0)";

    @Resource
    private ExcelImporter importer;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductReservationMapper productReservationMapper;

    @Override
    public PageableResult<Product> findPageProducts(int page, int size) {
        ProductExample example = new ProductExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Product> list = productMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Product>(page, size, productMapper.countByExample(example), list);
    }

    @Override
    public void addOrUpdateProduct(Product product) {
        Integer pid = product.getPid();
        if(pid == null){
            productMapper.insertSelective(product);
        }else{
            productMapper.updateByPrimaryKeySelective(product);
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
    public Product getProduct(Integer pid) {
        return productMapper.selectByPrimaryKey(pid);
    }

    @Override
    public boolean isNameExists(String name) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        if(productMapper.countByExample(example) > 0){
            return true;
        }
        return false;
    }
    @Override
    public int checkProductExists(String name) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        if(productMapper.countByExample(example) > 0){
            return productMapper.selectByExample(example).get(0).getPid();
        }
        return -1;
    }
    
    /**
     * 获得机构id
     * @param name
     * @return
     */
    public Product getProduct(String name){
    	return productMapper.selectByName(name);
    }

    /**
     * 查询预约产品列表
     * @param page
     * @param size
     * @return
     */
    public PageableResult<ProductReservationBo> findPageProductReservations(ProductReserQuery query, int page, int size) {
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<ProductReservationBo> list = productReservationMapper.selectReservations(query, rowBounds);
        return new PageableResult<ProductReservationBo>(page, size, productReservationMapper.countReservations(query), list);
    }

    @Override
    public int addProductReservation(ProductReservation productReservation) {
        return productReservationMapper.insert(productReservation);
    }

    @Override
    public List<Product> findAllProduct() {
        ProductExample example = new ProductExample();
        return productMapper.selectByExample(example);
    }
}
