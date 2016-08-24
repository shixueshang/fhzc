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
import com.fhzc.app.system.commons.vo.RightReservationVo;
import com.fhzc.app.system.commons.vo.RightVo;
import com.fhzc.app.system.controller.BaseController;
import com.fhzc.app.system.service.*;
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
        	List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.RIGHTS, rights.getId(),1);
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
    @SystemControllerLog(description = "新增权益预约")
    public ModelAndView preReservationAdd(){
        ModelAndView mav = new ModelAndView("business/rights/addRightReservation");

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
     * 获取权益信息
     * @return
     */
    @RequestMapping(value = "/reservation/add", method = RequestMethod.GET)
    public ModelAndView addReservation(long reservationRight, long customerId, Integer exchangeScore, Date reservationTime){
        RightsReservation reservation = new RightsReservation();
        reservation.setCtime(new Date());
        reservation.setRightsId((int) reservationRight);
        reservation.setCustomerId((int)customerId);
        reservation.setScoreCost(exchangeScore);
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
        List<RightReservationVo> vos = convertReserVo(reservations);

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reservations", vos);
        mav.addObject("url", "business/rights");
        return mav;
    }

    @RequestMapping(value = "/reservation/deal", method = RequestMethod.GET)
    public ModelAndView dealReservation(Integer id){
        ModelAndView mav = new ModelAndView("business/rights/dealReservation");
        RightsReservation reservation = rightsService.getReservationById(id);
        Customer customer = customerService.getCustomer(reservation.getCustomerId());
        User user = userService.getUser(customer.getUid());
        customer.setCustomerName(user.getRealname());
        customer.setAvailableScore(scoreHistoryService.getScoreByUserId(user.getUid()));

        RightVo rvo = new RightVo();
        Rights rights = rightsService.getRights(reservation.getRightsId());
        rvo.setScore(rights.getSpendScore());
        rvo.setProviderName(rights.getSupply());
        rvo.setRightId(reservation.getRightsId());

        PageableResult<Rights> pageableResult = rightsService.findPageRights(page, size);
        mav.addObject("reservation", reservation);
        mav.addObject("rights", pageableResult.getItems());

        mav.addObject("customer", customer);
        mav.addObject("providerInfo", rvo);
        return mav;
    }

    /**
     * 处理权益信息
     * @return
     */
    @RequestMapping(value = "/reservation/save", method = RequestMethod.GET)
    public ModelAndView saveReservation(String reservationRight, long id, Integer exchangeScore, Date reservationTime, long reservationStatus){
        //RightsReservation reservation = new RightsReservation();
        RightsReservation reservation = rightsService.getReservationById((int)id);
        if (!reservation.getRightsId().toString().equals(reservationRight)){
            reservation.setRightsId(Integer.parseInt(reservationRight));
            reservation.setScoreCost(exchangeScore);
        }

        Integer oldStatus = reservation.getStatus();
        reservation.setStatus((int)reservationStatus);
        reservation.setMarkDate(reservationTime);
        rightsService.updateReservation(reservation);

        if (oldStatus != reservation.getStatus() && oldStatus != 4 && oldStatus != 5 && (reservationStatus == 4 || reservationStatus == 5)){
            Customer customer = customerService.getCustomer(reservation.getCustomerId());

            ScoreHistory scoreHistory = new ScoreHistory();
            scoreHistory.setCtime(new Date());
            scoreHistory.setUid(customer.getUid());
            scoreHistory.setFromType("rights");
            scoreHistory.setScore(0 - reservation.getScoreCost());
            scoreHistory.setVaildTime(reservation.getMarkDate());
            scoreHistory.setStatus("consume");
            scoreHistory.setOperatorType("admin");
            scoreHistory.setOperatorId(1);
            scoreHistory.setIsApprove(1);
            scoreHistory.setIsVaild(1);
            if (reservationStatus == 4){
                scoreHistory.setDetail("客户消费");
            }

            if (reservationStatus == 5){
                scoreHistory.setDetail("客户缺席");
            }
            scoreHistoryService.addHistoryScore(scoreHistory);
        }

        ModelAndView mav = new ModelAndView("business/rights/reservationList");
        PageableResult<RightsReservation> pageableResult = rightsService.listRightReservations(page, size);
        List<RightsReservation> reservations = pageableResult.getItems();
        List<RightReservationVo> vos = convertReserVo(reservations);

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reservations", vos);
        return mav;
    }

    List<RightReservationVo> convertReserVo(List<RightsReservation> reservations){
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

                vo.setScore(reser.getScoreCost());
                vo.setReservationTime(reser.getMarkDate());
                switch (reser.getStatus()){
                    case 0:{
                        vo.setReservationStatus("预约中");
                        break;
                    }

                    case 1:{
                        vo.setReservationStatus("预约成功");
                        break;
                    }

                    case 2:{
                        vo.setReservationStatus("预约失败");
                        break;
                    }

                    case 3:{
                        vo.setReservationStatus("预约取消");
                        break;
                    }

                    case 4:{
                        vo.setReservationStatus("客户消费");
                        break;
                    }

                    case 5:{
                        vo.setReservationStatus("客户缺席");
                        break;
                    }
                }

                vos.add(vo);
            }
        }

        return vos;
    }
}
