package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by freeman on 16/7/19.
 */
@Controller
public class ProductApiController extends BaseController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductReservationService productReservationService;

    @Resource
    private FocusService focusService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private CustomerService customerService;

    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping(value = "/api/product", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productList() {
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, productService.findPageProducts(page, size, false));
    }

    /**
     * 精选基金列表
     *
     * @return
     */
    @RequestMapping(value = "/api/product/select", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productListSelect() {
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, productService.findPageProducts(page, size, true));
    }

    /**
     * 产品详情
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/product/detail", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productDetail(Integer productId) throws Exception {
        Product product = productService.getProduct(productId);
        Map result = ObjUtils.objectToMap(product);
        User user = super.getCurrentUser();
        Customer customer = customerService.getCustomerByUid(user.getUid());
        if (customer != null) {
            ProductReservation reservation = productReservationService.getUserProductReservation(customer.getCustomerId(), productId);
            if (reservation != null) {
                result.put("reservationId", reservation.getId());
                result.put("reservationResult", reservation.getResult());
            }
        }

        Focus focus = focusService.getFocusByCond(user.getUid(), productId, APIConstants.FocusType.Product);
        if (focus != null) {
            result.put("focusStatus", focus.getStatus());
        }

        result.put("riskLevel", super.getDicName(product.getRisk(), Const.DIC_CAT.RISK_LEVEL));
        result.put("customerLevel", super.getDicName(product.getLevel(), Const.DIC_CAT.CUSTOMER_LEVEL));
        result.put("issueType", super.getDicName(product.getIssueType(), Const.DIC_CAT.PRODUCT_ISSUE_TYPE));
        result.put("productTypeName", super.getDicName(Integer.parseInt(product.getProductType()), Const.DIC_CAT.PRODUCT_TYPE));
        if(product.getStatus() != null){
            result.put("productStatus", super.getDicName((Integer.parseInt(product.getStatus().toString())), Const.DIC_CAT.PRODUCT_STATUS));
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }

    /**
     * 资产配置建议
     *
     * @return
     */
    @RequestMapping(value = "/api/suggest/assets", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult suggestAssets() {
        List<AssetsRecommend>  list = productService.findAssetsRecommend();
        List<AssetsRecommend> result = new ArrayList<AssetsRecommend>();
        for(AssetsRecommend assetsRecommend : list){
            List<Dictionary> dicts =  dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE);
            for(Dictionary dictionary : dicts){
                if(assetsRecommend.getRecommendType().equals(dictionary.getValue())){
                    assetsRecommend.setTypeName(dictionary.getValue());
                    result.add(assetsRecommend);
                }
            }
        }
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, result);
    }


}
