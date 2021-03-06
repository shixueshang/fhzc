package com.fhzc.app.system.service;



import com.fhzc.app.dao.mybatis.model.Contract;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/7 15:34
 */
public interface ContractService {

    /**
     * 查询列表
     * @param page
     * @param size
     * @return
     */
    PageableResult<Contract> findPageContracts(Integer productId, List<Integer> plannerIds, int page, int size);

    /**
     * 添加
     * @param contract
     */
    void addOrUpdateContract(Contract contract);

    /**
     * 导入
     * @param multipartFile
     * @return
     * @throws Exception
     */
    Map<String, Object> importExcelFile(MultipartFile multipartFile) throws Exception;

    /**
     * 获得信息
     * @param id period
     * @return
     */
    Contract getContract(Integer id,String period);

    /**
     * 查询名称是否存在
     * @param name
     * @return
     */
    boolean isNameExists(String name);
    
    /**
     * 获得所有合同日报
     * @return
     */
    List<Contract> findAllContract();
    
    /**
     * 根据客户和产品获取
     * @param customerId
     * @param productId
     * @return
     */
    List<Contract> getContract(Integer customerId,Integer productId);
}
