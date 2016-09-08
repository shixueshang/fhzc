package com.fhzc.app.api.service.impl;

import com.fhzc.app.api.service.ProductService;
import com.fhzc.app.dao.mybatis.inter.AssetsHistoryMapper;
import com.fhzc.app.dao.mybatis.inter.AssetsRecommendMapper;
import com.fhzc.app.dao.mybatis.inter.ProductMapper;
import com.fhzc.app.dao.mybatis.model.AssetsRecommend;
import com.fhzc.app.dao.mybatis.model.AssetsRecommendExample;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductExample;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongde on 2016/7/7 15:43
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private AssetsRecommendMapper assetsRecommendMapper;


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
        List<Integer> status = new ArrayList<Integer>();
        status.add(Const.PRODUCT_STATUS.PREHEAT);
        status.add(Const.PRODUCT_STATUS.COLLECTING);
        criteria.andStatusIn(status);
        if(productMapper.countByExample(example) > 0) {
            return productMapper.selectByExampleWithBLOBs(example);
        }else{
            return null;
        }
    }

    @Override
    public Product getProduct(Integer pid) {
        return productMapper.selectByPrimaryKey(pid);
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

    @Override
    public List<AssetsRecommend> findAssetsRecommend() {
        AssetsRecommendExample example = new AssetsRecommendExample();
        AssetsRecommendExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Const.Data_Status.DATA_NORMAL);
        return assetsRecommendMapper.selectByExample(example);
    }
}
