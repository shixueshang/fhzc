package com.fhzc.app.system.controller.business;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fhzc.app.dao.mybatis.util.Const;
import com.fhzc.app.system.commons.util.FileUtil;
import com.fhzc.app.system.commons.util.TextUtils;
import com.fhzc.app.system.controller.BaseController;

/**
 * Created by Double_J on 2016/7/29
 */
@Controller
@RequestMapping(value = "business/upload")
public class UploadController extends BaseController {
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile upload,HttpServletResponse response) {
		response.setCharacterEncoding("GBK");  
        PrintWriter out;
        String callback = request.getParameter("CKEditorFuncNum");
		try {
			if (!upload.isEmpty()) {
				String coverName = FileUtil.generatePictureName(upload);
				String coverPath = TextUtils.getConfig(Const.CONFIG_KEY_SYSTEM_IMAGE_SAVE_PATH, this);
				FileUtil.transferFile(coverPath, coverName, upload);
				out = response.getWriter();
				response.reset();
				out.println("<script type=\"text/javascript\">"); 
				out.println("window.parent.CKEDITOR.tools.callFunction("
	                        + callback + ",'" + coverPath+coverName+"',''" + ")"); // 相对路径用于显示图片  
				out.println("</script>");
				out.flush();
				out.close();
				return "upload Success"; 
			}else{
				return "upload Failed";
			}

		} catch (IOException e) {
			 return "upload Failed";
		}
    }
   
}
