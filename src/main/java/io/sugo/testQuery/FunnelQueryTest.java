package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.FunnelQuery;
import io.sugo.query.member.periodGranularity.PeriodGranularity;
import io.sugo.query.member.step.CommonStep;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.json.JsonFormater;
import io.sugo.utils.json.JsonParseUtil;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenyuzhi on 17-8-19.
 */
public class FunnelQueryTest {
	static FunnelQuery query;
	static Logger logger = Logger.getLogger(FunnelQueryTest.class);
	static String baseDir = "resultFiles/funnelQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before(){
		query = new FunnelQuery(ComponentUtil.dataSource,null,ComponentUtil.context);
		query.setIntervals("2017-05-20T00:00:00.000Z/2017-05-25T23:59:59.999Z");
		query.setField("id");
		query.setGranularity(new PeriodGranularity("P4D",null,null));
		query.setSlidingWindow(new PeriodGranularity("P2D",null,null));
		query.addStep(new CommonStep("第 1 步","(province:广东省) OR (province:*自治区)"));
		query.addStep(new CommonStep("第 2 步","age:{30 TO 40}"));

	}

	public static boolean checkDetails(String fileName, String queryResult)  {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		return compareData(fileResultList,queryResultList);
	}

	public static boolean compareData(List<Map> fileResultList, List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) return false;
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		Iterator<Map> fileResultIterator = fileResultList.iterator();
		Iterator<Map> queryResultIterator = queryResultList.iterator();

		while (fileResultIterator.hasNext() && queryResultIterator.hasNext()){
			Map fileMap = fileResultIterator.next();
			Map queryMap = queryResultIterator.next();

			if(!fileMap.get("timestamp").equals(queryMap.get("timestamp"))){
				logger.error("FunnelQuery: " + "the query result timestamp not equal to the original file result timestamp");
				return false;
			}
			Map fileResultMap = (Map)fileMap.get("event");
			Map queryResultMap = (Map)queryMap.get("event");
			Set<String> fileResultKeySet = fileResultMap.keySet();
			for(Iterator<String> it=fileResultKeySet.iterator();it.hasNext();){
				String key = it.next();
				if(!QueryTestUitl.compareDouble(fileResultMap,queryResultMap,key,6)){
					logger.error("FunnelQuery: " + "the query result content not equal to the original file result content");
					return false;
				}

			}
		}

		return true;
	}


	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("FunnelQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("FunnelQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("FunnelQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args){
		test();
	}

	public static void test(){
		before();

		logger.info("start test FunnelQuery...");
		/*testWriteSelectorFilter();
		testWriteAndFilter();
		testWriteOrFilter();
		testWriteNotFilter();
		testWriteBoundFilter();
		testWriteInFilter();
		testWriteJavScriptFilter();
		testWriteRegexFilter();
		testWriteSearchFilter();
		testWriteLuceneFilter();
		testWriteAllFilter();*/

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

	public static void testWriteSelectorFilter()  {
		String filterName = "selectorFilter";
		String result;
		setSelectorFilter();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteAndFilter() {
		String filterName = "andFilter";
		String result;
		setAndFilter();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);

	}

	public static void testWriteOrFilter() {
		String filterName = "orFilter";
		String result;
		setOrFilter();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteNotFilter() {
		String filterName = "notFilter";
		setNotFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteBoundFilter() {
		String filterName = "boundFilter";
		setBoundFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteInFilter(){
		String filterName = "inFilter";
		setInFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteJavScriptFilter() {
		String filterName = "javaScriptFilter";
		setJavaScriptFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteRegexFilter() {
		String filterName = "regexFilter";
		setRegexFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteSearchFilter() {
		String filterName = "searchFilter";
		setSearchFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteLuceneFilter() {
		String filterName = "luceneFilter";
		setLuceneFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static void testWriteAllFilter(){
		String filterName = "allFilter";
		setAllFilter();
		String result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}

	public static String query(){
		/*try {
			println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		return  result;
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
