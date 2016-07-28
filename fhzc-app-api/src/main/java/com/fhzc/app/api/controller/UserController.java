package com.fhzc.app.api.controller;

import com.fhzc.app.api.exception.BadRequestException;
import com.fhzc.app.api.service.CustomerService;
import com.fhzc.app.api.service.DictionaryService;
import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.api.tools.ApiJsonResult;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by lihongde on 2016/7/28 14:59
 */
@Controller
public class UserController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 获取登录用户信息
     * @return
     */
    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    @ResponseBody
    public ApiJsonResult getUserInfo(){
        User user  = getCurrentUser();
        Customer customer = customerService.getCustomerByUid(user.getUid());
        if(customer == null){
            throw new BadRequestException("客户信息不存在");
        }

        List<Dictionary> dicts = dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL);
        for(Dictionary dict : dicts){
            if(dict.getValue().equals(customer.getLevelId().toString())){
                user.setLevel(dict.getKey());
            }
        }

        return new ApiJsonResult(APIConstants.API_JSON_RESULT.OK, user);
    }
}
