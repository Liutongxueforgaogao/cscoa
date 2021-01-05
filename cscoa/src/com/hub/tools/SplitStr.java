package com.hub.tools;

public class SplitStr {

	//需求，截取最后一个点后面的字符串
	public static String getFileType(String input) {
		
		String[] split = input.split("\\.");
		int length1 = split.length;
		if(length1>0) {
			return split[length1-1];
		}else {
			return "-1";
		}
		
		
	}
	
	//需求，截取后10位
	public static String getTenStr(String input) {
		char[] charArray = input.toCharArray();
		char[] out = new char[10];
		int length = charArray.length;
		int index = 0;
		if(length>=10) {
			int delength = length-10;//需要截断的长度
			for (int i = delength; i < length; i++) {
				out[index++] = charArray[i];
			}	
		}
		
		return new String(out);
	}
	
	public static String splitSuggest(String strin){
		String[] str = null;
		String strout = "";
		str = strin.split("=");
		if(str.length>1){
			//strout=str[1]+"<span style='font-size:14px;float:right;color:#CFCFCF;'>"+str[0]+"</span>";
			strout=str[1]+"<span style='font-size:14px;color:#ADADAD;'>"+str[0]+"</span>";
		}
		return strout;
	}
	
}
