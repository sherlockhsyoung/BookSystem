package com.HuangSiYang.BMS.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.HuangSiYang.BMS.util.DbUtil;

import java.sql.PreparedStatement;

public class LoginDao {

	private static DbUtil dbUtil = new DbUtil();

	public static int login(String get_id,String get_name) throws Exception{
		int flag = 1;//返回值
		Connection con = dbUtil.getCon();// 获取连接
		String sql = "select * from student";//获取学生表信息
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String id = rs.getString("s_id");
			String name = rs.getString("s_name");
			boolean result1 = get_id.equals(id);
			boolean result2 = get_name.equals(name);
			if(result1 == true && result2 == true ) //登录成功
				{
				flag = 2;
			}
		}
		if(flag == 1)//自动注册
			{
				addStudent(get_id,get_name);
			}
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		return flag;
	}
	//自动注册
	public static int addStudent(String get_id,String get_name) throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		String sql = "insert into student values(?,?)";//添加自动注册的学生的信息
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建Statement
		pstmt.setString(1, get_id);
		pstmt.setString(2, get_name);
		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt, con); // 关闭Statement和连接
		return result;//result为1则添加成功，反之添加失败
		
	}
}
