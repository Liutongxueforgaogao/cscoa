package com.hub.tools;

import java.text.DecimalFormat;

public class NumberTool {
	/** 
     * Float ������λС�� 
     * @return 
     */ 
    public static Float keepTwoDecimalFloat(Float f) { 
	    DecimalFormat decimalFormat=new DecimalFormat(".00"); 
	    System.out.println("����ת����"+decimalFormat.format(f));
	    return Float.parseFloat(decimalFormat.format(f)); 
    } 
}
