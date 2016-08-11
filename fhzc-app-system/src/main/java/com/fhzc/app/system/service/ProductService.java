package com.fhzc.app.system.service;



import com.fhzc.app.dao.mybatis.bo.ProductReservationBo;
import com.fhzc.app.dao.mybatis.model.Product;
import com.fhzc.app.dao.mybatis.model.ProductReserQuery;
import com.fhzc.app.dao.mybatis.model.ProductReservation;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    void addOrUpdateProduct(Product product);

    /**
     * 产品导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;

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
    
    /**
     * 查询产品名称是否存在
     * @param name
     * @return
     */
	int checkProductExists(String name);
	 
	/**
     * 查询产品
     * @param name
     * @return
     */
	 Product getProduct(String name);

    /**
     * 查询预约产品列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<ProductReservationBo> findPageProductReservations(ProductReserQuery query, int page, int size);

    /**
     * 添加产品预约
     * @param productReservation
     * @return
     */
    int addProductReservation(ProductReservation productReservation);

    /**
     * 获得所有产品
     * @return
     */
    List<Product> findAllProduct();
}
