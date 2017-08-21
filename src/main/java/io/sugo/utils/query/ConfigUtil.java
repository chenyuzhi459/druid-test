package io.sugo.utils.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenyuzhi on 17-8-12.
 */
public class ConfigUtil {
	private static String requestUrl;
	private static String dataSource;
	private static String intervals;
	private static String granularity;
	private static String redisHostsAndPorts;
	private static String initLookupUrl;
	private static String createLookupUrl;
	private static boolean redisClusterMode;
	private static Properties config;
	public static void init(){
		try {
			config = new Properties();
			config.load(new FileInputStream("src/main/resources/query.properties"));
			requestUrl = config.getProperty("requestUrl");
			dataSource = config.getProperty("dataSource");
			intervals = config.getProperty("intervals");
			granularity = config.getProperty("granularity");
			redisHostsAndPorts = config.getProperty("redisHostsAndPorts");
			redisClusterMode = Boolean.parseBoolean(config.getProperty("redisClusterMode"));
			initLookupUrl = config.getProperty("initLookupUrl");
			createLookupUrl = config.getProperty("createLookupUrl");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static String getRequestUrl() {
		if(null == config){
			init();
		}
		return requestUrl;
	}

	public static String getDataSource() {
		if(null == config){
			init();
		}
		return dataSource;
	}


	public static String getIntervals() {
		if(null == config){
			init();
		}
		return intervals;
	}



	public static String getGranularity() {
		if(null == config){
			init();
		}
		return granularity;
	}

	public static String getRedisHostsAndPorts() {
		if(null == config){
			init();
		}
		return redisHostsAndPorts;
	}

	public static boolean isRedisClusterMode() {
		if(null == config){
			init();
		}
		return redisClusterMode;
	}

	public static String getInitLookupUrl() {
		if(null == config){
			init();
		}
		return initLookupUrl;
	}

	public static String getCreateLookupUrl() {
		if(null == config){
			init();
		}
		return createLookupUrl;
	}

	public static void main(String[] args){
		System.out.println(getRequestUrl());
		System.out.println(getDataSource());
		System.out.println(getIntervals());
		System.out.println(getGranularity());
		System.out.println(getRedisHostsAndPorts());
		System.out.println(isRedisClusterMode());
		System.out.println(getInitLookupUrl());
		System.out.println(getCreateLookupUrl());
	}
}
