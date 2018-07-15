package com.HuangSiYang.BMS.service;

import com.HuangSiYang.BMS.dao.LoginDao;


public class LoginService {

	public  int loginService(String id,String name)throws Exception {
	
		int result = 0;
		//验证学号合法性   result == 0,学号不合法;result == 1，自动注册； result == 2，登录成功
		if(id.matches("\\d{10}") == true && id.matches("^3[1,2]1700.{4}") == true) {
			result = LoginDao.login(id,name);
		}
		return result;
	}
}
