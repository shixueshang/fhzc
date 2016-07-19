package com.fhzc.app.system.service.impl;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.commons.util.excel.ExcelImporter;
import com.fhzc.app.system.commons.util.excel.ImportCallBack;
import com.fhzc.app.system.commons.util.excel.ImportConfig;
import com.fhzc.app.system.mybatis.inter.ProductMapper;
import com.fhzc.app.system.mybatis.model.Product;
import com.fhzc.app.system.mybatis.model.ProductExample;
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

    private static final String IMPORT_SQL = "";

    @Resource
    private ExcelImporter importer;

    @Resource
    private ProductMapper productMapper;


    @Override
    public PageableResult<Product> findPageProducts(int page, int size) {
        ProductExample example = new ProductExample();
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Product> list = productMapper.selectByExampleWithRowbounds(example, rowBounds);
        return new PageableResult<Product>(page, size, list.size(), list);
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
}
