package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.dao.mybatis.inter.ProductMapper;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<Product> list = productMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Product>(page, size, list.size(), list);
    }

    @Override
    public PageableResult<Product> getProductList(int level,int risk,boolean is_recommend) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo((byte) 1);
        if(is_recommend) {
            criteria.andIsRecommendEqualTo((byte) 1);
        }
        if(productMapper.countByExample(example) > 0) {
            List<Product> list = productMapper.selectByExampleWithBLOBs(example);
            return new PageableResult<Product>(0, 100, list.size(), list);
        }else{
            return null;
        }
    }

    @Override
    public List<Product> getRecommendProductList() {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo((byte) 1);
        criteria.andIsRecommendEqualTo((byte) 1);
        if(productMapper.countByExample(example) > 0) {
            return productMapper.selectByExampleWithBLOBs(example);
        }else{
            return null;
        }
    }

    @Override
    public Product getProduct(Integer pid) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        if(productMapper.countByExample(example) > 0){
            return productMapper.selectByPrimaryKey(pid);
        }else{
            return null;
        }

    }

    @Override
    public List<Product> getNewProductList() {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("ctime desc");

        criteria.andIsDisplayEqualTo((byte) 1);
        if(productMapper.countByExample(example) > 0) {
            return productMapper.selectByExampleWithBLOBs(example);
        }else{
            return null;
        }
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
