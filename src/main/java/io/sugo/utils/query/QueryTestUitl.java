package io.sugo.utils.query;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class QueryTestUitl {
	static Logger logger = Logger.getLogger(QueryTestUitl.class);

	public static void main(String[] args){
		File file = new File("resultFiles/TimeBoundQueryResultFiles/timeBoundQueryResult.json");
		println(file.exists());

	}

	public static void writeResultToFile(String fileName,String result){
		File file = new File(fileName);
		FileWriter fw = null;
		try {
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if(!file.exists()){
				file.createNewFile();
				println(file.getAbsolutePath());
			}

			fw = new FileWriter(file);
			//println(JsonFormater.format(result));
			fw.write(result);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			logger.error("",e);
			e.printStackTrace();
		}
	}

	public static String getResultFromFile (String fileName) {
		BufferedReader reader;
		StringBuilder sb = null;
		String tmp;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			sb = new StringBuilder();
			while ((tmp=reader.readLine())!=null){
				sb.append(tmp);
			}
			reader.close();
		} catch (FileNotFoundException e){
			logger.error(fileName + "文件不存在",e);
			return null;
		} catch (IOException e) {
			logger.error("",e);
		}

		if(null == sb){
			RuntimeException nullPointException = new NullPointerException();
			logger.error("文件内容为空",nullPointException);
			return null;
		}
		return  sb.toString();
	}

	public static boolean compare(int fromFileResult, int fromQueryResult){
		if(fromFileResult == fromQueryResult) return true;
		return false;
	}

	public static boolean compare(Object fromFileResult, Object fromQueryResult){
		if(fromFileResult.equals(fromQueryResult)) return true;
		return false;
	}

	public static boolean compareDouble(Map fileMap, Map queryMap, String key, int precision){
		String fileResult = String.format("%."+precision+"f",fileMap.get(key));
		String queryResult = String.format("%."+precision+"f",queryMap.get(key));
		//println(key + ":" +fileResult + " " + queryResult);
		if(!fileResult.equals(queryResult)) return false;
		return true;
	}
	public static void println(Object str){
		System.out.println(str);
	}
}
