package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.TimeBoundaryQuery;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.*;

/** 
* TimeBoundaryQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/12/2017</pre> 
* @version 1.0 
*/ 
public class TimeBoundaryQueryTest {
	static TimeBoundaryQuery query;
	static Logger logger = Logger.getLogger(TimeBoundaryQueryTest.class);
	static String baseDir = "resultFiles/timeBoundQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before()  {
		query = new TimeBoundaryQuery(ComponentUtil.dataSource,ComponentUtil.intervals,null);
	}



	public static boolean checkDetails(String fileName, String queryResult){
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		if(fileResultList.size()!=queryResultList.size()) return false;
		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(List<Map> fromFileResult, List<Map> fromQueryResult){
		if(fromFileResult.size()==0 && fromFileResult.size()==0) return true;
		return fromFileResult.get(0).get("result").equals(fromQueryResult.get(0).get("result"));
	}


	public static void checkResult(String queryResult) {
		boolean passThrough = checkDetails(baseDir  + "result.json",queryResult);
		if(passThrough){
			logger.info("TimeBoundaryQuery: "  + "pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("TimeBoundaryQuery: " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("TimeBoundaryQuery: "  +  "fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String args[]){
		test();
	}

	public static void test(){
		before();

		logger.info("start test TimeBoundaryQuery...");
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir  + "result.json",result);
		checkResult(result);
	}

	public static String query() {

			//println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		return result;

	}

	public static void println(Object str){
		System.out.println(str);
	}
} 
