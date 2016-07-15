package com.fhzc.app.system.service;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.mybatis.model.Product;
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
     * 添加产品
     * @param product
     */
    void addProduct(Product product);

    /**
     * 产品导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;
}
