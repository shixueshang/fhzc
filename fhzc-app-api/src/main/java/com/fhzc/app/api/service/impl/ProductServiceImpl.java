package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.dao.mybatis.inter.ProductMapper;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/7/7 15:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;


    @Override
    public PageableResult<Product> findPageProducts(int page, int size, boolean isRecommend) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        example.setOrderByClause("`display_order` desc");
        if(isRecommend) {
            criteria.andIsRecommendEqualTo(Const.YES_OR_NO.YES);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * size, size);
        List<Product> list = productMapper.selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
        return new PageableResult<Product>(page, size, productMapper.countByExample(example), list);
    }

    @Override
    public List<Product> getRecommendProductList() {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
        criteria.andIsRecommendEqualTo(Const.YES_OR_NO.YES);
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

        criteria.andIsDisplayEqualTo(Const.YES_OR_NO.YES);
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
