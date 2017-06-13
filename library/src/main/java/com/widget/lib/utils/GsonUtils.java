package com.widget.lib.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {

    private static Gson gson = new Gson();

	/** 将对象转成string */
	public static String toJson(Object obj) {
		try {
			return gson.toJson(obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 将string转成对象 */
	public static <T> T fromJson(String str, Type type) {
		try {
			return gson.fromJson(str, type);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 将string转成对象 */
	public static <T> T fromJson(String str, Class<T> type) {
		try {
			return gson.fromJson(str, type);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}