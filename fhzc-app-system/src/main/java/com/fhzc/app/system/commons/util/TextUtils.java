package com.fhzc.app.system.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class TextUtils {
	
	/**
	 * 从Properties文件中读取配置项
	 * @param key
	 * @param object
	 * @return
	 */
	public static String getConfig(String key,Object object){
		 InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream("system.properties");
		  Properties p = new Properties();
		  try {
			  p.load(inputStream);
		  } catch (IOException e1) {
			  e1.printStackTrace();
		  }
		  
		return p.getProperty(key);
	}
	/**
	 * 获取纯文本
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
         
        Pattern p_script= Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style= Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html= Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串 
    } 
	
	/**
	 * 获取扩展名
	 * @param fileName
	 * @param defaultExtName
	 * @return
	 */
	public static String getFileExtName(String fileName, String defaultExtName) {
		if (fileName.lastIndexOf(".") == -1) {
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
			return prefix;
		}
		return defaultExtName;
	}
	
	/**
	 * 字符集转换
	 * @param text
	 * @return
	 */
	public static String characterConversion(String text){
		if(text != null && !text.equals("")){
			try {
				text = URLDecoder.decode(text.trim().replaceAll("%", "%25"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return text;
	}

    /**
     * 按新尺寸返回图片名字
     * @param name
     * @param width
     * @param height
     * @return
     */
    public static String newPictureName(String name, int width, int height){
        String suffix = name.substring(name.lastIndexOf(".") + 1);
        String preix = name.substring(0, name.lastIndexOf(".")) ;
        return preix + "_" + width + "x" + height + "." + suffix;
    }


    
    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
     try {
      Integer.parseInt(value);
      return true;
     } catch (NumberFormatException e) {
      return false;
     }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
     try {
      Double.parseDouble(value);
      if (value.contains("."))
       return true;
      return false;
     } catch (NumberFormatException e) {
      return false;
     }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
     return isInteger(value) || isDouble(value);
    }
    
    /**
     * 判断字符串是否是日期
     */
    public static boolean isValidDate(String str) {
	     boolean convertSuccess=true;
	     	// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        try {
	        	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
	           format.setLenient(false);
	           format.parse(str);
	         } catch (ParseException e) {
	        	 // e.printStackTrace();
	        	 // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
	        	 convertSuccess=false;
	        } 
	        return convertSuccess;
    	}
    
	/**
	 * 验证时间字符串格式输入是否正确
	 * @param timeStr
	 * @return
	 */
	public static boolean valiDateTimeWithShortFormat(String timeStr) {
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(timeStr);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m-1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}
    
    /**
     * 字符串直接转换成整数，如果为空，直接为0
     */
    public static int StringtoInteger(String value) {
    	if(isInteger(value)){
    		return Integer.parseInt(value);
    	}
    	else if(isDouble(value)){
    		return Math.round(Float.parseFloat(value));
    	}
    	else{
    		return 0;
    	}
    }
    
    /**
     * 字符串直接转换成整数，并放大1万倍，如果为空，直接为0
     */
    public static int Stringto10kInteger(String value) {
    	if(isInteger(value)){
    		return Integer.parseInt(value)*10000;
    	}
    	else if(isDouble(value)){
    		return Math.round(Float.parseFloat(value)*10000);
    	}
    	else{
    		return 0;
    	}
    }
    
    /**
     * 手机号转换
     */
    public static String IntToDouble(String value){
    	if(value.contains(".")||value.contains("e")){
    	 	Double temPhone = Double.parseDouble(value);
      	 	DecimalFormat df = new DecimalFormat();
      	 	return df.format(temPhone).replace(",", "");
    	}else{
    		return value;
    	}
    }
  

    /**
     * 截取小数点之前的整数，适用于常规格式的数字转换
     */
    public static int FloatToInt(String value){
    	return Integer.parseInt((value.contains(".")?value.substring(0,value.indexOf(".")):value));
    }
    
    
    /**
     * 检查日期
     * @param rowNum 行数
     * @param colNum	列数
     * @param errorMessage	错误消息
     * @return
     */
    public static List<Object[]> checkDateString(int rowNum, int colNum, Object value, boolean allowEmpty){
    	List<Object[]> checkResult = new LinkedList<Object[]>();
    	if(!allowEmpty){
	    	if(value == null || value.toString().trim().equals("") ){
	    		String errorMessage = "不能为空!";
	    		
	    		return setErrorMessage(rowNum,colNum,errorMessage);
	    	}
	    	
    	}
    	if(value != null && ! value.toString().trim().equals("")){
    		if(!valiDateTimeWithShortFormat(value.toString())){
	    		String errorMessage = value + "不是合法的日期格式 年-月-日!";
	    		return setErrorMessage(rowNum,colNum,errorMessage);
    		}
 		
    	}
    	
    	return checkResult;

    }
    
    
    /**
     * 检查数字
     * @param rowNum 行数
     * @param colNum	列数
     * @param errorMessage	错误消息
     * @return
     */
    public static List<Object[]> checkNumber(int rowNum, int colNum, Object value, boolean allowEmpty){
    	List<Object[]> checkResult = new LinkedList<Object[]>();
    	if(!allowEmpty){
	    	if(value == null || value.toString().trim().equals("") ){
	    		String errorMessage = "不能为空!";
	    		
	    		return setErrorMessage(rowNum,colNum,errorMessage);
	    	}
	    	
    	}
    	if(value != null && ! value.toString().trim().equals("")){
    		if(!isNumber(value.toString())){
	    		String errorMessage = value + " 不是数字!";
	    		return setErrorMessage(rowNum,colNum,errorMessage);
    		}
 		
    	}
    	
    	return checkResult;

    }
    
    /**
     * 检查字符串
     * @param rowNum 行数
     * @param colNum	列数
     * @param errorMessage	错误消息
     * @return
     */
    public static List<Object[]> checkEmptyString(int rowNum, int colNum, Object value){
    
    	if(value == null || value.toString().trim().equals("") ){
    		String errorMessage = "不能为空!";
    		
    		return setErrorMessage(rowNum,colNum,errorMessage);
    	}
    	else{
    		return new LinkedList<Object[]>();
    	}

    }

    
    /**
     * 设置错误的消息
     * @param rowNum 行数
     * @param colNum	列数
     * @param errorMessage	错误消息
     * @return
     */
    public static List<Object[]> setErrorMessage(int rowNum, int colNum, String errorMessage){
		errorMessage = "第" + String.valueOf(rowNum) + "行第"+ String.valueOf(colNum) +"列," + errorMessage ;
		return setErrorMessage(errorMessage);

    }

    
    /**
     * 设置错误的消息
     * @param errorMessage
     * @return
     */
    public static List<Object[]> setErrorMessage(String errorMessage){
		List<Object[]> errordata  = new LinkedList<Object[]>();
		//errordata.add(new Object[]{"error","第" + String.valueOf(i+1) + "行第"+ String.valueOf(6) +"列产品信息为空!"});
		errordata.add(new Object[]{"error",errorMessage});
		return errordata;

    }
    
    /**
     * 检验手机号码
     * @param phone
     * @return
     */
    public static List<Object[]> validPhoneNum(int rowNum, int colNum, String phone, boolean allowEmpty){
    	List<Object[]> checkResult = new LinkedList<Object[]>();
    	Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
		Matcher matcher = pattern.matcher(phone);
    	if(!allowEmpty){
    		if(phone == null || phone.trim().equals("")){
    			String errorMessage = "手机号码不能为空";
    			return setErrorMessage(rowNum,colNum,errorMessage);
    		}
    	}
    	if((phone != null) && (!phone.trim().equals(""))){
    		if((phone.trim().length() != 11) ||(! matcher.matches())){
        		String errorMessage = "手机号码:"+ phone +" 格式有误!";
        		return setErrorMessage(rowNum,colNum,errorMessage);
        	}
    	}
    	return checkResult;	

    } 
    
    /**
     * 校验excel表头
     * @param title
     * @param str
     * @return
     */
    public static boolean validWorkbookTitle(Object title ,String str){
    	if(title != null){
	    	if(title.toString().contains(str)){
	        	return true;
	        }else{
	    		return false;
	    	}
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * 校验上传文件的类型
     * @param multiFile
     * @return
     */
    public static boolean validDocType(MultipartFile multiFile){
    	String fileName = multiFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		if("xlsx".equals(suffix)||"xls".equals(suffix)){
			return true;
		}else{
			return false;
		}
    }

    public static String generateRandomString(int length){

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ;i<length;i++){
            int currentLetter = (int) (Math.random() * 10);
            sb.append(currentLetter);
        }
        return sb.toString();
    }
}
