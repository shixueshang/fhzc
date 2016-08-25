package com.fhzc.app.system.controller.personal;

import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.vo.FocusVo;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lihongde on 2016/7/30 14:00
 */
@Controller
@RequestMapping(value = "/personal/focus")
public class FocusController extends BaseController {
    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    @Resource
    private RightsService rightsService;

    @Resource
    private ReportService reportService;

    @Resource
    private ActivityService activityService;

    @Resource
    private CustomerService customerService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private FocusService focusService;

    /**
     * 用户关注列表页面
     * @return
     */
    @RequestMapping(value = "/list")
    @SystemControllerLog(description = "查看用户关注列表")
    public ModelAndView listSingleCustomer(String ftype){
        ModelAndView mav = new ModelAndView("personal/user/focusList");
        PageableResult<Focus> presult = focusService.getFocusByType(ftype, page, size);

        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
        mav.addObject("url", "personal/focus");
        return mav;
    }

    List<FocusVo> getFocusVos(PageableResult<Focus> presult){
        List<FocusVo> vos = new LinkedList<>();
        if (!CollectionUtils.isEmpty(presult.getItems())){
            for (Focus focus : presult.getItems()){
                FocusVo vo = new FocusVo();
                vo.setId(focus.getId());
                vo.setFocusTime(focus.getCtime());
                if (focus.getStatus() == 0){
                    vo.setStatus("取消关注");
                } else if (focus.getStatus() == 1) {
                    vo.setStatus("关注");
                }

                User user = null;
                try{
                    user = userService.getUser(focus.getUid());
                } catch (Exception ex){
                    continue;
                }

                vo.setUserName(user.getRealname());
               
                switch (focus.getFtype()){
                    case "product": {
                        vo.setContentType("产品");
                        Product p = productService.getProduct(focus.getFid());
                        if (p == null){
                            logger.error("Could not find product with id {}", focus.getFid());
                            continue;
                        }

                        vo.setContentName(p.getName());
                        break;
                    }

                    case "report":{
                        vo.setContentType("报告");
                        Report r = reportService.getReport(focus.getFid());
                        if (r == null){
                            logger.error("Could not find report with id {}", focus.getFid());
                            continue;
                        }

                        vo.setContentName(r.getName());
                        break;
                    }

                    case "rights":{
                        vo.setContentType("权益");
                        Rights ri = rightsService.getRights(focus.getFid());
                        if (ri == null){
                            logger.error("Could not find rights with id {}", focus.getFid());
                            continue;
                        }

                        vo.setContentName(ri.getName());
                        break;
                    }

                    case "activity":{
                        vo.setContentType("活动");
                        Activity a = activityService.getActivity(focus.getFid());
                        if (a == null){
                            logger.error("Could not find activity with id {}", focus.getFid());
                            continue;
                        }

                        vo.setContentName(a.getName());
                        break;
                    }
                }

                if ("customer".equalsIgnoreCase(user.getLoginRole().trim().toLowerCase())){
                    Customer customer = customerService.getCustomerByUid(user.getUid(), null);
                    if (customer == null){
                        logger.error("Could not find customer with id {}", user.getUid());
                        continue;
                    }
                    vo.setUserType("single".equals(customerService.getCustomerByUid(user.getUid(), null).getCustomerType())?"个人客户":"机构客户");
                } else {
                    vo.setUserType("理财师");
                }

                vos.add(vo);
            }
        }
        return vos;
    }

}
