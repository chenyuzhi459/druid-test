package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.SelectQuery;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.json.JsonParseUtil;
import io.sugo.query.member.pagingSpec.PagingSpec;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/** 
* SelectQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/11/2017</pre> 
* @version 1.0 
*/ 
public class SelectQueryTest {
	static SelectQuery query;
	static Logger logger = Logger.getLogger(SelectQueryTest.class);
	static String baseDir = "resultFiles/selectQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static int threshlod = 2;
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before()  {
		query = new SelectQuery(ComponentUtil.dataSource,ComponentUtil.intervals,ComponentUtil.granularity,ComponentUtil.context);


		query.addDimension("province");
		query.addDimension("age");
		query.addDimension("dimension2");
		query.setDescending(false);
		query.setPagingSpec(new PagingSpec(threshlod));

	}

	public static boolean checkDetails(String fileName, String queryResult) {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = specificParseJson2List(fileResult);
		List queryResultList = specificParseJson2List(queryResult);
		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(List<List> fromFileResult, List<List> fromQueryResult){
		if(fromFileResult.size() != fromQueryResult.size()) return false;
		if(compareData(fromFileResult,fromQueryResult)) return true;
		return false;
	}


	public static boolean compareData(List<List> fromFileResult,List<List> fromQueryResult){
		if(fromFileResult.size() != fromQueryResult.size()) return false;
		Iterator<List> fileListIterator = fromFileResult.iterator();
		Iterator<List> queryListIterator = fromQueryResult.iterator();

		while (fileListIterator.hasNext()&& queryListIterator.hasNext()){
			Map fileMap = (Map)fileListIterator.next().get(0);
			Map queryMap = (Map)queryListIterator.next().get(0);
			if(!fileMap.get("timestamp").equals(queryMap.get("timestamp"))) {
				logger.error("SelectQuery: " + "the query result timestamp not equal to the original file result timestamp");
				return false;
			}

			List<Map> fileList = (List)(((Map)fileMap.get("result")).get("events"));
			List<Map> queryList = (List)(((Map)queryMap.get("result")).get("events"));
			if(fileList.size() != queryList.size()){
				logger.error("SelectQuery: " + "the query result number not equal to the original file result number");
				return false;
			}

			Iterator<Map> fileIterator = fileList.iterator();
			Iterator<Map> queryIterator = queryList.iterator();
			while (fileIterator.hasNext()&&queryIterator.hasNext()){
				Map fileResultMap = fileIterator.next();
				Map queryResultMap = queryIterator.next();
				Set<String> keySet = fileResultMap.keySet();
				for (Iterator<String> it=keySet.iterator();it.hasNext();){
					String key = it.next();
					if(key.equals("segmentId")){
						continue;
					}else{
						if(!fileResultMap.get(key).equals(queryResultMap.get(key))) {
							logger.error("SelectQuery: " + "the query result content not equal to the original file result content");
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public static void checkResult(String queryType,String queryResult){
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("SelectQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("SelectQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("SelectQuery: " + queryType +  " fail to pass",exception);
			throw exception;
		}
	}

	public static List specificParseJson2List(String jsonStr)  {
		List<List> list = null;
		try {
			list = jsonMapper.readValue(jsonStr,List.class);
		} catch (IOException e) {
			logger.error("json转换出错",e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args){
		test();
	}

	public static void test(){
		before();

		logger.info("start test SelectQuery...");

		/*testWriteSelectorFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteAndFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteOrFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteNotFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteBoundFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteInFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteJavScriptFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteRegexFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteSearchFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteLuceneFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testWriteAllFilter();*/



		testSelectorFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testAndFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testOrFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testNotFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testBoundFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testInFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testJavScriptFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testRegexFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testSearchFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
		testLuceneFilter();
		query.setPagingSpec(new PagingSpec(threshlod));
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

	public static String query() {

			//println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		String firstResult = query.query(requestUrl);
		//println(JsonFormater.format(firstResult));
		String result = "[" + firstResult + multiQuery(firstResult,2) + "]";
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


	public static String multiQuery(String lastResult, int queryNumber)  {
		if(queryNumber<=0){
			return "";
		}
		Map lastResultMap = new HashMap();
		JsonParseUtil.parseJSON2Map(lastResultMap,JsonParseUtil.checkFormat(lastResult),null);
		Map pagingIdentifierMap = getPagingIdentifiers(lastResultMap);
		query.setPagingSpec(new PagingSpec(threshlod,false,pagingIdentifierMap));
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		--queryNumber;
		return "," + result  + multiQuery(result,queryNumber);
	}


	public static Map<String, Integer> getPagingIdentifiers(Map jsonMap){
		Map pagingIdentifierMap;
		Set<Entry> entrys = (Set<Entry>) jsonMap.entrySet();
		for(Iterator<Entry> it = entrys.iterator(); it.hasNext();){
			Entry entry = it.next();
			String key = entry.getKey().toString();
			if(key.contains("pagingIdentifiers")){
				int index = key.indexOf("pagingIdentifiers");
				//println(key.substring(index + 18) + ":" + entry.getValue());
				pagingIdentifierMap = new HashMap();
				//Set pagingentrys = pagingIdentifierMap.entrySet();
				pagingIdentifierMap.put(key.substring(index + 18),entry.getValue());
				return pagingIdentifierMap;
			}
		}
		return null;
	}

	public static void println(Object str){
		System.out.println(str);
	}

}