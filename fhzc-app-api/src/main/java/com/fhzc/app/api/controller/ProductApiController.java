package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.*;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.api.tools.ObjUtils;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
        PageableResult<Product> productList = productService.getProductList(0, 0, false);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, productList);
    }

    /**
     * 精选基金列表
     *
     * @return
     */
    @RequestMapping(value = "/api/product/select", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult productListSelect() {
        PageableResult<Product> productList = productService.getProductList(0, 0, true);

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, productList);
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

        result.put("riskLevel", super.getLevelName(product.getRisk()));
        result.put("customerLevel", super.getLevelName(product.getLevel()));
        result.put("issueType", super.getProductIssueType(product.getIssueType()));
        result.put("productStatus", super.getProductStatusName(Integer.valueOf(product.getStatus())));

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
        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, dictionaryService.findDicByType(Const.DIC_CAT.ASSET_CONFIG));
    }


}
