package com.fhzc.app.system.controller.business;

import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihongde on 2016/8/4 13:27
 */
@Controller
@RequestMapping(value = "/business/assets")
public class AssetsController extends BaseController {

    @Resource
    private AssetsService assetsService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private PlannerService plannerService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private ProductService productService;


    /**
     * 订单列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listOrder(){
        ModelAndView mav = new ModelAndView("/business/assets/list");
        PageableResult<AssetsHistory> pageableResult = assetsService.findPageAssets(page, size);

        List<AssetsHistory> assets = pageableResult.getItems();
        for(AssetsHistory assetsHistory : assets){
            Customer customer = customerService.getCustomer(assetsHistory.getCustomerId());
            assetsHistory.setCustomerNum(customer.getCbId());
            Planner planner = plannerService.getPlanner(assetsHistory.getPlannerId());
            if(planner != null){
                assetsHistory.setPlanner(userService.getUser(planner.getUid()).getRealname());
            }
            Product product = productService.getProduct(assetsHistory.getProductId());
            assetsHistory.setProductName(product.getName());
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("assets", assets);
        mav.addObject("assetsStatus", dictionaryService.findDicByType(Const.DIC_CAT.ASSETS_STATUS));
        mav.addObject("url", "/business/assets");
        return mav;
    }
}
