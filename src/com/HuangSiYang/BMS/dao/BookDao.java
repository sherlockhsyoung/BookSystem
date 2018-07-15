package com.HuangSiYang.BMS.dao;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.HuangSiYang.BMS.entity.BookEntity;
import com.HuangSiYang.BMS.entity.ReaderEntity;
import com.HuangSiYang.BMS.util.DbUtil;

public class BookDao {
	
	private static DbUtil dbUtil = new DbUtil();
	//录入图书
	public  int addBook(BookEntity book) throws Exception {
			Connection con = dbUtil.getCon();// 获取数据库连接
			String sql = "insert into book_info values(?,?,?,?,?,?,null,null)";
			PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
			pstmt.setString(1,book.getId());
			pstmt.setString(2, book.getName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPress());
			pstmt.setInt(5, book.getStock_all());
			pstmt.setInt(6, book.getStock_now());
			int result = pstmt.executeUpdate();
			dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
			return result;
		}
	//删除图书
	public  int deleteBook(String id) throws Exception {
		Connection con = dbUtil.getCon();// 获取数据库连接
		String sql = "delete from book_info where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		try {
			pstmt.setString(1, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		return result;
	}
	//修改图书
	public  int modifyBook(BookEntity book) throws Exception {
		Connection con = dbUtil.getCon();// 获取数据库连接
		String sql = "update book_info set name=?,author=?,press=?,stock_all=?,stock_now=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1, book.getName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPress());
		pstmt.setInt(4, book.getStock_all());
		pstmt.setInt(5, book.getStock_now());
		pstmt.setString(6, book.getId());
		int result = pstmt.executeUpdate();
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		return result;
	}
	//查找图书
	public  ArrayList<BookEntity> searchBook(String nameGetted) throws Exception{
		
		Connection con = dbUtil.getCon();// 获取数据库连接
		String sql = "select * from book_info where name like ?";
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		pstmt.setString(1,"%"+nameGetted+"%");//模糊查找
		ResultSet rs = pstmt.executeQuery();//获得结果集rs
		ArrayList<BookEntity> list = new ArrayList<BookEntity>();
		int count = 0;//用来标记rs里是否有数据
		while(rs.next()){
			String	id = rs.getString("id");
			String name = rs.getString("name");
			String author = rs.getString("author");
			String press= rs.getString("press");
			int stock_all = rs.getInt("stock_all");	
			int stock_now = rs.getInt("stock_now");
			BookEntity book = new BookEntity(id,name,author,press,stock_all,stock_now);
			list.add(book);
			count++;
		}
		dbUtil.close(pstmt, con); // 关闭PreparedStatement和连接
		if (count == 0) {
			ArrayList<BookEntity> nullList = new ArrayList<BookEntity>();
			nullList = null;
			return nullList;//返回空集合
		}else {
			return list; 
		}
	}
	//借阅图书
	public int borrowBook(String bookId) throws Exception{
		int getSn = 0,flag = 0;//拿到的现库存，flag做为标记
		String getName = null;//拿到的书名
		Connection con = dbUtil.getCon();// 获取数据库连接
		String sql1= "select * from book_info where id= ?";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);// 创建PreparedStatement
		pstmt1.setString(1, bookId);
		ResultSet rs = pstmt1.executeQuery();//获取结果集rs
		while(rs.next()) {
			getSn = rs.getInt("stock_now");//拿到现库存
			getName = rs.getString("name");//拿到书名
		}
		if(getSn > 0)//现库存大于0才可以借书成功 
			{
				
				String sql2 = "update book_info set stock_now= ? where id= ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);// 创建PreparedStatement
				pstmt2.setInt(1,getSn-1);//现库存减1
				pstmt2.setString(2,bookId);
				flag = pstmt2.executeUpdate();//flag==1,则借书成功
				dbUtil.close(pstmt2); // 关闭PreparedStatement2	
				//同时添加读者借阅信息
				Calendar expirationTime = Calendar.getInstance();//获取到期时间
				expirationTime.add(Calendar.MINUTE, 10);//加10分钟
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String eTime = formatter.format(expirationTime.getTime());//把到期时间转换成字符串
				String state = "借阅中";//更新借阅状态
				ReaderEntity info = new ReaderEntity(bookId,getName,eTime,state);
				ReaderDao reader = new ReaderDao();
				int result = reader.addBorrowInfo(info);//添加读者借阅信息
				if(result == 1) {
					System.out.println("借阅成功，已添加该读者借阅信息");
				}else {
					System.out.println("借阅失败！");
			}
		}
		dbUtil.close(pstmt1,con); // 关闭接口和连接
		return flag;//flag为 0 则借书失败
	}
	//归还图书
	public int returnBook(String bookId) throws Exception {
		
		String eTime = null;//到期时间
		Connection con = dbUtil.getCon();// 获取连接
		ReaderEntity rm = ReaderDao.getReaderInfo();
		//查找出这个读者所借书的到期时间
		String sql1 = "select * from reader_info where reader_id = ? and book_id = ?";
		PreparedStatement pstmt1 = con.prepareStatement(sql1);// 创建PreparedStatement
		pstmt1.setString(1, rm.getReader_id());//拿到当前登录的读者的学号
		pstmt1.setString(2, bookId);//输入的书编号
		ResultSet rs1 = pstmt1.executeQuery();
		while(rs1.next()) {
			eTime = rs1.getString("expiration_time");
		}
		//判段借书是否超时
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");//格式转换
		Date date =sdf.parse(eTime);
		Calendar returnTime = Calendar.getInstance();//归还时间
		Calendar eTime2 = Calendar.getInstance();
		eTime2.setTime(date);//到期时间类型转换为Calendar,方便比较
		int result = returnTime.compareTo(eTime2);//比较两个时间前后
		System.out.println(result);
		int flag = 0;//返回值
		if(result == 1 || result == 0)//归还成功，无超时
		{
			//拿到现库存
			int getSn = 0;
			String sql2= "select * from book_info where id= ?";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);// 创建PreparedStatement
			pstmt2.setString(1, bookId);
			ResultSet rs2 = pstmt2.executeQuery();
			while(rs2.next()) {
				getSn = rs2.getInt("stock_now");//拿到现库存
			}
			//更新图书库存
			String sql3 = "update book_info set stock_now= ? where id= ?";
			PreparedStatement pstmt3 = con.prepareStatement(sql3);// 创建PreparedStatement
			pstmt3.setInt(1,getSn+1);//现库存加1
			pstmt3.setString(2,bookId);
			flag = pstmt3.executeUpdate();;//为1则归还成功
			//更新借阅表信息
			String sql4 = "update reader_info set state = ? where reader_id = ? and book_id = ?";
			PreparedStatement pstmt4 = con.prepareStatement(sql4);// 创建PreparedStatement
			pstmt4.setString(1, "已归还");//更新借阅状态
			pstmt4.setString(2, rm.getReader_id());
			pstmt4.setString(3, bookId);
			pstmt4.executeUpdate();
			dbUtil.close(pstmt1);//关掉接口1
			dbUtil.close(pstmt2);//关掉接口2
			dbUtil.close(pstmt3);//关掉接口3
			dbUtil.close(pstmt4);//关掉接口4
			
		}
		else //借阅超时，归还失败
		{
			String sql5 = "update reader_info set state = ? where reader_id = ? and book_id = ?";//更新借阅状态
			PreparedStatement pstmt5 = con.prepareStatement(sql5);// 创建PreparedStatement
			pstmt5.setString(1, "借阅超时,请到前台归还");
			pstmt5.setString(2, rm.getReader_id());
			pstmt5.setString(3, bookId);
			pstmt5.executeUpdate();
			dbUtil.close(pstmt5);
			flag = 0;
		}
		dbUtil.close(con);//关闭连接
		return flag;
	}
	//展示书籍
	public ArrayList<BookEntity> showBook() throws Exception{
		
		Connection con = dbUtil.getCon();// 获取连接
		String sql= "select * from book_info ";//获取图书信息表中内容
		PreparedStatement pstmt = con.prepareStatement(sql);// 创建PreparedStatement
		ResultSet rs = pstmt.executeQuery();//获得结果集rs
		ArrayList<BookEntity> list = new ArrayList<BookEntity>();
		while(rs.next()) {
			
			String	id = rs.getString("id");
			String name = rs.getString("name");
			String author = rs.getString("author");
			String press= rs.getString("press");
			int stock_all = rs.getInt("stock_all");	
			int stock_now = rs.getInt("stock_now");
			Timestamp add_time = rs.getTimestamp("add_time");
			Timestamp lastest_update = rs.getTimestamp("lastest_update");
			BookEntity book = new BookEntity(id,name,author,press,stock_all,stock_now,add_time,lastest_update);
			list.add(book);//将图书信息挨个放入集合list
			
		}
		return list;
	}
}

