package com.fhzc.app.system.controller.business;

import com.alibaba.fastjson.JSON;
import com.fhzc.app.dao.mybatis.model.*;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.page.PageHelper;
import com.fhzc.app.dao.mybatis.page.PageableResult;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.aop.SystemControllerLog;
import com.fhzc.app.system.commons.util.DateUtil;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by lihongde on 2016/7/7 15:22
 */
@Controller
@RequestMapping(value = "business/product")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private PlannerService plannerService;

    @Resource
    private CustomerService customerService;

    @Resource
    private UserService userService;

    @Resource
    private FocusService focusService;

    @Resource
    private AssetsService assetsService;

    @Resource
    private PushTokenService pushTokenService;

    @Resource
    private NoticeService noticeService;


    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询产品列表")
    public ModelAndView listProduct() {
        ModelAndView mav = new ModelAndView("business/product/list");
        PageableResult<Product> pageableResult = productService.findPageProducts(page, size);
        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        List<Product> products = new ArrayList<Product>();
        for (Product product : pageableResult.getItems()) {
            //获得产品关注人数
            List<Focus> focuses = focusService.findFocusByType(Const.FOCUS_TYPE.PRODUCT, product.getPid());
            product.setFocusNum(focuses.size() > 0 ? focuses.size() : 0);

            //获得预约人数
            List<ProductReservation> orders = productService.findOrdersByPid(product.getPid(), Const.ORDER_RESULT.Success);
            product.setOrderNum(orders.size() > 0 ? orders.size() : 0);
            BigDecimal orderAmount = new BigDecimal(0.00);
            for (ProductReservation order : orders) {
                orderAmount = orderAmount.add(new BigDecimal(order.getAmount() / 10000));
            }
            product.setOrderAmount(orderAmount);
            products.add(product);
        }
        mav.addObject("products", products);
        mav.addObject("productTypes", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE));
        mav.addObject("productStatus", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS));
        mav.addObject("departments", departmentService.findDeptByParent(Const.ROOT_DEPT_ID));
        mav.addObject("yesNo", dictionaryService.findDicByType(Const.DIC_CAT.YES_NO));
        mav.addObject("url", "business/product");
        return mav;
    }

    /**
     * 产品发布页面
     *
     * @return
     */
    @RequestMapping(value = "/pub")
    public ModelAndView pubProduct() {
        ModelAndView mav = new ModelAndView("business/product/add");
        mav.addObject("productTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE)));
        mav.addObject("productStatus", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS)));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(Const.ROOT_DEPT_ID)));
        mav.addObject("productIssueType", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_ISSUE_TYPE)));
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        mav.addObject("url", "business/product");
        return mav;
    }


    /**
     * 产品发布
     *
     * @param product    产品信息
     * @param coverFile  产品封面
     * @param proveFile  备案证明
     * @param noticeFile 产品成立公告
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增或修改产品")
    public String addOrUpdateProduct(Product product, MultipartFile coverFile, MultipartFile proveFile, MultipartFile noticeFile) {

        if (!coverFile.isEmpty()) {
            String coverName = FileUtil.generatePictureName(coverFile);
            String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(coverPath, coverName, coverFile);
            product.setCover(coverPath + coverName);
        }

        if (!proveFile.isEmpty()) {
            String proveUrlName = FileUtil.generatePictureName(proveFile);
            String proveUrlPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(proveUrlPath, proveUrlName, proveFile);
            product.setProveUrl(proveUrlPath + proveUrlName);
        }

        if (!noticeFile.isEmpty()) {
            String noticeName = FileUtil.generatePictureName(noticeFile);
            String noticePath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
            FileUtil.transferFile(noticePath, noticeName, noticeFile);
            product.setNotice(noticePath + noticeName);
        }

        if (product.getInvestThreshold() != null && product.getInvestThreshold().compareTo(new BigDecimal(0)) == 1) {
            product.setInvestThreshold(product.getInvestThreshold().multiply(new BigDecimal(10000)));
        }
        product.setCtime(new Date());
        productService.addOrUpdateProduct(product);
        product.setDisplayOrder(product.getPid());
        productService.addOrUpdateProduct(product);

        return "redirect:/business/product/list";
    }

    /**
     * 产品编辑
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/detail/{pid}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable(value = "pid") Integer pid) {
        ModelAndView mav = new ModelAndView("business/product/add");
        Product product = productService.getProduct(pid);
        mav.addObject("product", product);
        mav.addObject("productTypes", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE)));
        mav.addObject("productStatus", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_STATUS)));
        mav.addObject("departments", JSON.toJSON(departmentService.findDeptByParent(1)));
        mav.addObject("productIssueType", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_ISSUE_TYPE)));
        mav.addObject("customerLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL)));
        mav.addObject("riskLevel", JSON.toJSON(dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL)));
        mav.addObject("url", "business/product");
        return mav;
    }

    @RequestMapping(value = "/isNameExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isNameExists(String name) {
        boolean flag = productService.isNameExists(name);
        return !flag;
    }

    @RequestMapping(value = "/isCodeExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isCodeExists(String code) {
        boolean flag = productService.isCodeExists(code);
        return !flag;
    }

    @RequestMapping(value = "/importor")
    public String importorProduct() {

        return "business/product/importor";
    }

    /**
     * excel导入
     *
     * @param multiFile
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "导入产品")
    public Map<String, Object> importExcel(MultipartFile multiFile) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result = productService.importExcelFile(multiFile);
            result.put("success", true);
        } catch (Exception e) {
            logger.error("导入失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 产品预约列表
     *
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看产品预约列表")
    public ModelAndView listProductOrdered() {
        ModelAndView mav = new ModelAndView("business/product/reservationList");
        mav.addObject("url", "business/product");
        return mav;
    }

    /**
     * 产品预约列表
     *
     * @return
     */
    @RequestMapping(value = "/order/filter", method = RequestMethod.GET)
    public ModelAndView listProductOrderedByFilter(String productName, String identityId, Date startTime, Date endTime) {
        ModelAndView mav = new ModelAndView("business/product/reservationList");
        ProductReserQuery query = new ProductReserQuery();
        if (StringUtils.isNotBlank(productName)) {
            query.setProductName(productName);
            Product product = productService.getProduct(productName.trim());
            if (product != null) {
                query.setProductName(product.getName());
                query.setProductId(product.getPid());
            }
        }

        if (startTime != null) {
            query.setStartDate(startTime);
        }

        if (endTime != null) {
            query.setEndDate(endTime);
        }

        Admin admin  = super.getCurrentUser();
        List<Integer> departments = departmentService.findAllChildrenIds(admin.getOrgan());

        PageableResult<ProductReservation> pageableResult = productService.findPageProductReservations(query, page, size);
        List<ProductReservation> result = new ArrayList<ProductReservation>();
        for (ProductReservation productReservation : pageableResult.getItems()) {
            productReservation.setProductName(productService.getProduct(productReservation.getProductId()).getName());
            Customer customer = customerService.getCustomer(productReservation.getCustomerId());
            User customUser = userService.getUser(customer.getUid());
            productReservation.setCustomerNum(customer.getCbId());
            productReservation.setCustomerName(customUser.getRealname());

            Planner planner = plannerService.getPlanner(productReservation.getPlannerId());
            User plannerUser = userService.getUser(planner.getUid());
            productReservation.setPlannerNum(planner.getWorkNum());
            productReservation.setPlannerMobile(plannerUser.getMobile());
            productReservation.setPlannerName(plannerUser.getRealname());
            if(departments.contains(customer.getDepartmentId())){
                result.add(productReservation);
            }
        }

        mav.addObject("page", PageHelper.getPageModel(request, pageableResult));
        mav.addObject("reservations", result);
        mav.addObject("url", "business/product");
        return mav;
    }

    /**
     * 产品关注列表
     *
     * @return
     */
    @RequestMapping(value = "/focus/list", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看产品关注列表")
    public ModelAndView listProductFocus() {
        ModelAndView mav = new ModelAndView("business/product/focusList");
        mav.addObject("url", "business/product");
        return mav;
    }

    /**
     * 产品关注列表
     *
     * @return
     */
    @RequestMapping(value = "/focus/find", method = RequestMethod.GET)
    @SystemControllerLog(description = "查看产品关注列表")
    public ModelAndView listProductFocus(String productName) {
        ModelAndView mav = new ModelAndView("business/product/focusList");
        List<Integer> pids = new ArrayList<Integer>();
        List<Product> products = new ArrayList<Product>();
        if (StringUtils.isNotBlank(productName)) {
            products = productService.findAllProduct();
            for (Product product : products) {
                if (product.getName().contains(productName.trim())) {
                    pids.add(product.getPid());
                }
            }
            if (pids.isEmpty()) {
                return mav;
            }
        }
        PageableResult<Focus> presult = focusService.getFocusByType(Const.FOCUS_TYPE.PRODUCT, pids, page, size);
        mav.addObject("page", PageHelper.getPageModel(request, presult));
        mav.addObject("focuses", getFocusVos(presult));
        mav.addObject("url", "business/product");
        return mav;
    }

    List<FocusVo> getFocusVos(PageableResult<Focus> presult) {
        List<FocusVo> vos = new LinkedList<>();
        if (!CollectionUtils.isEmpty(presult.getItems())) {
            for (Focus focus : presult.getItems()) {
                FocusVo vo = new FocusVo();
                vo.setId(focus.getId());
                vo.setFocusTime(focus.getCtime());
                if (focus.getStatus() == 0) {
                    vo.setStatus("取消关注");
                } else if (focus.getStatus() == 1) {
                    vo.setStatus("关注");
                }
                User user = null;
                try {
                    user = userService.getUser(focus.getUid());
                } catch (Exception ex) {
                    continue;
                }
                vo.setUserName(user.getRealname());
                Product p = productService.getProduct(focus.getFid());
                if(p == null){
                	continue;
                }else{
                	vo.setContentName(p.getName());
                }
                if ("customer".equalsIgnoreCase(user.getLoginRole().trim().toLowerCase())) {
                    Customer customer = customerService.getCustomerByUid(user.getUid(), null);
                    if (customer == null) {
                        logger.error("Could not find customer with id {}", user.getUid());
                        continue;
                    }
                    vo.setUserType("single".equals(customerService.getCustomerByUid(user.getUid(), null).getCustomerType()) ? "个人客户" : "机构客户");
                } else {
                    vo.setUserType("理财师");
                }
                vos.add(vo);
            }
        }
        return vos;
    }

    /**
     * 产品分类
     *
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ModelAndView listType() {
        ModelAndView mav = new ModelAndView("business/product/productType");
        mav.addObject("productTypes", dictionaryService.findDicByType(Const.DIC_CAT.PRODUCT_TYPE));
        mav.addObject("url", "business/product");
        return mav;
    }

    @RequestMapping(value = "/isKeyExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isKeyExists(String key) {
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.PRODUCT_TYPE, "key", key);
        return !flag;
    }

    @RequestMapping(value = "/isValueExists", method = RequestMethod.GET)
    @ResponseBody
    public Object isValueExists(String value) {
        boolean flag = dictionaryService.isKeyOrValueExists(Const.DIC_CAT.PRODUCT_TYPE, "value", value);
        return !flag;
    }

    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public String add(Dictionary dictionary) {

        dictionary.setStatus(Const.Data_Status.DATA_NORMAL);
        dictionary.setCat(Const.DIC_CAT.PRODUCT_TYPE);
        dictionary.setIsDefault(Const.YES_OR_NO.NO);
        dictionary.setIsModify(Const.YES_OR_NO.NO);
        dictionary.setName("产品类型");
        dictionaryService.addOrUpdate(dictionary);
   
        AssetsRecommend assetsRecommend = new AssetsRecommend();
        assetsRecommend.setRecommendType(dictionary.getValue());
        AssetsRecommend temassetsRecommend = assetsService.getAssetsRecommendByType(dictionaryService.getDictionary(dictionary.getId()).getValue());
        if(temassetsRecommend != null){
        	assetsRecommend.setId(temassetsRecommend.getId());
        	assetsRecommend.setProportion(temassetsRecommend.getProportion());
        }else{
        	assetsRecommend.setProportion(BigDecimal.valueOf(0));
        }
        assetsRecommend.setStatus(Const.Data_Status.DATA_NORMAL);
        assetsService.addOrUpdateAssetsRecommend(assetsRecommend);
        return "redirect:/business/product/type";
    }

    @RequestMapping(value = "/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson delete(@PathVariable(value = "id") Integer id) {

        //判断该分类是否被使用
        Dictionary dictionary = dictionaryService.getDictionary(id);
        List<Product> products = productService.getProductByType(dictionary.getValue());
        if (products.size() > 0) {
            return new AjaxJson(false, "已被产品使用，不能删除");
        }

        AssetsRecommend recommend = assetsService.getAssetsRecommendByType(dictionaryService.getDictionary(id).getValue());
        if(recommend != null){
            assetsService.delRecommend(recommend.getId());
        }
        dictionaryService.delete(id);
        return new AjaxJson(true);
    }

    /**
     * 产品预约
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "/order/{pid}", method = RequestMethod.GET)
    public ModelAndView reservationPub(@PathVariable(value = "pid") Integer pid) {
        ModelAndView mav = new ModelAndView("business/product/addReservation");
        mav.addObject("product", productService.getProduct(pid));
        mav.addObject("customer_level", dictionaryService.findCustomerLevel(productService.getProduct(pid).getLevel() + ""));
        mav.addObject("risk_level", dictionaryService.findRiskLevel(productService.getProduct(pid).getRisk() + ""));
        mav.addObject("url", "business/product");
        return mav;
    }

    /**
     * 产品预约
     *
     * @param customerId
     * @param reservationAmount
     * @param reservationTime
     * @param productId
     * @return
     */
    @RequestMapping(value = "/reservationSave", method = RequestMethod.GET)
    @ResponseBody
    public boolean reservationSave(long customerId, long reservationAmount, Date reservationTime, long productId, String workNum) {
        Planner planner = plannerService.getPlannerByWorkNum(workNum, Const.PLANNER_STATUS.ON);
        ProductReservation productReservation = new ProductReservation();
        productReservation.setCtime(new Date());
        productReservation.setAmount((Long) reservationAmount);
        productReservation.setCustomerId((int) customerId);
        productReservation.setApplyTime(reservationTime);
        productReservation.setPlannerId(planner.getId());
        productReservation.setProductId((int) productId);
        productReservation.setResult(Const.ORDER_RESULT.Success);
        productService.addProductReservation(productReservation);

        return true;
    }

    @RequestMapping(value = "/validateWorkNum", method = RequestMethod.GET)
    @ResponseBody
    public boolean validateWorkNum(String workNum) {
        Planner planner = plannerService.getPlannerByWorkNum(workNum, Const.PLANNER_STATUS.ON);
        if (planner == null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/upDisplayOrder", method = RequestMethod.GET)
    @ResponseBody
    public String upDisplayOrder(String pid) {
        if (StringUtils.isBlank(pid)) {
            return "redirect:/business/product/list";
        }
        String msg;
        int originId;
        int finalId;
        int temId;
        List<Product> products = productService.findAllProduct();
        List<Integer> list = new ArrayList<Integer>();
        for (Product product : products) {
            list.add(product.getPid());
        }
        int i = list.indexOf(Integer.parseInt(pid)) - 1;
        if (i == -1) {
            msg = "top";
        } else {
            Product preProduct = products.get(i);
            finalId = preProduct.getDisplayOrder();
            Product product = productService.getProduct(Integer.parseInt(pid));
            originId = product.getDisplayOrder();
            temId = finalId;
            finalId = originId;
            originId = temId;
            product.setDisplayOrder(originId);
            preProduct.setDisplayOrder(finalId);
            productService.addOrUpdateProduct(product);
            productService.addOrUpdateProduct(preProduct);
            msg = "success";
        }
        return msg;
    }

    @RequestMapping(value = "/downDisplayOrder", method = RequestMethod.GET)
    @ResponseBody
    public String downDisplayOrder(String pid) {
        if (StringUtils.isBlank(pid)) {
            return "redirect:/business/product/list";
        }
        String msg;
        int originId;
        int finalId;
        int temId;
        List<Product> products = productService.findAllProduct();
        List<Integer> list = new ArrayList<Integer>();
        for (Product product : products) {
            list.add(product.getPid());
        }
        int i = list.indexOf(Integer.parseInt(pid)) + 1;
        if (i == list.size()) {
            msg = "bottom";
        } else {
            Product proProduct = products.get(i);
            finalId = proProduct.getDisplayOrder();
            Product product = productService.getProduct(Integer.parseInt(pid));
            originId = product.getDisplayOrder();
            temId = finalId;
            finalId = originId;
            originId = temId;
            product.setDisplayOrder(originId);
            proProduct.setDisplayOrder(finalId);
            productService.addOrUpdateProduct(product);
            productService.addOrUpdateProduct(proProduct);
            msg = "success";
        }
        return msg;
    }

    /**
     * 产品推送,根据产品的不同状态推送给对应客户和理财师
     *
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "产品推送")
    public AjaxJson productPush(SystemNotice notice) {
        Integer pid = notice.getId();

        List<AssetsHistory>   list =  assetsService.findAssetsByProduct(null, pid);

        List<Integer> customerIds = new ArrayList<Integer>();
        for(AssetsHistory assetsHistory : list){
            if(!customerIds.contains(assetsHistory.getCustomerId())){
                Date expireDay = assetsHistory.getExpireDay();
                Date before = DateUtil.getDateNextDays(expireDay, 10);  //过期10天
                if(expireDay == null){
                    customerIds.add(assetsHistory.getCustomerId());
                }else if(expireDay.getTime() > System.currentTimeMillis()){
                    customerIds.add(assetsHistory.getCustomerId());
                }else if(before.getTime() > System.currentTimeMillis()){
                    customerIds.add(assetsHistory.getCustomerId());
                }
            }
        }

        //获得发送对象
        List<Integer> pushUsers = new ArrayList<Integer>();
        for(Integer cId : customerIds){
            pushUsers.add(customerService.getCustomer(cId).getUid());
            pushUsers.add(plannerService.getPlanner(customerService.getPlannerByCustomerId(cId, Const.YES_OR_NO.YES).getPlannerId()).getUid());
        }

        preHandle(notice, pushUsers);

        return new AjaxJson(true);
    }

    private void preHandle(SystemNotice systemNotice, List<Integer> pushUsers){

        String channel = systemNotice.getPushChannel();
        String[] channelArr = channel.split(",");
        for(String cha : channelArr){
            if(cha.equals(Const.PUSH_CHANNEL.SYSTEM.toString())){
                //向notice表中添加一条不可用记录
                SystemNotice notice = new SystemNotice();
                String noticeTitle = "";
                String title = systemNotice.getTitle();
                if(title.equals(Const.ASSETS_TYPE.FOUND)){
                    noticeTitle = "产品成立";
                }else if(title.equals(Const.ASSETS_TYPE.DIVIDEND)){
                    noticeTitle = "产品派息";
                }else{
                    noticeTitle = "产品兑付";
                }
                notice.setTitle(noticeTitle);
                notice.setContent(systemNotice.getContent());
                notice.setPushStatus(Const.PUSH_STATUS.PUSHED);
                notice.setPushChannel(Const.PUSH_CHANNEL.SYSTEM.toString());
                notice.setPublishTime(new Date());
                notice.setStatus(Const.YES_OR_NO.NO);
                noticeService.addOrUpdate(notice);
                this.doHandleSystemNotice(notice, Const.PUSH_CHANNEL.SYSTEM);
            }
            if(cha.equals(Const.PUSH_CHANNEL.MESSAGE.toString())){
                this.doHandleSystemMessage(systemNotice, pushUsers);
            }
        }
    }

    /**
     * 处理系统通知
     * @param systemNotice
     * @param channel
     */
    private void doHandleSystemNotice(SystemNotice systemNotice, Integer channel){
        List<PushToken> list = pushTokenService.getAllTokens();
        for(PushToken pushToken : list){
            SystemNoticeRecord record = new SystemNoticeRecord();
            record.setNoticeId(systemNotice.getId());
            record.setUserId(pushToken.getUserId());
            record.setContent(systemNotice.getContent());
            record.setPushChannel(channel);
            record.setPushStatus(systemNotice.getPushStatus());
            record.setPushTime(new Date());
            noticeService.addOrUpdateNoticeRecord(record);
        }
    }

    /**
     * 处理消息推送
     * @param systemNotice
     * @param pushUsers
     */
    private void doHandleSystemMessage(SystemNotice systemNotice, List<Integer> pushUsers){
        for(Integer userId : pushUsers){
            try {
                pushTokenService.pushMessageToUser(userId, systemNotice.getContent());
            } catch (Exception e) {
                logger.error("推送失败");
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/getScoreFactor", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson getProductFactor(String productType){
        String factor = "";
        if(StringUtils.isNotEmpty(productType)){
            if(Const.PRODUCT_FACTOR.FORIGN.equals(productType)){
                factor = TextUtils.getConfig(Const.PRODUCT_FACTOR.FACTOR_FORIGN, this);
            }else if(Const.PRODUCT_FACTOR.OTHER.equals(productType)){
                factor = TextUtils.getConfig(Const.PRODUCT_FACTOR.FACTOR_OTHER, this);
            }else if(Const.PRODUCT_FACTOR.TOUR.equals(productType)){
                factor = TextUtils.getConfig(Const.PRODUCT_FACTOR.FACTOR_TOUR, this);
            }else{
                factor = TextUtils.getConfig(Const.PRODUCT_FACTOR.FACTOR_RIGHT, this);
            }
        }
        return new AjaxJson(true, (Object)factor);
    }
}
