package com.fhzc.app.system.commons.page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihongde on 2016/7/6 19:35
 */
public class PageHelper {
    public static PageModel getPageModel(HttpServletRequest request, PageableResult pageableResult) {
        PageModel pageModel = new PageModel();
        if (pageableResult != null) {
            pageModel.set(pageableResult);
        }
        pageModel.setRequestUrl(request.getRequestURI());

        Map pageParams = new HashMap<String, Object>();
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            if (key.equals("page") || key.equals("size")) {
                continue;
            }
            String[] ss = params.get(key);
            if (ss == null || ss.length != 1) {
                continue;
            }
            pageParams.put(key, ss[0]);
        }
        pageModel.setPageParams(pageParams);
        return pageModel;
    }
}
