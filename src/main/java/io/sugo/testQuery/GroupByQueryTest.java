package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.components.having.*;
import io.sugo.query.GroupByQuery;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.query.member.dimension.DefaultDimension;
import io.sugo.query.member.dimension.base.BaseDimension;
import io.sugo.query.member.limitSpec.DefaultLimitSpec;
import io.sugo.query.member.limitSpec.orderByColumnSpec.OrderByColumnSpec;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.util.*;


/** 
* GroupByQuery Tester. 
* 
* @author <Authors name> 
* @since <pre>08/08/2017</pre> 
* @version 1.0 
*/ 
public class GroupByQueryTest {
	static GroupByQuery query = new GroupByQuery();
	static String baseDir = "resultFiles/groupByQueryResultFiles/";
	static String requestUrl = ComponentUtil.requestUrl;
	static ObjectMapper jsonMapper = new ObjectMapper();
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	static Logger logger = Logger.getLogger(GroupByQueryTest.class);

	public static void before() {
		query = new GroupByQuery(ComponentUtil.dataSource,
								 ComponentUtil.intervals,
								 ComponentUtil.granularity,
								 ComponentUtil.context);

		List<BaseDimension> dimensions = new ArrayList<>();
		dimensions.add(new DefaultDimension("province"));
		query.setDimensions(dimensions);


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
		query.addAggregation(ComponentUtil.thetaSketchAggregation);
		query.addAggregation(ComponentUtil.javaScriptAggregation);

		//添加PostAggregation配置
		query.addPostAggregation(ComponentUtil.fieldAccessPostAggregation);
		query.addPostAggregation(ComponentUtil.arithmeticPostAggregation);
		query.addPostAggregation(ComponentUtil.javaScriptPostAggregation);
		query.addPostAggregation(ComponentUtil.hyperUniqueCardinalityPostAggregation);


		DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(3);
		defaultLimitSpec.addOrderByColumnSpec(new OrderByColumnSpec("LONG_SUM_AGE",
				null,
				OrderByColumnSpec.DIRECTION_ASCENDING));
		query.setLimitSpec(defaultLimitSpec);

	}




	public static boolean checkDetails(String fileName, String queryResult)  {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = JsonParseUtil.parseJsonToList(fileResult);
		List queryResultList = JsonParseUtil.parseJsonToList(queryResult);
		return compare(fileResultList,queryResultList);
	}

	public static boolean compare(Object fromFileResult, Object fromQueryResult){
		List<Map> fileResultList = (List<Map>)fromFileResult;
		List<Map> queryResultList = (List<Map>)fromQueryResult;
		if(fileResultList.size()==0 && queryResultList.size()==0) return true;
		return compareData(fileResultList,queryResultList);
	}


	public static boolean compareData(List<Map> fileResultList,List<Map> queryResultList){
		if(fileResultList.size() != queryResultList.size()) return false;
		Iterator<Map> fileResultIterator = fileResultList.iterator();
		Iterator<Map> queryResultIterator = queryResultList.iterator();

		while (fileResultIterator.hasNext() && queryResultIterator.hasNext()){
			Map fileResultMap = (Map)fileResultIterator.next().get("event");
			Map queryResultMap = (Map)queryResultIterator.next().get("event");
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
				} else {
					if(!fileResultMap.get(key).equals(queryResultMap.get(key))) return logDefaultErr();
				}
			}
		}

		return true;
	}

	public static boolean logDefaultErr(){
		logger.error("GroupByQuery: " + "the query result content not equal to the original file result content");
		return false;
	}

	public static void checkResult(String queryType,String queryResult) {
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("GroupByQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("GroupByQuery: <" + queryType + "> " +
																			QueryResultMissMatchException.MESSAGE);
			logger.error("GroupByQuery: " + queryType +  " fail to pass",exception);
			throw  exception;
		}
	}

	public static void main(String[] args){
		test();
	}

	public static void test() {
		before();

		logger.info("start test GroupByQuery...");
		testSearchFilter();
		testSelectorFilter();
		testRegexFilter();
		testAndFilter();
		testOrFilter();
		testBoundFilter();
		testAllFilter();
		testLuceneFilter();
		testJavaScriptFilter();
		testInFilter();
		testNotFilter();

	}

	public static void testSelectorFilter() {

		String result;
		setSelectorFilter();

		query.setHaving(new GreaterThanHavingSpec("DOUBLE_SUM_DIMENSION1",3000));
		result = query();
		checkResult("selectorFilter/greaterThanHavingQuery",result);

		setLessThanHavingSpec();
		result = query();
		checkResult("selectorFilter/lessThanHavingQuery",result);

		//setAndHavingSpec();
		AndHavingSpec andHavingSpec = new AndHavingSpec();
		GreaterThanHavingSpec _greaterThanHavingSpec = new GreaterThanHavingSpec("FilteredCount(dim1 < 20)",3510);
		LessThanHavingSpec _lessThanHavingSpec = new LessThanHavingSpec("JavaScriptAggregation_Max(salary-average)",2918);

		andHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		andHavingSpec.addHavingSpec(_lessThanHavingSpec);
		query.setHaving(andHavingSpec);
		result = query();
		checkResult("selectorFilter/andHavingQuery",result);

		setOrHavingSpec();
		result = query();
		checkResult("selectorFilter/orHavingQuery",result);

		query.setHaving(new NotHavingSpec(_greaterThanHavingSpec));
		result = query();
		checkResult("selectorFilter/notHavingQuery",result);

		query.setHaving(new EqualToHavingSpec("JavaScriptPostAggregation(MAX_SALARY - MIN_SALARY)",19998));
		result = query();
		checkResult("selectorFilter/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		checkResult("selectorFilter/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		checkResult("selectorFilter/alwaysHavingQuery",result);

		String queryType = "selectorFilter/alwaysHavingQuery";
		//QueryTestUitl.writeResultToFile(baseDir + queryType + "Result.json",result);
	}

	public static void testRegexFilter(){
		String result;
		setRegexFilter();

		setGreaterThanHavingSpec();
		result = query();
		checkResult("regexFilter/greaterThanHavingQuery",result);

		setLessThanHavingSpec();
		result = query();
		checkResult("regexFilter/lessThanHavingQuery",result);

		setAndHavingSpec();
		result = query();
		checkResult("regexFilter/andHavingQuery",result);

		setOrHavingSpec();
		result = query();
		checkResult("regexFilter/orHavingQuery",result);


		setNotHavingSpec();
		result = query();
		checkResult("regexFilter/notHavingQuery",result);

		setEqualToHavingSpec();
		result = query();
		checkResult("regexFilter/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		checkResult("regexFilter/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		checkResult("regexFilter/alwaysHavingQuery",result);

		String queryType = "regexFilter/alwaysHavingQuery";
		//QueryTestUitl.writeResultToFile(baseDir + queryType + "Result.json",result);
	}

	public static void testAndFilter(){
		String filterName = "andFilter";
		String result;
		setAndFilter();

		setGreaterThanHavingSpec();
		result = query();
		checkResult(filterName + "/greaterThanHavingQuery",result);

		setLessThanHavingSpec();
		result = query();
		checkResult(filterName + "/lessThanHavingQuery",result);

		setAndHavingSpec();
		result = query();
		checkResult(filterName + "/andHavingQuery",result);

		setOrHavingSpec();
		result = query();
		checkResult(filterName+ "/orHavingQuery",result);


		setNotHavingSpec();
		result = query();
		checkResult(filterName + "/notHavingQuery",result);

		query.setHaving(new EqualToHavingSpec("Count(*)",882359));
		result = query();
		checkResult(filterName + "/equalToHavingQuery",result);

		query.setHaving(new DimensionSelectorHavingSpec("province","海南省"));
		result = query();
		checkResult(filterName + "/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		checkResult(filterName + "/alwaysHavingQuery",result);

	}

	public static void testOrFilter() {
		String filterName = "orFilter";
		setOrFilter();
		checkGeneralHavingSpecs(filterName);
	}

	public static void testNotFilter(){
		String filterName = "notFilter";

		setNotFilter();
		//writeFiles(filterName);
		checkGeneralHavingSpecs(filterName);

	}

	public static void testSearchFilter() {
		String filterName = "searchFilter";
		setSearchFilter();
		//writeFiles(filterName);
		checkGeneralHavingSpecs(filterName);
	}

	public static void testInFilter(){
		String filterName = "inFilter";
		setInFilter();
		//writeFiles(filterName);
		checkGeneralHavingSpecs(filterName);
	}

	public static void testBoundFilter() {
		String result;
		String filterName = "boundFilter";
		setBoundFilter();

		GreaterThanHavingSpec _greaterThanHavingSpec = new GreaterThanHavingSpec("FilteredCount(age < 45)",264680);
		query.setHaving(_greaterThanHavingSpec);
		result = query();
		checkResult(filterName + "/greaterThanHavingQuery",result);

		LessThanHavingSpec _lessThanHavingSpec = new LessThanHavingSpec("FilteredCount(age < 45)",264690);
		query.setHaving(_lessThanHavingSpec);
		result = query();
		checkResult(filterName + "/lessThanHavingQuery",result);

		AndHavingSpec andHavingSpec = new AndHavingSpec();
		andHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		andHavingSpec.addHavingSpec(_lessThanHavingSpec);
		query.setHaving(andHavingSpec);
		result = query();
		checkResult(filterName + "/andHavingQuery",result);

		OrHavingSpec orHavingSpec = new OrHavingSpec();
		orHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		orHavingSpec.addHavingSpec(_lessThanHavingSpec);
		query.setHaving(orHavingSpec);
		result = query();
		checkResult(filterName+ "/orHavingQuery",result);

		NotHavingSpec notHavingSpec = new NotHavingSpec(_lessThanHavingSpec);
		query.setHaving(notHavingSpec);
		result = query();
		checkResult(filterName + "/notHavingQuery",result);

		setEqualToHavingSpec();
		result = query();
		checkResult(filterName + "/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		checkResult(filterName + "/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		checkResult(filterName + "/alwaysHavingQuery",result);
	}

	public static void testAllFilter(){
		String filterName = "allFilter";
		setAllFilter();
		//writeFiles(filterName);
		checkGeneralHavingSpecs(filterName);
	}

	public static void testLuceneFilter() {
		String result;
		String filterName = "luceneFilter";
		setLuceneFilter();

		GreaterThanHavingSpec _greaterThanHavingSpec = new GreaterThanHavingSpec("Count(*)",511766);
		query.setHaving(_greaterThanHavingSpec);
		result = query();
		checkResult(filterName + "/greaterThanHavingQuery",result);

		LessThanHavingSpec _lessThanHavingSpec = new LessThanHavingSpec("JavaScriptAggregation_Max(salary-average)",2903);
		query.setHaving(_lessThanHavingSpec);
		result = query();
		checkResult(filterName + "/lessThanHavingQuery",result);

		AndHavingSpec andHavingSpec = new AndHavingSpec();
		andHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		andHavingSpec.addHavingSpec(_lessThanHavingSpec);
		query.setHaving(andHavingSpec);
		result = query();
		checkResult(filterName + "/andHavingQuery",result);

		OrHavingSpec orHavingSpec = new OrHavingSpec();
		orHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		orHavingSpec.addHavingSpec(_lessThanHavingSpec);
		query.setHaving(orHavingSpec);
		result = query();
		checkResult(filterName+ "/orHavingQuery",result);

		NotHavingSpec notHavingSpec = new NotHavingSpec(_lessThanHavingSpec);
		query.setHaving(notHavingSpec);
		result = query();
		checkResult(filterName + "/notHavingQuery",result);

		setEqualToHavingSpec();
		result = query();
		checkResult(filterName + "/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		checkResult(filterName + "/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		checkResult(filterName + "/alwaysHavingQuery",result);

	}

	public static void testJavaScriptFilter() {
		String filterName = "javaScriptFilter";
		setJavaScriptFilter();
		//writeFiles(filterName);
		checkGeneralHavingSpecs(filterName);
	}

	public static void writeFiles(String filterName) throws Exception{
		String result;

		setGreaterThanHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/greaterThanHavingQueryResult.json",result);
		//checkResult(filterName + "/greaterThanHavingQuery",result);

		setLessThanHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/lessThanHavingQueryResult.json",result);
		//checkResult(filterName + "/lessThanHavingQuery",result);

		setAndHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/andHavingQueryResult.json",result);
		//checkResult(filterName + "/andHavingQuery",result);

		setOrHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/orHavingQueryResult.json",result);
		//checkResult(filterName+ "/orHavingQuery",result);


		setNotHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/notHavingQueryResult.json",result);
		//checkResult(filterName + "/notHavingQuery",result);

		setEqualToHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/equalToHavingQueryResult.json",result);
		//checkResult(filterName + "/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/dimensionSelectorHavingQueryResult.json",result);
		//checkResult(filterName + "/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		QueryTestUitl.writeResultToFile(baseDir + filterName + "/alwaysHavingQueryResult.json",result);
		//checkResult(filterName + "/alwaysHavingQuery",result);
	}

	public static void checkGeneralHavingSpecs(String filterName){
		String result;

		setGreaterThanHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/greaterThanHavingQueryResult.json",result);
		checkResult(filterName + "/greaterThanHavingQuery",result);

		setLessThanHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/lessThanHavingQueryResult.json",result);
		checkResult(filterName + "/lessThanHavingQuery",result);

		setAndHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/andHavingQueryResult.json",result);
		checkResult(filterName + "/andHavingQuery",result);

		setOrHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/orHavingQueryResult.json",result);
		checkResult(filterName+ "/orHavingQuery",result);


		setNotHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/notHavingQueryResult.json",result);
		checkResult(filterName + "/notHavingQuery",result);

		setEqualToHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/equalToHavingQueryResult.json",result);
		checkResult(filterName + "/equalToHavingQuery",result);

		setDimensionSelectorHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/dimensionSelectorHavingQueryResult.json",result);
		checkResult(filterName + "/dimensionSelectorHavingQuery",result);

		setAlwaysHavingSpec();
		result = query();
		//QueryTestUitl.writeResultToFile(baseDir + filterName + "/alwaysHavingQueryResult.json",result);
		checkResult(filterName + "/alwaysHavingQuery",result);
	}

	public static String query()  {

		String reslut = query.query(requestUrl);
		//println(JsonFormater.format(reslut));
		return reslut;
	}

	public static void setGreaterThanHavingSpec(){
		query.setHaving(ComponentUtil.greaterThanHavingSpec);
	}

	public static void setLessThanHavingSpec(){
		query.setHaving(ComponentUtil.lessThanHavingSpec);
	}

	public static void setAndHavingSpec(){
		query.setHaving(ComponentUtil.andHavingSpec);
	}

	public static void setOrHavingSpec(){
		query.setHaving(ComponentUtil.orHavingSpec);
	}

	public static void setNotHavingSpec(){
		query.setHaving(ComponentUtil.notHavingSpec);
	}

	public static void setEqualToHavingSpec(){
		query.setHaving(ComponentUtil.equalToHavingSpec);
	}

	public static void setDimensionSelectorHavingSpec(){
		query.setHaving(ComponentUtil.dimensionSelectorHavingSpec);
	}

	public static void setAlwaysHavingSpec(){
		query.setHaving(ComponentUtil.alwaysHavingSpec);
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

	public static void setNotFilter(){

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

	public static void println(Object str){
		System.out.println(str);
	}
} 
