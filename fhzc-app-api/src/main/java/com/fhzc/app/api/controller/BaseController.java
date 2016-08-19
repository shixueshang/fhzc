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

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;


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

    /**
     * 获得当前用户信息
     * @return
     */
    public User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User user  = (User)subject.getSession().getAttribute("user");
        return user;
    }

	/**
	 * 根据字典值和类型获得显示值
	 * @param value 字典值
     * @param cat 类型
	 * @return
     */
    public String getDicName(Integer value, String cat){
        if(value != null){
            List<Dictionary> dicts = dictionaryService.findDicByType(cat);
            for (Dictionary dict : dicts) {
                if (dict.getValue().equals(value.toString())) {
                    return dict.getKey();
                }
            }
        }
        return "";
    }

    /**
     * 设置用户头像路径
     * @param user
     * @return
     */
	public User setUserAvatarPath(User user){
        if(user.getAvatar() != null && !user.getAvatar().contains("http")) {
            user.setAvatar(basePath + user.getAvatar());
        }
		return user;
	}
}
