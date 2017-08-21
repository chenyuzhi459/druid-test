package io.sugo.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;

import java.io.File;

/**
 * Created by chenyuzhi on 17-8-16.
 */
public class TestUtil {
	//static{PropertyConfigurator.configure("src/main/resources/log4j.properties");}
	static Logger logger = Logger.getLogger(TestUtil.class);
	public static void main(String[] args){
		File file = new File("src/main/resources/query.properties");
		System.out.println(file.exists());

		logger.info(new RuntimeException("404").getStackTrace());
	}
}
