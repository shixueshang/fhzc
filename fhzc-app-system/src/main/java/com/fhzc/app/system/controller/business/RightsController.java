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
import com.fhzc.app.system.commons.vo.FocusVo;
import com.fhzc.app.system.commons.vo.RightVo;
import com.fhzc.app.system.controller.AjaxJson;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        	List<RightsReservation> orders= rightsService.findSuccessOrdersById(rights.getId(), Const.FOCUS_STATUS.ON);
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
        rights.setNotice("需提前"+rights.getAdvanceDay()+"天报名");
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
        vo.setProviderPhone(rights.getSupplyPhone());
        vo.setNotice(rights.getNotice());
        return vo;
    }

    /**
     * 权益预约
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/reservation/add", method = RequestMethod.GET)
    @SystemControllerLog(description = "权益预约")
    public String addReservation(Integer reservationRight, Integer customerId, Integer exchangeScore, String markDate) throws ParseException{
        RightsReservation reservation = new RightsReservation();
        reservation.setCtime(new Date());
        reservation.setRightsId(reservationRight);
        reservation.setCustomerId(customerId);
        reservation.setScoreCost(exchangeScore);
        reservation.setMarkDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(markDate));
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

    @RequestMapping(value = "/reservation/status", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson reserStatus(){
        return  new AjaxJson(true, dictionaryService.findDicByType(Const.DIC_CAT.RESERVATION_STATUS));
    }

    /**
     * 取消预约
     * @param reserId
     * @param status
     * @return
     */
    @RequestMapping(value = "/reservation/deal", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "处理预约")
    public AjaxJson dealReservation(Integer reserId, Integer status){
        RightsReservation reservation = rightsService.getReservationById(reserId);
        reservation.setStatus(status);
        rightsService.updateReservation(reservation);
        return new AjaxJson(true);
    }
    
    /**
     * 权益关注列表
     * @return
     */
    @RequestMapping(value = "/focusList", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看权益关注列表")
    public ModelAndView listRightsFocus(){
        ModelAndView mav = new ModelAndView("business/rights/focusList");
        mav.addObject("url", "business/rights");
        return mav;
    }
    
    /**
     * 权益关注列表
     * @return
     */
    @RequestMapping(value = "/focusFind", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看权益关注列表")
    public ModelAndView listRightsFocus(String rightsName){
        ModelAndView mav = new ModelAndView("business/rights/focusList");
        List<Integer> rids = new ArrayList<Integer>();
        List<Rights> rightss = new ArrayList<Rights>();
        if(StringUtils.isNotBlank(rightsName)){
        	rightss = rightsService.getAllRights();
        	for (Rights rights : rightss) {
				if(rights.getName().contains(rightsName.trim())){
					rids.add(rights.getId());
				}
			}
        	if(rids.isEmpty()){
        		return mav;
        	}
        }
        PageableResult<Focus> presult = focusService.getFocusByType(Const.FOCUS_TYPE.RIGHTS, rids, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
        mav.addObject("url", "business/rights");
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
                Rights r = rightsService.getRights(focus.getFid());
                vo.setContentName(r.getName());
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
    
    /**
     * 权益分类
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ModelAndView listType(){
        ModelAndView mav = new ModelAndView("business/rights/rightsType");
        mav.addObject("rightsTypes", dictionaryService.findDicByType(Const.DIC_CAT.RIGHTS_CATEGORY));
        mav.addObject("url", "business/rights");
        return mav;
    }
    
    @RequestMapping(value = "/isKeyExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isKeyExists(String key){
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.RIGHTS_CATEGORY, "key", key);
        return !flag;
    }
    
    @RequestMapping(value = "/isValueExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isValueExists(String value){
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.RIGHTS_CATEGORY, "value", value);
        return !flag;
    }
    
    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public String add(Dictionary dictionary){
        dictionary.setStatus(Const.Data_Status.DATA_NORMAL);
        dictionary.setCat(Const.DIC_CAT.RIGHTS_CATEGORY);
        dictionary.setIsDefault(Const.YES_OR_NO.NO);
        dictionary.setIsModify(Const.YES_OR_NO.NO);
        dictionary.setName("权益类型");
        dictionaryService.addOrUpdate(dictionary);
        return "redirect:/business/rights/type";
    }

    @RequestMapping(value = "/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value = "id") Integer id){

        //判断该分类是否被使用
        Dictionary dictionary = dictionaryService.getDictionary(id);
        List<Rights> rights = rightsService.getRightsByType(dictionary.getValue());
        if(rights.size() > 0){
            return new AjaxJson(false, "已被权益使用，不能删除");
        }
        dictionaryService.delete(id);
        return new AjaxJson(true);
    }

}
