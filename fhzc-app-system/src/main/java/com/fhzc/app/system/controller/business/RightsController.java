package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.commons.vo.RightVo;
import com.fhzc.app.system.controller.AjaxJson;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
import org.springframework.stereotype.Controller;
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
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private ScoreHistoryService scoreHistoryService;
    
    @Resource
    private FocusService focusService;

    @Resource
    private ScoreService scoreService;

    /**
     * 权益列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看权益列表")
    public ModelAndView listRights(){
        ModelAndView mav = new ModelAndView("business/rights/list");
        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, 300);
        for(Rights rights : pageableResult.getItems() ){
        	List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.RIGHTS, rights.getId());
        	rights.setFocusNum(focuses.size() > 0 ? focuses.size() : 0);
        	List<RightsReservation> orders= rightsService.findSuccessOrdersById(rights.getId(), 1);
        	rights.setOrderNum(orders.size() > 0 ? orders.size() : 0);
        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("rights", pageableResult.getItems());
        mav.addObject("customerLevel", dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL));
        mav.addObject("rightsCategory", dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY));
        mav.addObject("url", "business/rights");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView preAdd(){
        ModelAndView mav = new ModelAndView("business/rights/add");
        List<Dictionary> list = dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL);
        Collections.reverse(list);
        mav.addObject("customerLevel", JSON.toJSON(list));
        mav.addObject("rightsCategory", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY)));
        mav.addObject("url", "business/rights");
        return mav;
    }

    /**
     * 添加权益
     * @param rights
     * @param coverFile
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改权益")
    public String addRight(Rights rights, MultipartFile coverFile){
        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            rights.setCover(coverPath + coverName);
        }
        rights.setCtime(new Date());
        rightsService.addOrUpdateRights(rights);

        return "redirect:/business/rights/list";
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
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("rightsCategory", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY)));
        return mav;
    }

    /**
     * 创建权益预约
     * @return
     */
    @RequestMapping(value = "/reservation/pub", method = RequestMethod.GET)
    @SystemControllerLog(description = "新增权益预约")
    public ModelAndView preReservationAdd(){
        ModelAndView mav = new ModelAndView("business/rights/addRightReservation");
        mav.addObject("rights", rightsService.getAllRights());
        mav.addObject("url", "business/rights");
        return mav;
    }

    /**
     * 检查用户手机号
     * @return
     */
    @RequestMapping(value = "/check/phone", method = RequestMethod.GET)
    @ResponseBody
    public Customer checkPhone(String phoneNum){
        User user = userService.getUserByMobile(phoneNum.trim());
        if(user == null){
            return null;
        }
        Customer customer = customerService.getCustomerByUid(user.getUid(), null);
        customer.setCustomerName(user.getRealname());
        customer.setAvailableScore(scoreService.sumScore(scoreService.getAvailableList(user.getUid())));
        customer.setLevelName(super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
        return customer;
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
     * 权益预约
     * @return
     */
    @RequestMapping(value = "/reservation/add", method = RequestMethod.GET)
    @SystemControllerLog(description = "权益预约")
    public String addReservation(Integer reservationRight, Integer customerId, Integer exchangeScore){
        RightsReservation reservation = new RightsReservation();
        reservation.setCtime(new Date());
        reservation.setRightsId(reservationRight);
        reservation.setCustomerId(customerId);
        reservation.setScoreCost(exchangeScore);
        reservation.setMarkDate(new Date());
        reservation.setStatus(1);
        rightsService.addRightsReservation(reservation);

        return "redirect:/business/rights/reservations";
    }

    /**
     * 权益预约列表
     * @param rightName
     * @param identityId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public ModelAndView listRightReservations(String rightName, String identityId, Date startTime, Date endTime){
        ModelAndView mav = new ModelAndView("business/rights/reservationList");
        PageableResult<RightsReservation> pageableResult = rightsService.listRightReservations(page, size);

        List<RightsReservation> list = new ArrayList<RightsReservation>();
        for(RightsReservation reservation : pageableResult.getItems()){
            Rights rights = rightsService.getRights(reservation.getRightsId());
            Customer customer = customerService.getCustomer(reservation.getCustomerId());
             User user = userService.getUser(customer.getUid());
            reservation.setCustomerName(user.getRealname());
            reservation.setCustomerMobile(user.getMobile());
            reservation.setRightName(rights.getName());
            list.add(reservation);
        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reservations", list);
        mav.addObject("url", "business/rights");
        return mav;
    }

    /**
     * 取消预约
     * @param id
     * @return
     */
    @RequestMapping(value = "/reservation/cancel", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "取消预约")
    public AjaxJson dealReservation(Integer id){
        RightsReservation reservation = rightsService.getReservationById(id);
        reservation.setStatus(3);
        rightsService.updateReservation(reservation);
        return new AjaxJson(true);
    }
}
