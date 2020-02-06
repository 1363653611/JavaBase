package com.zbcn.common.utils;

import java.util.UUID;

/**
 *  @title UUIDUtils
 *  @Description uuid 工具类
 *  @author zbcn8
 *  @Date 2020/1/15 16:20
 */
public class UUIDUtils {

	/**
	 * 获取随机uuid
	 * @return
	 */
	public static String getUid(){
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取全局uid hash值
	 * @return
	 */
	public static int getUidInt(){
		return getUid().hashCode();
	}
}
