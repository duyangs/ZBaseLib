package com.duyangs.zbase.util;

import android.util.Log;

/**
 * log工具
 * 
 * @author luxf 2015-7-15 下午1:46:50
 */
public final class LogUtil {
	/**
	 * 是否需要打印
	 * 
	 * logOn=false :屏蔽打印
	 * 
	 * logOn=true :显示打印
	 */
	private static boolean logOn = true;


	private LogUtil() {
		throw new UnsupportedOperationException("can't instantiate...");
	}

	public static class Builder{

		public Builder() {
		}

		public Builder setLogOn(boolean logOn){
			LogUtil.logOn = logOn;
			return this;
		}
	}

	private static String fotmatMsg(String msg){
		return "<<---" + msg + "--->>";
	}

	/**
	 * log.i 打印info类型的信息
	 * 
	 * @param tag 标记
	 * @param msg 显示信息
	 */
	public static void i(String tag, String msg) {
		if (logOn) {
			Log.i(tag, fotmatMsg(msg));
		}
	}

	/**
	 * log.w 打印warm类型的信息
	 * 
	 * @param tag 标记
	 * @param msg	显示信息
	 */
	public static void w(String tag, String msg) {
		if (logOn) {
			Log.w(tag, fotmatMsg(msg));
		}
	}

	/**
	 * log.r 打印error类型的信息
	 * 
	 * @param tag 标记
	 * @param msg 显示信息
	 */
	public static void e(String tag, String msg) {
		if (logOn) {
			Log.e(tag, fotmatMsg(msg));
		}
	}

	/**
	 * log.v 打印verbose类型的信息
	 * 
	 * @param tag	标记
	 * @param msg	显示信息
	 */
	public static void v(String tag, String msg) {
		if (logOn) {
			Log.v(tag, fotmatMsg(msg));
		}
	}

	/**
	 * log.d 打印debug类型的信息
	 * 
	 * @param tag	标记
	 * @param msg	显示信息
	 */
	public static void d(String tag, String msg) {
		if (logOn) {
			Log.d(tag, fotmatMsg(msg));
		}
	}
}
