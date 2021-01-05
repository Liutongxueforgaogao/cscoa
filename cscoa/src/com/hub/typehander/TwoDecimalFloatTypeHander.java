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

public class TwoDecimalFloatTypeHander implements  TypeHandler<String> {
	private static DecimalFormat decimalFormat=new DecimalFormat(".00");
	//������Mybatis��ȡ���ݽ����ʱ��ΰ����ݿ�����ת��Ϊ��Ӧ��Java����
	public String getResult(ResultSet resultSet, String columnName) throws SQLException {
		System.out.println("�õ���"+resultSet.getString(columnName));
		if(resultSet.getString(columnName) != null){
			return decimalFormat.format(Double.parseDouble(resultSet.getString(columnName)));			
		}else{
			return "";
		}
	}

	public String getResult(ResultSet resultSet, int i) throws SQLException {
		System.out.println("�õ���"+resultSet.getString(i));
		if(resultSet.getString(i) != null){
			return decimalFormat.format(Double.parseDouble(resultSet.getString(i)));
		}else{
			return "";
		}
	}

	public String getResult(CallableStatement callableStatement, int columnIndex)
			throws SQLException {
		System.out.println("�õ���"+callableStatement.getFloat(columnIndex));
		if(callableStatement.getString(columnIndex)!=null){
			return  decimalFormat.format(Double.parseDouble(callableStatement.getString(columnIndex)));  
		}
		return "";
	}
	
/*	private String tranferType(Float fl) {
		System.out.println("�鿴ת��ǰ��С��:"+fl);
		
		System.out.println("�鿴ת�����С��:"+decimalFormat.format(fl));
		return decimalFormat.format(fl); 
	}*/
	
	// ���ڶ�����Mybatis���ò���ʱ����ΰ�Java���͵Ĳ���ת��Ϊ��Ӧ�����ݿ�����
	public void setParameter(PreparedStatement ps, int i, String str,
			JdbcType jdbcType) throws SQLException {
		ps.setFloat(i,Float.parseFloat(str));
	}


	
}
