package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.sugo.query.TimeseriesQuery;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.json.JsonFormater;
import io.sugo.utils.query.ComponentUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.*;

/** 
* TimeseriesQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/07/2017</pre> 
* @version 1.0 
*/ 
public class TimeseriesQueryTest {

	private static TimeseriesQuery<BaseAggregation, BasePostAggregation> query;
	static Logger logger = Logger.getLogger(TimeseriesQueryTest.class);
	static String baseDir = "resultFiles/timeseriesQueryResultFiles/";
	static String requestUrl = ComponentUtil.requestUrl;
	static ObjectMapper jsonMapper = new ObjectMapper();
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before() {
		query = new TimeseriesQuery<>(ComponentUtil.dataSource,"day",ComponentUtil.intervals,ComponentUtil.context);

		//添加Aggregation配置
		query.addAggregation(ComponentUtil.countAggregation);
		query.addAggregation(ComponentUtil.dateMaxAggregation);
		query.addAggregation(ComponentUtil.dateMinAggregation);
		query.addAggregation(ComponentUtil.doubleMaxAggregation);
		query.addAggregation(ComponentUtil.doubleMinAggregation);
		query.addAggregation(ComponentUtil.doubleSumAggregation);
		query.addAggregation(ComponentUtil.hyperUniqueAggregation);
		query.addAggregation(ComponentUtil.longMaxAggregation);
		query.addAggregation(ComponentUtil.longMinAggregation);
		query.addAggregation(ComponentUtil.longSumAggregation);
		query.addAggregation(ComponentUtil.filteredAggregation);
		query.addAggregation(ComponentUtil.thetaSketchAggregation);
		query.addAggregation(ComponentUtil.javaScriptAggregation);

		//添加PostAggregation配置
		query.addPostAggregation(ComponentUtil.fieldAccessPostAggregation);
		query.addPostAggregation(ComponentUtil.arithmeticPostAggregation);
		query.addPostAggregation(ComponentUtil.javaScriptPostAggregation);
		query.addPostAggregation(ComponentUtil.hyperUniqueCardinalityPostAggregation);


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
			if(!fileMap.get("timestamp").equals(queryMap.get("timestamp"))) {
				logger.error("TimeseriesQuery: " + "the query result timestamp not equal to the original file result timestamp");
				return false;
			}

			Map fileResultMap = (Map)fileMap.get("result");
			Map queryResultMap = (Map)queryMap.get("result");
			Set<String> fileResultKeySet = fileResultMap.keySet();
			for(Iterator<String> it=fileResultKeySet.iterator();it.hasNext();){
				String key = it.next();
				if(key.equals("ThetaSketchAggregation_Name")){
					String fileResult = String.format("%.6f",((Map)fileResultMap.get(key)).get("estimate"));
					String queryResult = String.format("%.6f",((Map)queryResultMap.get(key)).get("estimate"));
					if(!fileResult.equals(queryResult)) return logDefaultErr();
				}else if(key.equals("JavaScriptAggregation_Max(salary-average)")){
					if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6))
						return logDefaultErr();
				}else if(key.equals("HyperUnique_age")){
					if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6))
						return logDefaultErr();
				}else if(key.equals("ArithmeticPostAggregation_Rate(age < 45)%")){
					if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6))
						return logDefaultErr();
				}else if(key.equals("HyperUniquePostAggregation_age")) {
					if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6))
						return logDefaultErr();
				}
				else {
					if(!fileResultMap.get(key).equals(queryResultMap.get(key))) return logDefaultErr();
				}
			}
		}

		return true;
	}

	public static boolean logDefaultErr(){
		logger.error("TimeseriesQuery: " + "the query result content not equal to the original file result content");
		return false;
	}

	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("TimeseriesQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("TimeseriesQuery的: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("TimeseriesQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args){
		test();
	}

	public static void test(){
		before();

		logger.info("start test TimeseriesQuery...");


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

		testLookupFilter();

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

	public static void testLookupFilter(){
		String filterName = "lookupFilter";
		setLookupFilter();
		println("groupId: " + ComponentUtil.lookupFilter.getLookup());
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
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

	public static void setLookupFilter(){
		query.setFilter(ComponentUtil.lookupFilter);
	}

	public static void println(Object str){
		System.out.println(str);
	}

} 
