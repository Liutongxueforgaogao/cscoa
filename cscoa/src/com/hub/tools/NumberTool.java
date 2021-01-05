package com.hub.tools;

import java.text.DecimalFormat;

public class NumberTool {
	/** 
     * Float 保留两位小数 
     * @return 
     */ 
    public static Float keepTwoDecimalFloat(Float f) { 
	    DecimalFormat decimalFormat=new DecimalFormat(".00"); 
	    System.out.println("数字转换后："+decimalFormat.format(f));
	    return Float.parseFloat(decimalFormat.format(f)); 
    } 
}
