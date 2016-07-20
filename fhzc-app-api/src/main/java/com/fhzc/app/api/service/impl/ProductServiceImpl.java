package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.dao.mybatis.inter.ProductMapper;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/7 15:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final String IMPORT_SQL = "";

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
    public PageableResult<Product> getProductList(int level,int risk) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo((byte) 1);
        List<Product> list = productMapper.selectByExample(example);
        return new PageableResult<Product>(0, 100, list.size(), list);
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
}
