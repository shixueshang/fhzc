package com.fhzc.app.system.service;

import com.fhzc.app.system.commons.page.PageableResult;
import com.fhzc.app.system.mybatis.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by lihongde on 2016/7/7 15:34
 */
public interface ProductService {

    PageableResult<Product> findPageProducts(int page, int size);

    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;
}
