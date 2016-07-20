package com.fhzc.app.api.service;

import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by lihongde on 2016/7/7 15:34
 */
public interface ProductService {

    /**
     * 查询产品列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Product> findPageProducts(int page, int size);

    /**
     * 条件查询产品列表
     * @param level
     * @param risk
     * @return
     */
    PageableResult<Product> getProductList(int level, int risk);

    /**
     * 添加产品
     * @param product
     */
    void addOrUpdateProduct(Product product);


    /**
     * 获得产品信息
     * @param pid
     * @return
     */
    Product getProduct(Integer pid);

    /**
     * 查询产品名称是否存在
     * @param name
     * @return
     */
    boolean isNameExists(String name);
}
