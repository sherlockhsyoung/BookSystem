package com.HuangSiYang.BMS.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


	public class DbUtil{
		
		private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/book?useUnicode=true&characterEncoding=utf-8"; 
		//数据库地址
		private static String dbUserName="root";
		//密码
		private static String dbPassword="1234";
		//驱动
		private static String  jdbcName="com.mysql.jdbc.Driver";
		
		/**
		 * 获取数据库连接
		 * @return
		 * @throws Exception
		 */
		public Connection getCon()throws Exception{
			
			Class.forName(jdbcName);
			Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
			return con;
		}
		
		/**
		 * 关闭PreparedStatement和连接
		 * @param pstmt
		 * @param con
		 * @throws Exception
		 */
		public void close (PreparedStatement pstmt,Connection con)throws Exception{
			if(pstmt!=null) {
				pstmt.close();
				if(con!=null) {
					con.close();
				}
			}
			
		}
		public void close(PreparedStatement pstmt)throws Exception{
			
			pstmt.close();
		}
		public void close(Connection con)throws Exception{
			con.close();
		}
}

