package io.sugo.testQuery;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sugo.query.FirstNQuery;
import io.sugo.query.member.dimension.DefaultDimension;
import io.sugo.testQuery.queryException.QueryResultMissMatchException;
import io.sugo.utils.query.ComponentUtil;
import io.sugo.utils.query.QueryTestUitl;
import io.sugo.utils.json.JsonParseUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class FirstNQueryTest {
	static FirstNQuery query;
	static ObjectMapper jsonMapper = new ObjectMapper();
	static String baseDir = "resultFiles/firstNQueryResultFiles/";
	static String requestUrl = ComponentUtil.requestUrl;
	static{
		jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	static Logger logger = Logger.getLogger(FirstNQueryTest.class);

	public static void before() {
		query = new FirstNQuery(ComponentUtil.dataSource,
								ComponentUtil.intervals,
								ComponentUtil.granularity,
								3,
								false,
								ComponentUtil.context);
		query.setDimension(new DefaultDimension("province"));

	}

	public static boolean checkDetails(String fileName, String queryResult) {
		String fileResult = QueryTestUitl.getResultFromFile(fileName);
		List fileResultList = specificParseJson2List(fileResult);
		List queryResultList = specificParseJson2List(queryResult);

		if(!QueryTestUitl.compare(fileResultList.size(),queryResultList.size())){
			logger.error("FirstNQuery: " + "the query result number not equal to the original file result number");
			return false;
		}
		return true;
	}

	public static void checkResult(String queryType,String queryResult){
		boolean passThrough = checkDetails(baseDir + queryType + "Result.json",queryResult);
		if(passThrough){
			logger.info("FirstNQuery: " + queryType + " pass");
		}else{
			RuntimeException exception = new QueryResultMissMatchException("FirstNQuery: <" + queryType + "> " +
																			QueryResultMissMatchException.MESSAGE);
			logger.error("FirstNQuery: " + queryType +  " fail to pass",exception);
			throw exception;
		}
	}

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		String result;
		before();

		logger.info("start test FirstNQuery...");
		setSelectorFilter();
		result = query();
		checkResult("selectorFilterQuery",result);

		setRegexFilter();
		result = query();
		checkResult("regexFilterQuery",result);

		setAndFilter();
		result = query();
		checkResult("andFilterQuery",result);

		setOrFilter();
		result = query();
		checkResult("orFilterQuery",result);

		setNotFilter();
		result = query();
		checkResult("notFilterQuery",result);

		setSearchFilter();
		result = query();
		checkResult("searchFilterQuery",result);

		setInFilter();
		result = query();
		checkResult("inFilterQuery",result);

		setBoundFilter();
		result = query();
		checkResult("boundFilterQuery",result);

		setJavaScriptFilter();
		result = query();
		checkResult("javaScriptFilterQuery",result);

		setLuceneFilter();
		result = query();
		checkResult("luceneFilterQuery",result);

		setAllFilter();
		result = query();
		checkResult("allFilterQuery",result);

	}

	public static List specificParseJson2List(String jsonStr)  {
		HashMap map = null;
		try {
			map = jsonMapper.readValue(JsonParseUtil.checkFormat(jsonStr),HashMap.class);
		} catch (IOException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		List list = (List)map.get("result");
		return list;
	}


	public static String query() {
		String result = query.query(requestUrl);
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
