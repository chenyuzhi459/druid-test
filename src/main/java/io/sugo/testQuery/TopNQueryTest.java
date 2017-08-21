package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.TopNQuery;
import io.sugo.query.member.dimension.DefaultDimension;
import io.sugo.query.member.topNMetricSpec.NumericTopNMetricSpec;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.json.JsonFormater;
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
public class TopNQueryTest {

	static TopNQuery query;
	static ObjectMapper jsonMapper = new ObjectMapper();
	static Logger logger = Logger.getLogger(TopNQueryTest.class);
	static String baseDir = "resultFiles/topNQueryResultFiles/";
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before() {
		query = new TopNQuery(ComponentUtil.dataSource,ComponentUtil.intervals,"day",ComponentUtil.context);
		query.setDimension(new DefaultDimension("province"));
		query.setMetric(new NumericTopNMetricSpec("LONG_SUM_AGE"));
		query.setThreshold(3);

		//添加Aggregation配置
		query.addAggregation(ComponentUtil.longSumAggregation);

	}

	public static boolean checkDetails(String fileName, String queryResult) {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);

		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(List<Map> fileResultList, List<Map> queryResultList){
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		if(fileResultList.size() != queryResultList.size()) return false;

		Iterator<Map> fileListIterator = fileResultList.iterator();
		Iterator<Map> queryListIterator = queryResultList.iterator();
		while(fileListIterator.hasNext() && queryListIterator.hasNext()){
			Map fileMap = fileListIterator.next();
			Map queryMap = queryListIterator.next();
			if(((List)fileMap.get("result")).size() != ((List)queryMap.get("result")).size()){
				logger.error("TopNQuery: " + "the query result number not equal to the original file result number");
				return false;
			}
		}

		return true;
	}


	public static void checkResult(String queryType,String queryResult){
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("TopNQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("FirstNQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("TopNQuery: " + queryType +  " fail to pass",exception);
			throw exception;
		}
	}

	public static void main(String[] args)  {
		test();
	}
	public static void test(){
		before();

		logger.info("start test TopNQuery...");

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


	public static String query()  {
		/*try {
			println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}*/
		String reslut = query.query(requestUrl);
		//println(JsonFormater.format(reslut));
		return reslut;
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
