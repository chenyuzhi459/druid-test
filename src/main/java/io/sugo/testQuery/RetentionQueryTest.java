package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.RetentionQuery;
import io.sugo.query.member.step.CommonStep;
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
 * Created by chenyuzhi on 17-8-18.
 */
public class RetentionQueryTest {
	static RetentionQuery query;
	static Logger logger = Logger.getLogger(RetentionQueryTest.class);
	static String baseDir = "resultFiles/retentionQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}


	public static void before(){
		query = new RetentionQuery(ComponentUtil.dataSource,
								   null,
								   "day",
								   ComponentUtil.context);
		query.setIntervals("2017-05-15T00:00:00.000Z/2017-05-17T23:59:59.999Z");
		query.setField("id");
		query.setStartStep(new CommonStep("total","(province:广东省) OR (province: *自治区)"));
		query.setReturnStep(new CommonStep("total_return","province:广东省"));

	}

	public static boolean checkDetails(String fileName, String queryResult)  {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		return compareData(fileResultList,queryResultList);
	}




	public static boolean compareData(List<Map> fileResultList,List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) return false;
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		Iterator<Map> fileResultIterator = fileResultList.iterator();
		Iterator<Map> queryResultIterator = queryResultList.iterator();

		while (fileResultIterator.hasNext() && queryResultIterator.hasNext()){
			Map fileMap = fileResultIterator.next();
			Map queryMap = queryResultIterator.next();

			if(!fileMap.get("timestamp").equals(queryMap.get("timestamp"))){
				logger.error("RetentionQuery: " + "the query result timestamp not equal to the original file result timestamp");
				return false;
			}
			Map fileResultMap = (Map)fileMap.get("result");
			Map queryResultMap = (Map)queryMap.get("result");
			Set<String> fileResultKeySet = fileResultMap.keySet();
			for(Iterator<String> it=fileResultKeySet.iterator();it.hasNext();){
				String key = it.next();
				if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6)){
					logger.error("RetentionQuery: " + "the query result content not equal to the original file result content");
					return false;
				}
			}
		}

		return true;
	}


	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("RetentionQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("RetentionQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("RetentionQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}


	public static void main(String[] args){
		test();
	}

	public static void test(){
		before();
		logger.info("start test RetentionQuery...");

		testSelectorFilter();
		testAndFilter();
		testOrFilter();
		testNotFilter();
		testBoundFilter();
		testInFilter();
		testJavScriptFilter();
		testRegexFilter();
		testSearchFilter();
		testLuceneFilter();
		testAllFilter();
	}



	public static void testSelectorFilter()  {
		String filterName = "selectorFilter";
		String result;
		setSelectorFilter();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testAndFilter() {
		String filterName = "andFilter";
		String result;
		setAndFilter();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);

	}

	public static void testOrFilter() {
		String filterName = "orFilter";
		String result;
		setOrFilter();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testNotFilter() {
		String filterName = "notFilter";
		setNotFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testBoundFilter() {
		String filterName = "boundFilter";
		setBoundFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testInFilter(){
		String filterName = "inFilter";
		setInFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testJavScriptFilter() {
		String filterName = "javaScriptFilter";
		setJavaScriptFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testRegexFilter() {
		String filterName = "regexFilter";
		setRegexFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testSearchFilter() {
		String filterName = "searchFilter";
		setSearchFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testLuceneFilter() {
		String filterName = "luceneFilter";
		setLuceneFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testAllFilter(){
		String filterName = "allFilter";
		setAllFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static String query(){
		/*try {
			println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		return result;
	}

	public static void setSelectorFilter(){
		query.setFilter(ComponentUtil.seletorFilter);
	}

	public static void setRegexFilter(){
		query.setFilter(ComponentUtil.regexFilter);
	}

	public static void setAndFilter(){
		query.setFilter(ComponentUtil.andFilter);
	}

	public static void setOrFilter(){
		query.setFilter(ComponentUtil.orFilter);
	}

	public static void setNotFilter(){
		query.setFilter(ComponentUtil.notFilter);
	}

	public static void setSearchFilter(){
		query.setFilter(ComponentUtil.searchFilter);
	}

	public static void setInFilter(){
		query.setFilter(ComponentUtil.inFilter);
	}

	public static void setBoundFilter(){
		query.setFilter(ComponentUtil.boundFilter);
	}

	public static void setJavaScriptFilter(){
		query.setFilter(ComponentUtil.javaScriptFilter);
	}

	public static void setAllFilter(){
		query.setFilter(ComponentUtil.allFilter);
	}

	public static void setLuceneFilter(){
		query.setFilter(ComponentUtil.luceneFilter);
	}

	public static void println(Object o){
		System.out.println(o);
	}
}
