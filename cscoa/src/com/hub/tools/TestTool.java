
package com.hub.tools;

public class TestTool {

	public static void main(String[] args) {
//		String tenStr = SplitStr.getTenStr("FP201709091234");
//		String fileType = SplitStr.getFileType("123213.pdf");
//		System.out.println(fileType);
//		
		
		
		
//	String test1 = "���÷ѹ��ڲ��÷�";
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
		//ȥ�����һ����
		String clearLastSpot = clearLastSpot(checkids);
		System.out.println(clearLastSpot);
		//��ÿһ�����Ŷ����ϵ�����
		String[] split = clearLastSpot.split(",");
		String result = "";
		for (int i = 0; i < split.length; i++) {
			result=result + "'"+split[i]+"',";
		}
		result = clearLastSpot(result);
		checkids = "("+result+")";
		System.out.println(checkids);
	}	
	//ȥ�����һ������
	public static String clearLastSpot(String checkids) {
		char[] charArray = checkids.toCharArray();
		String result = "";
		if(charArray.length>0) {
			result = checkids.substring(0, charArray.length-1);
		}
		return result;
	}
	
	
	
}








