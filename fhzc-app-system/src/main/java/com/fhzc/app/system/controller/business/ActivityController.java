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

import java.util.*;

/**
 * Created by lihongde on 2016/7/18 15:38
 */
@Controller
@RequestMapping(value = "business/activity")
public class ActivityController extends BaseController {

    @Resource
    private ActivityService activityService;

    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private FocusService focusService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询活动列表")
    public ModelAndView listActivities(){
        ModelAndView mav = new ModelAndView("business/activity/list");
        Admin admin = super.getCurrentUser();
        List<Integer> departments = new ArrayList<Integer>();
        List<Department> dps = departmentService.findAllDepartment();
        PageableResult<Activity> pageableResult;
        if(admin.getOrgan() == 1){
        	 for (Department department : dps) {
        		 departments.add(department.getDepartmentId());
             }
        	 pageableResult = activityService.findPageActivies(departments, page, size);
        }else{
        	 departments.add(Const.ROOT_DEPT_ID);
             departments.add(admin.getOrgan());
             pageableResult = activityService.findPageActivies(departments, page, size);
        }
         
        for (Activity activity : pageableResult.getItems()) {
        	List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.ACTIVITY, activity.getId());
        	activity.setFocusNum(focuses.size() > 0 ? focuses.size() : 0);
        	List<ActivityApply> orders = activityService.findSuccessOrdersById(activity.getId(), Const.FOCUS_STATUS.ON);
        	int personNum = 0;
        	if(orders.size() > 0){
            	for (ActivityApply activityApply : orders) {
    				personNum = personNum+activityApply.getPersonNum();
    			}
        	}
        	activity.setOrderNum(personNum > 0 ? personNum : 0);
        	activity.setDepartmentName(departmentService.getDepartment(activity.getDepartmentId()).getTitle());
		}
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activities", pageableResult.getItems());
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        mav.addObject("url", "business/activity");
        return mav;
    }

    @RequestMapping(value = "/pub")
    public ModelAndView preAdd(){
        ModelAndView mav = new ModelAndView("business/activity/add");
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        Admin admin = super.getCurrentUser();
        mav.addObject("department", JSON.toJSON(departmentService.getDepartment(admin.getOrgan())));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("url", "business/activity");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改活动")
    public String addActivity(Activity activity, MultipartFile coverFile){
        if(!coverFile.isEmpty()){
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            activity.setCover(coverPath + coverName);
        }
        activity.setCtime(new Date());
        activityService.addOrUpdateActivity(activity);
        return "redirect:/business/activity/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "id") Integer id){
        ModelAndView mav = new ModelAndView("business/activity/add");
        Activity activity = activityService.getActivity(id);
        mav.addObject("activity", activity);
        mav.addObject("activityTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY)));
        mav.addObject("department", JSON.toJSON(departmentService.getDepartment(activity.getDepartmentId())));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("url", "business/activity");
        return mav;
    }

    @RequestMapping(value = "/registers", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看活动报名列表")
    public ModelAndView listActivityApplies(String activityName, Date startTime, Date endTime){
        ModelAndView mav = new ModelAndView("business/activity/registerList");
        ActivityApplyQuery query = new ActivityApplyQuery();
        if(StringUtils.isNotBlank(activityName)){
            Activity activity = activityService.findActivityByName(activityName.trim());
            if(activity != null){
                query.setActivityId(activity.getId());
            }else{
            	return mav;
            }
        }
        if (startTime != null){
            query.setStartDate(startTime);
        }
        if (endTime != null){
            query.setEndDate(endTime);
        }
        PageableResult<ActivityApply> pageableResult = activityService.findPageActivityApplies(query, page, size);
        List<ActivityApply> list = new ArrayList<ActivityApply>();
        for(ActivityApply apply : pageableResult.getItems()){
            apply.setActivityName(activityService.getActivity(apply.getActivityId()).getName());
            Customer customer = customerService.getCustomer(apply.getCustomerId());
            apply.setLevel(super.getDicName(customer.getLevelId(), Const.DIC_CAT.CUSTOMER_LEVEL));
            if(apply.getType().equals(Const.APPLYTYPE.SELF)){
            	apply.setPersonName(userService.getUser(customer.getUid()).getRealname());
                apply.setMobile(userService.getUser(customer.getUid()).getMobile());
            }else{
            	apply.setPersonName(apply.getPersonName());
            	apply.setMobile(apply.getPhone());
            }
            list.add(apply);
        }
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("activityApplies", list);
        mav.addObject("url", "business/activity");
        return mav;
    }
    
    /**
     * 活动关注列表
     * @return
     */
    @RequestMapping(value = "/focusList", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看活动关注列表")
    public ModelAndView listActivtyFocus(){
        ModelAndView mav = new ModelAndView("business/activity/focusList");
        mav.addObject("url", "business/activity");
        return mav;
    }
    
    /**
     * 活动关注列表
     * @return
     */
    @RequestMapping(value = "/focusFind", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看活动关注列表")
    public ModelAndView listActivityFocus(String activtyName){
        ModelAndView mav = new ModelAndView("business/activity/focusList");
        List<Integer> aids = new ArrayList<Integer>();
        List<Activity> activitys = new ArrayList<Activity>();
        if(StringUtils.isNotBlank(activtyName)){
        	activitys = activityService.getAllActivities();
        	for (Activity activity : activitys) {
				if(activity.getName().contains(activtyName.trim())){
					aids.add(activity.getId());
				}
			}
        	if(aids.isEmpty()){
        		return mav;
        	}
        }
        PageableResult<Focus> presult = focusService.getFocusByType(Const.FOCUS_TYPE.ACTIVITY, aids, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
        mav.addObject("url", "business/activity");
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
                Activity a = activityService.getActivity(focus.getFid());
                vo.setContentName(a.getName());
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
     * 活动分类
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ModelAndView listType(){
        ModelAndView mav = new ModelAndView("business/activity/activityType");
        mav.addObject("activityTypes", dictionaryService.findDicByType(Const.DIC_CAT.ACTIVITY_CATEGORY));
        mav.addObject("url", "business/activity");
        return mav;
    }
    
    @RequestMapping(value = "/isKeyExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isKeyExists(String key){
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.ACTIVITY_CATEGORY, "key", key);
        return !flag;
    }
    
    @RequestMapping(value = "/isValueExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isValueExists(String value){
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.ACTIVITY_CATEGORY, "value", value);
        return !flag;
    }
    
    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public String add(Dictionary dictionary){
        dictionary.setStatus(Const.Data_Status.DATA_NORMAL);
        dictionary.setCat(Const.DIC_CAT.ACTIVITY_CATEGORY);
        dictionary.setIsDefault(Const.YES_OR_NO.NO);
        dictionary.setIsModify(Const.YES_OR_NO.NO);
        dictionary.setName("活动类型");
        dictionaryService.addOrUpdate(dictionary);
        return "redirect:/business/activity/type";
    }

    @RequestMapping(value = "/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value = "id") Integer id){
        //判断该分类是否被使用
        Dictionary dictionary = dictionaryService.getDictionary(id);
        List<Activity> activitys = activityService.getActivityByType(dictionary.getValue());
        if(activitys.size() > 0){
            return new AjaxJson(false, "已被使用，不能删除");
        }
        dictionaryService.delete(id);
        return new AjaxJson(true);
    }
}
