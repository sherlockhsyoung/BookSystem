package com.HuangSiYang.BMS.entity;

import java.sql.Timestamp;

public class ReaderEntity {

	/**读者借阅信息表实体
	 *
	 */
	private String reader_id;//读者学号
	private String reader_name;//读者姓名
	private String book_id;//借阅书籍的编号
	private String book_name;//借阅书籍的名字
	private Timestamp borrow_time;//借阅的时间
	private String expiration_time;//到期时间
	private Timestamp return_time;//归还时间
	private String state;//借阅状态 /是否超时
	//构造方法
	public ReaderEntity(String book_id, String book_name, String expiration_time,String state) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.expiration_time = expiration_time;
		this.state = state;
	}
	public ReaderEntity(String book_id, String book_name, Timestamp borrow_time, String expiration_time,
			String state) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.borrow_time = borrow_time;
		this.expiration_time = expiration_time;
		this.state = state;
	}
	public ReaderEntity(String reader_id, String reader_name, String book_name, Timestamp borrow_time,
			String expiration_time, String state) {
		super();
		this.reader_id = reader_id;
		this.reader_name = reader_name;
		this.book_name = book_name;
		this.borrow_time = borrow_time;
		this.expiration_time = expiration_time;
		this.state = state;
	}
	public ReaderEntity(String reader_id, String reader_name) {
		super();
		this.reader_id = reader_id;
		this.reader_name = reader_name;
	}
	public String getReader_id() {
		return reader_id;
	}

	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}

	public String getReader_name() {
		return reader_name;
	}

	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public Timestamp getBorrow_time() {
		return borrow_time;
	}

	public void setBorrow_time(Timestamp borrow_time) {
		this.borrow_time = borrow_time;
	}

	public String getExpiration_time() {
		return expiration_time;
	}

	public void setExpiration_time(String expiration_time) {
		this.expiration_time = expiration_time;
	}

	public Timestamp getReturn_time() {
		return return_time;
	}

	public void setReturn_time(Timestamp return_time) {
		this.return_time = return_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
		
}
