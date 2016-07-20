package com.fhzc.app.system.controller;

import com.fhzc.app.dao.mybatis.page.PageableResult;

import java.util.List;
import java.util.Map;

public class AjaxJson {

    private boolean success = false;
    private PageableResult<Map<String, Object>> page;
    private List<Map<String, Object>> data;
    private Map<String, Object> map;
    private String message = "";
    private Object children;

    public AjaxJson() {
        super();
    }

    public AjaxJson(boolean success) {
        this.success = success;
    }

    public AjaxJson(boolean success, PageableResult<Map<String, Object>> page) {
        this.success = success;
        this.page = page;
    }

    public AjaxJson(boolean success, List<Map<String, Object>> data) {
        this.success = success;
        this.data = data;
    }

    public AjaxJson(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AjaxJson(boolean success, Map<String, Object> map) {
        this.success = success;
        this.map = map;
    }

    public AjaxJson(boolean success, Object children) {
        this.success = success;
        this.children = children;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PageableResult<Map<String, Object>> getPage() {
        return page;
    }

    public void setPage(PageableResult<Map<String, Object>> page) {
        this.page = page;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }

}