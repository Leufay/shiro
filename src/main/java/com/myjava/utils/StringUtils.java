package com.myjava.utils;

import java.util.HashSet;
import java.util.Set;

public class StringUtils {
	/**
	 * 字符串数组-->Long数组
	 * @param strs
	 * @return
	 */
	public static Long[] convert(String[] strs){
		Long[] nums = new Long[strs.length] ;
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Long.parseLong(strs[i]) ;
		}
		return nums;
	}
	
	/**
	 * 字符串数组-->以分隔符分隔的字符串
	 * @param strs
	 * @param seperator 分隔符
	 * @return
	 */
	public static String convertToString(String[] strs , String seperator){
		String result = "" ;
		for (int i = 0; i < strs.length; i++) {
			if(i==strs.length-1){
				result += strs[i] ;
			}else{
				result += strs[i]+seperator ;
			}
		}
		return result;
	}
	
	/**
	 * 将分隔符分隔的字符串变成Set
	 * @param str
	 * @param separator
	 * @return
	 */
	public static Set<String> convertStringToSet(String str , String separator){
		Set<String> strs = new HashSet<>() ;
		String[] strArray = str.split(separator) ;
		for (int i = 0; i < strArray.length; i++) {
			strs.add(strArray[i]) ;
		}
		return strs ;
	}
	
}
