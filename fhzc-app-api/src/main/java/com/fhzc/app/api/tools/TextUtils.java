package com.fhzc.app.api.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    private static final String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
    private static final String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
    private static final String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	
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

        Pattern p_script= Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style= Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html= Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

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
    public static String generateRandomString(int length){

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ;i<length;i++){
            int currentLetter = (int) (Math.random() * 10);
            System.out.println("currentLetter is = " + currentLetter);
            sb.append(currentLetter);
        }
        return sb.toString();
    }

}
