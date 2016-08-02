package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.bo.ActivityApplyBo;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.vo.CustomerVo;
import com.fhzc.app.system.commons.vo.RightReservationVo;
import com.fhzc.app.system.commons.vo.RightVo;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/rights")
public class RightsController extends BaseController{

    @Resource
    private RightsService rightsService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    /**
     * 权益列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listRights(){
        ModelAndView mav = new ModelAndView("business/rights/list");
        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("rights", pageableResult.getItems());
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("rightsCategory", dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY));
        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
        mav.addObject("url", "business/rights");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView preAdd(){
        ModelAndView mav = new ModelAndView("business/rights/add");
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("rightsCategory", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY)));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("url", "business/rights");
        return mav;
    }

    /**
     * 添加权益
     * @param rights
     * @param coverFile
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView addRight(Rights rights, MultipartFile coverFile){
        ModelAndView mav = new ModelAndView("business/rights/list");
        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            rights.setCover(coverPath + coverName);
        }
        rights.setCtime(new Date());
        rightsService.addOrUpdateRights(rights);

        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("rights", pageableResult.getItems());
        mav.addObject("url", "business/rights");
        return mav;
    }

    /**
     * 权益编辑
     * @param id
     * @return
     */
    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/rights/add");
        mav.addObject("right", rightsService.getRights(id));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(1)));
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("rightsCategory", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY)));
        return mav;
    }

    /**
     * 权益预约列表
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public ModelAndView listRightsOrder(){
        ModelAndView mav = new ModelAndView("business/rights/order/list");

        return mav;
    }

    /**
     * 创建权益预约
     * @return
     */
    @RequestMapping(value = "/reservation/pub", method = RequestMethod.GET)
    public ModelAndView preReservationAdd(){
        ModelAndView mav = new ModelAndView("business/rights/addRightReservation");
        PageableResult<Rights> pageableResult = rightsService.findPageRights(1, 500);
        mav.addObject("rights", pageableResult.getItems());
        return mav;
    }

    /**
     * 检查用户手机号
     * @return
     */
    @RequestMapping(value = "/check/phone", method = RequestMethod.GET)
    @ResponseBody
    public CustomerVo checkPhone(String phoneNum){
        CustomerVo vo = customerService.getCustomerInfoByMobile(phoneNum);
        return vo;
    }

    /**
     * 获取权益信息
     * @return
     */
    @RequestMapping(value = "/get/rightInfo", method = RequestMethod.GET)
    @ResponseBody
    public RightVo rightInfo(long rightId){
        RightVo vo = new RightVo();
        Rights rights = rightsService.getRights((int)rightId);
        vo.setScore(rights.getSpendScore());
        vo.setProviderName(rights.getSupply());

        return vo;
    }

    /**
     * 获取权益信息
     * @return
     */
    @RequestMapping(value = "/reservation/add", method = RequestMethod.GET)
    public ModelAndView addReservation(long reservationRight, long customerId, long exchangeScore, Date reservationTime){
        RightsReservation reservation = new RightsReservation();
        reservation.setCtime(new Date());
        reservation.setRightsId((int)reservationRight);
        reservation.setCustomerId((int)customerId);
        reservation.setScoreCost(Long.toString(exchangeScore));
        reservation.setMarkDate(reservationTime);
        reservation.setStatus(1);
        rightsService.addRightsReservation(reservation);

        ModelAndView mav = new ModelAndView("business/rights/addRightReservation");
        PageableResult<Rights> pageableResult = rightsService.findPageRights(1, 500);
        mav.addObject("rights", pageableResult.getItems());
        return mav;
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ModelAndView listRightReservations(String rightName, String identityId, Date startTime, Date endTime){
        ModelAndView mav = new ModelAndView("business/rights/reservationList");
        PageableResult<RightsReservation> pageableResult = rightsService.listRightReservations(page, size);
        List<RightsReservation> reservations = pageableResult.getItems();
        List<RightReservationVo> vos = new LinkedList<RightReservationVo>();
        if (!CollectionUtils.isEmpty(reservations)){
            for (RightsReservation reser : reservations){
                RightReservationVo vo = new RightReservationVo();
                vo.setId(reser.getId());
                Rights r = rightsService.getRights(reser.getRightsId());
                vo.setRightName(r.getName());

                Customer c = customerService.getCustomer(reser.getCustomerId());
                User u = userService.getUser(c.getUid());
                vo.setCustomerName(u.getRealname());
                vo.setPhoneNum(u.getMobile());

                vo.setScore(Integer.parseInt(reser.getScoreCost()));
                vo.setReservationTime(reser.getMarkDate());
                vos.add(vo);
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reservations", vos);
        return mav;
    }
}
