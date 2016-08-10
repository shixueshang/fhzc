package com.fhzc.app.api.controller;

import com.fhzc.app.api.service.DictionaryService;
import com.fhzc.app.dao.mybatis.model.Customer;
import com.fhzc.app.dao.mybatis.model.Dictionary;
import com.fhzc.app.dao.mybatis.model.User;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.dao.mybatis.util.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected String basePath;
	protected Integer page;
	protected Integer size;

	@Resource
	private DictionaryService dictionaryService;

	public BaseController() {
		super();
	}

	/**
	 * 定义日期类型的数据绑定
	 * 
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	@ModelAttribute
	protected void initRequestResponseSession(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();

		String path = request.getContextPath();
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

		String page_str = request.getParameter("page");
		String size_str = request.getParameter("size");

		if (StringUtils.isNotEmpty(page_str)) {
			page = Integer.parseInt(page_str);
		}else{
            page = Const.DEFAULT_PAGE;
        }
		if (StringUtils.isNotEmpty(size_str)) {
            size = Integer.parseInt(size_str);
		}else{
            size = Const.DEFAULT_PAGE_SIZE;
        }
	}

    public User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User user  = (User)subject.getSession().getAttribute("user");
        return user;
    }

	/**
	 * 获得用户等级信息明文
	 * @param level
	 * @return
     */
    public String getLevelName(Integer level){
        List<Dictionary> dicts = dictionaryService.findDicByType(Const.DIC_CAT.CUSTOMER_LEVEL);
        for (Dictionary dict : dicts) {
            if (dict.getValue().equals(level.toString())) {
                return dict.getKey();
            }
        }
        return "";
    }

	/**
	 * 获得风险等级汉字
	 * @param risk
	 * @return
     */
    public String getRiskName(Integer risk){
        List<Dictionary> dicts = dictionaryService.findDicByType(Const.DIC_CAT.RISK_LEVEL);
        for (Dictionary dict : dicts) {
            if (dict.getValue().equals(risk.toString())) {
                return dict.getKey();
            }
        }
        return "";
    }

    /**
     * 获得用户证件类型明文
     * @param passport_type_id
     * @return
     */
    public String getPassportTypeName(Integer passport_type_id){
        List<Dictionary> dicts = dictionaryService.findDicByType(Const.DIC_CAT.PASSPORT);
        for (Dictionary dict : dicts) {
            if (dict.getValue().equals(passport_type_id.toString())) {
                return dict.getKey();
            }
        }
        return "";
    }
}
