package com.HuangSiYang.BMS.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import com.HuangSiYang.BMS.dao.BookDao;
import com.HuangSiYang.BMS.dao.ReaderDao;
import com.HuangSiYang.BMS.entity.BookEntity;
import com.HuangSiYang.BMS.entity.ReaderEntity;

public class ShowService {
	
	private ReaderDao rd = new ReaderDao();//调用Dao层
	private BookDao bd = new BookDao();
	//展示书籍
	public  DefaultTableModel showAllBook() throws Exception{
		
		ArrayList<BookEntity> list  = bd.showBook();//从Dao层拿到书籍信息
		Collections.sort(list,new AddTimeComparator());//按录入时间排序
		final Vector<Vector<Comparable>> rowData = new Vector<Vector<Comparable>>();
		final Vector<String> columnNames = new Vector<String>();		
		columnNames.add("录入时间");
		columnNames.add("编号");
		columnNames.add("书名");
		columnNames.add("作者");	//第一行
		columnNames.add("出版社");
		columnNames.add("总库存");
		columnNames.add("现库存");
		columnNames.add("最新更新");
		for (int i = 0 ;i < list.size() ; i++) {
				BookEntity book = list.get(i);
				final Vector<Comparable> info = new Vector<Comparable>();
				info.add(book.getAdd_time());
				info.add(book.getId());
				info.add(book.getName());
				info.add(book.getAuthor());	//添加数据
				info.add(book.getPress());
				info.add(book.getStock_all());
				info.add(book.getStock_now());
				info.add(book.getLastest_update());
				rowData.add(info);
			}
		DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
		return model;
	}
	//按录入时间从早到晚排序
	public class AddTimeComparator implements Comparator<Object> {    
        public int compare(Object object1, Object object2) {// 实现接口中的方法    
            BookEntity b1 = (BookEntity) object1; // 强制转换    
            BookEntity b2 = (BookEntity) object2;    
            Timestamp t1 = b1.getAdd_time();
            Timestamp t2 = b2.getAdd_time();
            Calendar c1 = Calendar.getInstance();
            c1.setTime(t1);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(t2);
            return c1.compareTo(c2); //c1小于c2，返回1；c1==c2,返回0；c1大于c2，返回-1
        }
      }  
	//查看书籍被学生借阅的情况
	public  DefaultTableModel showBookBorrowed(String bookId) throws Exception{
		
		ArrayList<ReaderEntity> list = rd.showBookBorrowed(bookId);
		final Vector<Vector<Comparable>> rowData = new Vector<Vector<Comparable>>();
		final Vector<String> columnNames = new Vector<String>();		
		columnNames.add("学生姓名");
		columnNames.add("书名");
		columnNames.add("借阅时间");		//第一行
		columnNames.add("应归还时间");
		columnNames.add("是否归还/借阅超时");
		for (int i = 0 ;i < list.size() ; i++) {
			ReaderEntity leader = list.get(i);
			final Vector<Comparable> info = new Vector<Comparable>();
			info.add(leader.getReader_name());
			info.add(leader.getBook_name());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String borrowTime = formatter.format(leader.getBorrow_time().getTime());//把借阅时间转换成字符串
			info.add(borrowTime);
			info.add(leader.getExpiration_time());
			info.add(leader.getState());
			rowData.add(info);
			}
		DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
		return model;
	}
	//查看某位学生借书情况
	public  DefaultTableModel showStudentBorrowed(String name) throws Exception{
		
		ArrayList<ReaderEntity> list = rd.showStudentBorrowed(name);
		final Vector<Vector<Comparable>> rowData = new Vector<Vector<Comparable>>();
		final Vector<String> columnNames = new Vector<String>();		
		columnNames.add("书籍编号");
		columnNames.add("书名");
		columnNames.add("借阅时间");		//第一行
		columnNames.add("应归还时间");
		columnNames.add("是否归还/借阅超时");
		for (int i = 0 ;i < list.size() ; i++) {
			ReaderEntity book = list.get(i);
			final Vector<Comparable> info = new Vector<Comparable>();
			info.add(book.getBook_id());
			info.add(book.getBook_name());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String borrowTime = formatter.format(book.getBorrow_time().getTime());//把借阅时间转换成字符串
			info.add(borrowTime);
			info.add(book.getExpiration_time());
			info.add(book.getState());
			rowData.add(info);
			}
		DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
		return model;
	}
}
