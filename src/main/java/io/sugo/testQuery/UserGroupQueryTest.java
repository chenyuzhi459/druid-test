package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.UserGroupQuery;
import io.sugo.query.member.cachingLookup.CachingLookup;
import io.sugo.query.member.cachingLookup.RedisDataFetcher;
import io.sugo.query.member.redisIOConfig.RedisIOConfig;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.json.JsonFormater;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.http.MyHttpConnection;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/** 
* UserGroupQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/14/2017</pre> 
* @version 1.0 
*/ 
public class UserGroupQueryTest {
	public static String groupId = "17";

	static UserGroupQuery query;
	static Logger logger = Logger.getLogger(UserGroupQueryTest.class);
	static String baseDir = "resultFiles/userGroupQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String initLookupUrl = ComponentUtil.initLookupUrl;
	static String createLookupUrl = ComponentUtil.createLookupUrl;
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before() {
		query = new UserGroupQuery(ComponentUtil.dataSource,
								   ComponentUtil.intervals,
				                   ComponentUtil.granularity,
				                   ComponentUtil.context,
				                   "province");

		RedisIOConfig redisIOConfig = new RedisIOConfig(ComponentUtil.redisHostsAndPorts,
														ComponentUtil.redisClusterMode,
														groupId);
		query.setDataConfig(redisIOConfig);

	}

	public static boolean checkDetails(String fileName, String queryResult) {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);

		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(List<Map> fileResultList, List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) {
			logger.error("UserGroupQuery: " + "the query result number not equal to the original file result number");
			return false;
		}
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		 if(!((Map)fileResultList.get(0).get("event")).get("RowCount").equals(((Map)queryResultList.get(0).get("event")).get("RowCount"))){
			 logger.error("UserGroupQuery: " + "the \"RowCount\" is not same");
			 return false;
		 }
		return true;
	}

	public static void checkResult(String queryResult){
		boolean passThrough = checkDetails(baseDir +  "result.json",queryResult);
		if(passThrough){
			logger.info("UserGroupQuery: "  + "pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("UserGroupQuery: "  +
					QueryResultMissMatchException.MESSAGE);
			logger.error("UserGroupQuery: " +  "fail to pass",exception);
			throw exception;
		}
	}

	public static void main(String[] args){
		test();
	}

	public static void test(){
		before();
		logger.info("start test UserGroupQuery...");

		setSearchFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir  + "result.json",result);
		checkResult(result);

		initAndCreateLookup();
	}

	public static String query() {

		/*try {
			println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		return result;
	}

	public static void initAndCreateLookup(){
		String result = MyHttpConnection.postData(initLookupUrl,"{}");
		//println(result);
		createLookup();

	}

	public static void createLookup(){
		String url = createLookupUrl + "/" + groupId;

		RedisDataFetcher dataFetcher = new RedisDataFetcher(ComponentUtil.redisHostsAndPorts,
															ComponentUtil.redisClusterMode,
															groupId);
		CachingLookup cachingLookup = new CachingLookup("1",dataFetcher);
		try {
			String jsonData = jsonMapper.writeValueAsString(cachingLookup);
			//println(jsonData);
			String result = MyHttpConnection.postData(url,jsonData);
			//println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}


	public static void setSearchFilter(){
		query.setFilter(ComponentUtil.searchFilter);
	}

	public static void println(Object o){
		System.out.println(o);
	}


} 
