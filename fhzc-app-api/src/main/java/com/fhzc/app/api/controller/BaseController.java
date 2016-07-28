package com.fhzc.app.api.controller;

import com.fhzc.app.api.tools.APIConstants;
import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.dao.mybatis.util.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected String basePath;
	protected Integer page;
	protected Integer size;

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

}
