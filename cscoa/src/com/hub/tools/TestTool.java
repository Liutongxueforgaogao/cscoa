
package com.hub.tools;

public class TestTool {

	public static void main(String[] args) {
//		String tenStr = SplitStr.getTenStr("FP201709091234");
//		String fileType = SplitStr.getFileType("123213.pdf");
//		System.out.println(fileType);
//		
		
		
		
//	String test1 = "差旅费国内差旅费";
//	String result = "";
//	char[] charArray = test1.toCharArray();
//	for (int i = 0; i < charArray.length; i++) {
//		if(i<5) {
//			result = result+charArray[i];
//		}else {
//			break;
//		}
//	}
//	System.out.println(result);
		
		
		
		String checkids="2321,213,1223,1312,321,3,213,1321,";
		//去除最后一个点
		String clearLastSpot = clearLastSpot(checkids);
		System.out.println(clearLastSpot);
		//将每一个单号都加上单引号
		String[] split = clearLastSpot.split(",");
		String result = "";
		for (int i = 0; i < split.length; i++) {
			result=result + "'"+split[i]+"',";
		}
		result = clearLastSpot(result);
		checkids = "("+result+")";
		System.out.println(checkids);
	}	
	//去除最后一个逗号
	public static String clearLastSpot(String checkids) {
		char[] charArray = checkids.toCharArray();
		String result = "";
		if(charArray.length>0) {
			result = checkids.substring(0, charArray.length-1);
		}
		return result;
	}
	
	
	
}








