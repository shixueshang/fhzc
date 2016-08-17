package com.fhzc.app.system.controller.personal;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.vo.FocusVo;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lihongde on 2016/7/30 14:00
 */
@Controller
@RequestMapping(value = "/personal/user")
public class UserController extends BaseController {
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

    /**
     * 用户关注列表页面
     * @return
     */
    @RequestMapping(value = "/focus/list")
    public ModelAndView listSingleCustomer(String ftype){
        ModelAndView mav = new ModelAndView("personal/user/focusList");
        PageableResult<Focus> presult = userService.getFocusByType(ftype, page, size);

        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
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

                    Dictionary customerLevel = dictionaryService.findCustomerLevel(customer.getLevelId().toString());
                    vo.setUserType(customerLevel.getKey());
                } else {
                    vo.setUserType("理财师");
                }

                vos.add(vo);
            }
        }

        return vos;
    }

}