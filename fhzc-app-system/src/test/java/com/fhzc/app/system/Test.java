package com.fhzc.app.system;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
* Created by lihongde on 2016/7/7 11:40
*/
public class Test {
    public static void main(String[] args){
        Date date=new Date();//取时间
        Calendar calenda = new GregorianCalendar();
        calenda.setTime(date);
        calenda.add(calenda.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calenda.getTime();
        System.out.println(date);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        System.out.println("开始时间："+calendar.getTime());
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        System.out.println("结束时间："+calendar.getTime());
    }
}
