package com.HuangSiYang.BMS.entity;
import java.sql.Timestamp;
public class BookEntity {

	private String id;
	private String name;
	private String author;
	private String press;
	private int stock_all;
	private int stock_now;
	private Timestamp add_time;
	private Timestamp lastest_update; 
	/**
	 * 图书信息表实体
	 * @param id 图书编号
	 * @param name 书名
	 * @param author 作者
	 * @param press 出版社
	 * @param stock_all 总库存
	 * @param stock_now 现库存
	 * @param add_time 录入时间
	 * @param lastest_update 最新更新时间
	 */
	//构造方法
	public BookEntity(String id, String name, String author, String press, int stock_all, int stock_now) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.press = press;
		this.stock_all = stock_all;
		this.stock_now = stock_now;
	}

	public BookEntity(String id, String name, String author, String press, int stock_all, int stock_now, Timestamp add_time,
			Timestamp lastest_update) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.press = press;
		this.stock_all = stock_all;
		this.stock_now = stock_now;
		this.add_time = add_time;
		this.lastest_update = lastest_update;
	}

	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

	public Timestamp getLastest_update() {
		return lastest_update;
	}

	public void setLastest_update(Timestamp lastest_update) {
		this.lastest_update = lastest_update;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public int getStock_all() {
		return stock_all;
	}

	public void setStock_all(int stock_all) {
		this.stock_all = stock_all;
	}

	public int getStock_now() {
		return stock_now;
	}

	public void setStock_now(int stock_now) {
		this.stock_now = stock_now;
	}
}
