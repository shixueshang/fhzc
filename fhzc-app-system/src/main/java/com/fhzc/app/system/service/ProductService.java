package com.fhzc.app.system.service;



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
     * 查询产品代码是否存在
     * @param code
     * @return
     */
    boolean isCodeExists(String code);
    
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
    PageableResult<ProductReservation> findPageProductReservations(ProductReserQuery query, int page, int size);

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

    /**
     * 根据产品类型获得产品
     * @param typeId
     * @return
     */
    List<Product> getProductByType(String typeId);

    /**
     * 根据产品id获得产品预约列表
     * @param pId
     * @return
     */
    List<ProductReservation> findOrdersByPid(Integer pId);
    
    /**
     * 根据产品id获得产品预约成功的产品
     * @param pId
     * @return
     */
    List<ProductReservation> findSuccessOrdersByPid(Integer pId,String result);
    
	/**
     * 查询产品
     * @param name
     * @return
     */
	 Product getProductById(Integer pId);
    
}
