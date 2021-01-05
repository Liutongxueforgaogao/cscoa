package com.hub.typehander;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.apache.ibatis.type.FloatTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.hub.tools.NumberTool;

public class SplitFiveTypeHander implements  TypeHandler<String> {

	//������Mybatis��ȡ���ݽ����ʱ��ΰ����ݿ�����ת��Ϊ��Ӧ��Java����
	public String getResult(ResultSet resultSet, String columnName) throws SQLException {
		if(resultSet.getString(columnName) != null){
			
			String test1 = resultSet.getString(columnName);
			String result = "";
			char[] charArray = test1.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				if(i<5) {
					result = result+charArray[i];
				}else {
					break;
				}
			}
			return result;			
		}else{
			return "";
		}
	}

	public String getResult(ResultSet resultSet, int i) throws SQLException {
		if(resultSet.getString(i) != null){
			
			String test1 = resultSet.getString(i);
			String result = "";
			char[] charArray = test1.toCharArray();
			for (int j = 0; j < charArray.length; j++) {
				if(i<5) {
					result = result+charArray[j];
				}else {
					break;
				}
			}
			return result;
		}else{
			return "";
		}
	}

	public String getResult(CallableStatement callableStatement, int columnIndex)
			throws SQLException {
		if(callableStatement.getString(columnIndex)!=null){
			String test1 = callableStatement.getString(columnIndex);
			String result = "";
			char[] charArray = test1.toCharArray();
			for (int j = 0; j < charArray.length; j++) {
				if(j<5) {
					result = result+charArray[j];
				}else {
					break;
				}
			}
			
			return  result;  
		}else {
			return "";
		}
	}
	
	
	// ���ڶ�����Mybatis���ò���ʱ����ΰ�Java���͵Ĳ���ת��Ϊ��Ӧ�����ݿ�����
	public void setParameter(PreparedStatement ps, int i, String str,
			JdbcType jdbcType) throws SQLException {
		ps.setString(i, str);
	}


	
}
