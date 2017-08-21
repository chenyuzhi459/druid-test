package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.components.aggregation.*;
import io.sugo.query.MultiHavingQuery;
import io.sugo.query.member.dimension.DefaultDimension;
import io.sugo.query.member.havingAggregator.HavingAggregator;
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
 * Created by chenyuzhi on 17-8-15.
 */
public class MultiHavingQueryTest {
	static MultiHavingQuery query;
	static Logger logger = Logger.getLogger(MultiHavingQueryTest.class);
	static String baseDir = "resultFiles/multiHavingQueryResultFiles/";
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static void before()  {
		query = new MultiHavingQuery(ComponentUtil.dataSource,ComponentUtil.intervals,ComponentUtil.granularity,ComponentUtil.context);
		query.addDimension(new DefaultDimension("province"));

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
		query.addAggregation(ComponentUtil.dimensionFilteredAggregation1);
		//query.addAggregation(ComponentUtil.dimensionFilteredAggregation2);
		query.addAggregation(ComponentUtil.thetaSketchAggregation);
		query.addAggregation(ComponentUtil.javaScriptAggregation);

		//添加PostAggregation配置
		query.addPostAggregation(ComponentUtil.fieldAccessPostAggregation);
		query.addPostAggregation(ComponentUtil.arithmeticPostAggregation);
		query.addPostAggregation(ComponentUtil.javaScriptPostAggregation);
		query.addPostAggregation(ComponentUtil.hyperUniqueCardinalityPostAggregation);

		addLessThanHaving_CountAggregator();
		addGreatThanHaving_DoubleMaxAggregator_LongMinAggregator();
		addAlwaysHaving_DoubleMinAggregaor_DoubleSumAggregator();
		addOrHaving_LongSumAggregator();

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

		if(compareData(fileResultList,queryResultList)) return true;
		return false;
	}

	public static boolean compareData(List<Map> fileResultList,List<Map> queryResultList){
		Map fileResultMap = (Map)((Map)fileResultList.get(0).get("event")).get("data");
		Map queryResultMap = (Map)((Map)queryResultList.get(0).get("event")).get("data");
		Set<String> fileResultKeySet = fileResultMap.keySet();
		for(Iterator<String> it=fileResultKeySet.iterator();it.hasNext();){
			String key = it.next();
			if(key.equals("AlwaysHaving_DoubleMinAggregator_DoubleSumAggregator")){
				//对double数据的比较进行精度控制
				if(!QueryTestUitl.compareDouble((Map)fileResultMap.get(key),(Map)queryResultMap.get(key),"DOUBLE_MIN(age<45)%",6)) {
					logger.error("MultiHavingQuery: " + "the query result content not equal to the original file result content");
					return false;
				}

				if(!QueryTestUitl.compareDouble((Map)fileResultMap.get(key),(Map)queryResultMap.get(key),"DOUBLE_SUM(age<45)%",6)) {
					logger.error("MultiHavingQuery: " + "the query result content not equal to the original file result content");
					return false;
				}
			}else {
				if(!fileResultMap.get(key).equals(queryResultMap.get(key))) {
					logger.error("MultiHavingQuery: " + "the query result content not equal to the original file result content");
					return false;
				}
			}
		}

		return true;
	}



	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("MultiHavingQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("MultiHavingQuery: <" + queryType + "> " +
					QueryResultMissMatchException.MESSAGE);
			logger.error("MultiHavingQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args)  {
		test();
	}

	public static void test() {
		before();

		logger.info("start test MultiHavingQuery...");
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

	public static void testSelectorFilter() {
		String filterName = "selectorFilter";
		String result;
		setSelectorFilter();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void testAndFilter(){
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

	public static void testNotFilter(){
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

	public static void testJavScriptFilter(){
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

	public static void testAllFilter() {
		String filterName = "allFilter";
		setAllFilter();
		String result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "Result.json",result);
		checkResult(filterName,result);
	}

	public static void writeFiles(String filterName) throws Exception{
		String result;

		//setGreaterThanHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/greaterThanHavingQueryResult.json",result);
		//checkResult(filterName + "/greaterThanHavingQuery",result);


	}

	public static String query() {
		//println(JsonFormater.format(jsonMapper.writeValueAsString(query)));
		String result = query.query(requestUrl);
		//println(JsonFormater.format(result));
		return result;
	}

	public static void addLessThanHaving_CountAggregator(){
		HavingAggregator havingAggregator = new HavingAggregator("Count_LessThanHaving");
		havingAggregator.setHavingSpec(ComponentUtil.lessThanHavingSpec);
		havingAggregator.addAggregator(ComponentUtil.countAggregation);
		query.addHavingAggregator(havingAggregator);
	}

	public static void addGreatThanHaving_DoubleMaxAggregator_LongMinAggregator(){
		HavingAggregator havingAggregator = new HavingAggregator("GreatThanHaving_DoubleMaxAggregator_LongMinAggregator");
		havingAggregator.setHavingSpec(ComponentUtil.greaterThanHavingSpec);
		havingAggregator.addAggregator(new DoubleMaxAggregation("DOUBLE_MAX(age<45)","FilteredCount(age < 45)"));
		havingAggregator.addAggregator(new LongMinAggregation("Min_Count(*)","Count(*)"));
		query.addHavingAggregator(havingAggregator);
	}


	public static void addAlwaysHaving_DoubleMinAggregaor_DoubleSumAggregator(){
		HavingAggregator havingAggregator = new HavingAggregator("AlwaysHaving_DoubleMinAggregator_DoubleSumAggregator");
		havingAggregator.setHavingSpec(ComponentUtil.alwaysHavingSpec);
		havingAggregator.addAggregator(new DoubleMinAggregation("DOUBLE_MIN(age<45)%","ArithmeticPostAggregation_Rate(age < 45)%"));
		havingAggregator.addAggregator(new DoubleSumAggregation("DOUBLE_SUM(age<45)%","ArithmeticPostAggregation_Rate(age < 45)%"));
		query.addHavingAggregator(havingAggregator);
	}

	public static void addOrHaving_LongSumAggregator(){
		HavingAggregator havingAggregator = new HavingAggregator("OrHaving_LongSumAggregator");
		havingAggregator.setHavingSpec(ComponentUtil.orHavingSpec);
		havingAggregator.addAggregator(new LongSumAggregation("LONG_SUM(min_age)","LONG_MIN_AGE"));
		havingAggregator.addAggregator(new LongSumAggregation("LONG_SUM(min_salary)","DOUBLE_MIN_SALARY"));
		havingAggregator.addAggregator(ComponentUtil.countAggregation);
		query.addHavingAggregator(havingAggregator);
	}

	public static void setSelectorFilter(){
		query.setFilter(ComponentUtil.seletorFilter);
	}

	public static void setRegexFilter(){
		query.setFilter(ComponentUtil.regexFilter);
	}

	public static void setAndFilter(){
		//此Filter可适用部分通用的having过滤
		query.setFilter(ComponentUtil.andFilter);
	}


	public static void setOrFilter(){
		//此Filter可适用部分通用的having过滤
		query.setFilter(ComponentUtil.orFilter);
	}

	public  static void setNotFilter(){

		//此Filter可适用部分通用的having过滤
		query.setFilter(ComponentUtil.notFilter);
	}

	public static void setSearchFilter(){
		//此Filter可适用部分通用的having过滤
		query.setFilter(ComponentUtil.searchFilter);
	}

	public static void setInFilter(){
		//此Filter可适用部分通用的having过滤
		query.setFilter(ComponentUtil.inFilter);
	}

	public static void setBoundFilter(){
		query.setFilter(ComponentUtil.boundFilter);
	}
	public static void setJavaScriptFilter(){
		query.setFilter(ComponentUtil.javaScriptFilter);
	}

	public static void setAllFilter(){
		//此Filter可适用全部通用的having过滤
		query.setFilter(ComponentUtil.allFilter);
	}

	public static void setLuceneFilter(){
		query.setFilter(ComponentUtil.luceneFilter);
	}

	public static void println(Object o){
		System.out.println(o);
	}
}
