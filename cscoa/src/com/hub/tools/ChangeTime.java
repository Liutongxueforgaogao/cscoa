package com.hub.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeTime {
	
	
	public static String formatTime(String currentTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		//currentTime = "2017-12-19 00:00:00";
		Date parse = null;
		try {
			parse = formatter.parse(currentTime);
		} catch (ParseException e) {
			throw new RuntimeException("时间转换异常："+currentTime);
		}
		String out = formatter.format(parse);
		return out;
	}
}
