package com.HuangSiYang.BMS.entity;

public class StudentEntity {

	/**学生信息模型
	 * @param s_id 学号
	 * @param s_name 姓名
	 */
	private String s_id;
	private String s_name;
	//构造方法
	public StudentEntity(String s_id, String s_name) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}	
}	

	