package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.ScanQuery;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class ScanQueryTest  {

	static ScanQuery query;
	static Logger logger = Logger.getLogger(ScanQueryTest.class);
	static String baseDir = "resultFiles/scanQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before() {
		query = new ScanQuery(ComponentUtil.dataSource,
							  ComponentUtil.intervals,
							  "list",
							  2,
				              7,
							  ComponentUtil.context);

		query.addColumn("province");
		query.addColumn("id");
	}

	public static boolean checkDetails(String fileName, String queryResult) {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(Object fromFileResult, Object fromQueryResult){
		List<Map> fileResultList = (List<Map>)fromFileResult;
		List<Map> queryResultList = (List<Map>)fromQueryResult;
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		if(compareDetailSize(fileResultList,queryResultList)) return true;

		return false;
	}

	public static boolean compareDetailSize(List<Map> fileResultList,List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) return false;
		Iterator<Map> fileResultIterator = fileResultList.iterator();
		Iterator<Map> queryResultIterator = queryResultList.iterator();

		while (fileResultIterator.hasNext()&&queryResultIterator.hasNext()){
			List fileList = (List)fileResultIterator.next().get("events");
			List queryList = (List)queryResultIterator.next().get("events");
			if(fileList.size() != queryList.size()) {
				logger.error("ScanQuery: " + "the query result number not equal to the original file result number");
				return false;
			}
		}
		return true;
	}

	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("ScanQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("ScanQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("ScanQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args) throws Exception {
		test();
	}

	public static void test() {
		before();

		logger.info("start test ScanQuery...");
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
		QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		//checkResult(filterName,result);
	}



	public static String query(){

			//println(JsonFormater.format(jsonMapper.writeValueAsString(query)));

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
