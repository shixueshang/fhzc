package com.fhzc.app.system.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
