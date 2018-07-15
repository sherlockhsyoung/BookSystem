package com.HuangSiYang.BMS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.HuangSiYang.BMS.entity.ReaderEntity;
import com.HuangSiYang.BMS.util.DbUtil;

public class ReaderDao {

	private static DbUtil dbUtil = new DbUtil();
	//借阅成功时，增加读者信息
	public  int addBorrowInfo(ReaderEntity reader)throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		ReaderEntity rm = getReaderInfo();//找出最新登录的读者,拿到该读者的学号和名字
		//添加该读者的借阅信息
		String sql =  "insert into reader_info values(?,?,?,?,null,?,null,?,null)";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1, rm.getReader_id());
		pstmt.setString(2, rm.getReader_name());
		pstmt.setString(3, reader.getBook_id());
		pstmt.setString(4, reader.getBook_name());
		pstmt.setString(5, reader.getExpiration_time());
		pstmt.setString(6, reader.getState());
		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		return result;//返回是否添加成功
		
	}
	//录入登录的读者信息
	public  int addReader(String id,String name) throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		String sql = "insert into login_info values(?,?,null)";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1, id);
		pstmt.setString(2,name);
		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		return result;
	}
	//拿到最新登录的读者的学号和姓名
	public static ReaderEntity getReaderInfo()throws Exception{
		
		String reader_id = null,reader_name = null;
		Connection con = dbUtil.getCon();// 获取连接
		//找出最新登录的读者
		String sql1= "select * from login_info ";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);// 创建PreparedStatement
		ResultSet rs1 = pstmt1.executeQuery();//获得结果集rs
		ArrayList<Integer> array = new ArrayList<Integer>();
		while(rs1.next()) {
			int flagGetted = rs1.getInt("flag");
			array.add(flagGetted);
			}
		int flag = array.get(array.size()-1);//最新登录的读者的标记
		//拿到该读者的学号和名字
		String sql2= "select * from login_info where flag = ?";
		PreparedStatement pstmt2 = con.prepareStatement(sql2);// 创建PreparedStatement
		pstmt2.setInt(1, flag);
		ResultSet rs2 = pstmt1.executeQuery();
		while(rs2.next()) {
				reader_id = rs2.getString("l_id");
				reader_name = rs2.getString("l_name");
			}
		dbUtil.close(pstmt1);
		dbUtil.close(pstmt2, con);//关闭接口和连接
		ReaderEntity model = new ReaderEntity(reader_id,reader_name);
		return model;
		
	}
	//查看书籍被学生借阅情况
	public ArrayList<ReaderEntity> showBookBorrowed(String bookId)throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		String sql= "select * from reader_info where book_id= ?";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1, bookId);
		ResultSet rs = pstmt.executeQuery();//获取结果集rs
		ArrayList<ReaderEntity> list = new ArrayList<ReaderEntity>();//创建个集合，返回值
		while(rs.next()) {
			
			String leaderId = rs.getString("reader_id");
			String leaderName = rs.getString("reader_name");
			String bookName = rs.getString("book_name");
			Timestamp bTime = rs.getTimestamp("borrow_time"); 
			String eTime = rs.getString("expiration_time");
			String state = rs.getString("state"); 
			ReaderEntity leader = new ReaderEntity(leaderId,leaderName,bookName,bTime,eTime,state);
			list.add(leader);//挨个添加信息到集合list
			
		}
		dbUtil.close(pstmt, con);//关闭接口和连接
		return list;
	} 
	//查看学生借阅书籍情况
	public ArrayList<ReaderEntity> showStudentBorrowed(String name)throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		String sql= "select * from reader_info where reader_name = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();//获取结果集rs
		ArrayList<ReaderEntity> list = new ArrayList<ReaderEntity>();//创建个集合，返回值
		while(rs.next()) {
			String bookId = rs.getString("book_id");
			String bookName = rs.getString("book_name");
			Timestamp bTime = rs.getTimestamp("borrow_time"); 
			String eTime = rs.getString("expiration_time");
			String state = rs.getString("state"); 
			ReaderEntity book = new ReaderEntity(bookId,bookName,bTime,eTime,state);
			list.add(book);//挨个添加信息到集合list
			
		}
		dbUtil.close(pstmt, con);//关闭接口和连接
		return list;
	} 
}
