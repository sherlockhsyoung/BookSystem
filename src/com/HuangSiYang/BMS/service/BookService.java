package com.HuangSiYang.BMS.service;

import java.util.ArrayList;
import java.util.Vector;
import com.HuangSiYang.BMS.util.VerifyUtil;
import javax.swing.table.DefaultTableModel;
import com.HuangSiYang.BMS.dao.BookDao;
import com.HuangSiYang.BMS.dao.ReaderDao;
import com.HuangSiYang.BMS.entity.BookEntity;

public class BookService {

		private BookDao  bd = new BookDao();//调用Dao层
		private ReaderDao rd = new ReaderDao();
		//录入书籍
		public  int addBookService(String id,String name,String author,String press,String stockAllS,String stockNowS)throws Exception {
			int result;//返回值
			if(VerifyUtil.verifyString(id) ==true||VerifyUtil.verifyString(name)==true||VerifyUtil.verifyString(author)==true||VerifyUtil.verifyString(press)==true||VerifyUtil.verifyString(stockAllS)==true||VerifyUtil.verifyString(stockNowS)==true)
				{
					result = 0;
				}//判断输入的字符是否为空
			else {
				
				int stockAll = Integer.parseInt(stockAllS);
				int stockNow = Integer.parseInt(stockNowS);
				if(stockAll<stockNow) {
					result = 0; 
				}
				else{
					BookEntity book = new BookEntity(id,name,author,press,stockAll,stockNow);
					result = bd.addBook(book);//录入书籍
				}
				
			}
			return result ;
		}
		//下架书籍
		public  int deleBookService(String id) throws Exception {
			int result;//返回值
			if(VerifyUtil.verifyString(id) ==true) {
				result = 0;
			}//判断输入的字符是否为空
			else{
				result = bd.deleteBook(id);//下架书籍
			}
			return result;
		}
		//修改书籍
		public  int modifyBookService(String id,String name,String author,String press,String stockAllS,String stockNowS)throws Exception {
			int result;//返回值
			if(VerifyUtil.verifyString(id) ==true||VerifyUtil.verifyString(name)==true||VerifyUtil.verifyString(author)==true||VerifyUtil.verifyString(press)==true||VerifyUtil.verifyString(stockAllS)==true||VerifyUtil.verifyString(stockNowS)==true)
			{
				result = 0;
			}//判断输入的字符是否为空
			else{
				int stockAll = Integer.parseInt(stockAllS);//强制类型转换
				int stockNow = Integer.parseInt(stockNowS);//强制类型转换
				BookEntity book = new BookEntity(id,name,author,press,stockAll,stockNow);
				result = bd.modifyBook(book);//修改书籍
			}
			return result ;
		}
		//查找书籍
		//判断输入是否为空以及是否找得到该书
		public  boolean judge(String name) throws Exception{
			
			if(VerifyUtil.verifyString(name) ==true) {
				return false;//判断输入的字符是否为空
			}else {
				 name = name.replaceAll("%", "\\\\%");//转义特殊字符%
				ArrayList<BookEntity> list = new ArrayList<BookEntity>();
				list = bd.searchBook(name);
				if(list == null) {
					
					return false;
				}else {
					return true;
				}
				
			}
		}
		//返回查找到的信息
		public  DefaultTableModel seachBookService(String name) throws Exception{
			
			ArrayList<BookEntity> list = new ArrayList<BookEntity>();//创建一个集合
			name = name.replaceAll("%", "\\\\%");//转义特殊字符%
			list = bd.searchBook(name);//传入找到的书的相关信息
			Vector<Vector<Comparable>> rowData = new Vector<Vector<Comparable>>();//使用动态数组Vector
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("编号");
			columnNames.add("书名");
			columnNames.add("作者");
			columnNames.add("出版社");
			columnNames.add("总库存");
			columnNames.add("现库存");
			for (int i = 0 ;i < list.size() ; i++) {
				BookEntity book = list.get(i);//拿出集合中每本书的信息
				Vector<Comparable> info = new Vector<Comparable>();
				info.add(book.getId());
				info.add(book.getName());
				info.add(book.getAuthor());
				info.add(book.getPress());
				info.add(book.getStock_all());
				info.add(book.getStock_now());
				rowData.add(info);
			}
			DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
			return model;
		}
		//添加登录的读者的信息
		public void loginInfoService(String id,String name)throws Exception{
			//输入不为空才执行
			if(VerifyUtil.verifyString(id) ==false&&VerifyUtil.verifyString(name)==false){
				try {
						rd.addReader(id, name);//添加最新进入系统的读者信息
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		//借阅
		public  int borrowBookService(String bookId) throws Exception {
			int result = 0;//返回值
			if(VerifyUtil.verifyString(bookId)==true) {
				result = 0;
			}//判断输入字符串是否为空
			else {
				try {
					result = bd.borrowBook(bookId);//借阅书籍
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
		//归还
		public  int returnBookService(String bookId) throws Exception {
			int result;//返回值
			if(VerifyUtil.verifyString(bookId)==true) {
				result = 0;
			}//判断输入的字符串是否为空
			else {
				result = bd.returnBook(bookId);//归还书籍
				if(result == 1) {
					System.out.println("归还成功！");
				}
			}
			return result;
		}
}
