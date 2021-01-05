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
	//用于在Mybatis获取数据结果集时如何把数据库类型转换为对应的Java类型
	public String getResult(ResultSet resultSet, String columnName) throws SQLException {
		System.out.println("得到："+resultSet.getString(columnName));
		if(resultSet.getString(columnName) != null){
			return decimalFormat.format(Double.parseDouble(resultSet.getString(columnName)));			
		}else{
			return "";
		}
	}

	public String getResult(ResultSet resultSet, int i) throws SQLException {
		System.out.println("得到："+resultSet.getString(i));
		if(resultSet.getString(i) != null){
			return decimalFormat.format(Double.parseDouble(resultSet.getString(i)));
		}else{
			return "";
		}
	}

	public String getResult(CallableStatement callableStatement, int columnIndex)
			throws SQLException {
		System.out.println("得到："+callableStatement.getFloat(columnIndex));
		if(callableStatement.getString(columnIndex)!=null){
			return  decimalFormat.format(Double.parseDouble(callableStatement.getString(columnIndex)));  
		}
		return "";
	}
	
/*	private String tranferType(Float fl) {
		System.out.println("查看转换前的小数:"+fl);
		
		System.out.println("查看转换后的小数:"+decimalFormat.format(fl));
		return decimalFormat.format(fl); 
	}*/
	
	// 用于定义在Mybatis设置参数时该如何把Java类型的参数转换为对应的数据库类型
	public void setParameter(PreparedStatement ps, int i, String str,
			JdbcType jdbcType) throws SQLException {
		ps.setFloat(i,Float.parseFloat(str));
	}


	
}
