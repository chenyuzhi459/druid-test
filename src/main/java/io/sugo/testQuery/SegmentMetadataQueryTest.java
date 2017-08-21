package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.SegmentMetadataQuery;
import io.sugo.query.member.columnIncluderator.NoneColumnIncluderator;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** 
* SegmentMetadataQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/14/2017</pre> 
* @version 1.0 
*/ 
public class SegmentMetadataQueryTest {
	static SegmentMetadataQuery query;
	static Logger logger = Logger.getLogger(SegmentMetadataQueryTest.class);
	static String baseDir = "resultFiles/segmentMetadataQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	public static void before(){

		query = new SegmentMetadataQuery(ComponentUtil.dataSource,
										ComponentUtil.intervals,
										 new NoneColumnIncluderator(),
										true,
										false,
										true,
										ComponentUtil.context);

	}

	public static boolean checkDetails(String fileName, String queryResult){
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(Object fromFileResult, Object fromQueryResult){
		List<Map> fileResultList = (List<Map>)fromFileResult;
		List<Map> queryResultList = (List<Map>)fromQueryResult;
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		if(compareData(fileResultList,queryResultList)) return true;

		return false;
	}

	public static boolean compareData(List<Map> fileResultList, List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) return false;
		Iterator<Map> fileResultIterator = fileResultList.iterator();
		Iterator<Map> queryResultIterator = queryResultList.iterator();

		while (fileResultIterator.hasNext()&&queryResultIterator.hasNext()){
			Map fileResultMap = fileResultIterator.next();
			Map queryResultMap = queryResultIterator.next();
			Set<String> fileResultKeySet = fileResultMap.keySet();
			for(Iterator<String> it=fileResultKeySet.iterator();it.hasNext();){
				String key = it.next();
				if(key.equals("id")){
					continue;
				}else if(key.equals("size")){
					continue;
				}else {
					if(null == fileResultMap.get(key)){
						if(null != queryResultMap.get(key)){
							logger.error("SegmentMetadataQuery: " + "the query result content not equal to the original file result content");
							return false;
						}
						continue;
					}
					if(!fileResultMap.get(key).equals(queryResultMap.get(key))) {
						logger.error("SegmentMetadataQuery: " + "the query result content not equal to the original file result content");
						return false;
					}
				}
			}

		}
		return true;
	}

	public static void checkResult(String queryResult) {
		boolean passThrough = checkDetails(baseDir  + "result.json",queryResult);
		if(passThrough){
			logger.info("SegmentMetadataQuery: "  + "pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("SegmentMetadataQuery: " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("SegmentMetadataQuery: "  +  "fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		before();

		logger.info("start test SegmentMetadataQuery...");
		String result = query();
		checkResult(result);
	}

	public static String query(){
		String result = query.query(requestUrl);
		return  result;
	}

	public static void println(Object str){
		System.out.println(str);
	}
} 
