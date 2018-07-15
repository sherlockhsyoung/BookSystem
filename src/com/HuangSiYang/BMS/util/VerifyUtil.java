package com.HuangSiYang.BMS.util;

public class VerifyUtil {
	//验证输入的字符串是否为空
	public static boolean verifyString(String s) {
		if(s.equals(null)||s.equals("")) {
			return true;
		}
		return false;
	}
	
}
