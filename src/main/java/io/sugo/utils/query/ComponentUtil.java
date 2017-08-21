package io.sugo.utils.query;

import io.sugo.components.aggregation.*;
import io.sugo.components.filter.*;
import io.sugo.components.having.*;
import io.sugo.components.postAggregation.*;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.member.javaScriptConfig.JavaScriptConfig;
import io.sugo.query.member.searchQuerySpec.ContainsSearchQuerySpec;
import io.sugo.testQuery.UserGroupQueryTest;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chenyuzhi on 17-8-8.
 */
public class ComponentUtil {
	static Logger logger = Logger.getLogger(ComponentUtil.class);
	public static String requestUrl = ConfigUtil.getRequestUrl();
	public static String dataSource = ConfigUtil.getDataSource();

	public static String intervals = ConfigUtil.getIntervals();
	public static String granularity = ConfigUtil.getGranularity();
	public static String redisHostsAndPorts = ConfigUtil.getRedisHostsAndPorts();
	public static boolean redisClusterMode = ConfigUtil.isRedisClusterMode();
	public static String initLookupUrl = ConfigUtil.getInitLookupUrl();
	public static String createLookupUrl = ConfigUtil.getCreateLookupUrl();
	static {
		logger.info("datasource: " + dataSource);
		logger.info("queryUrl: " + requestUrl);
		logger.info("redisHostsAndPorts: " + redisHostsAndPorts);
		logger.info("redisClusterMode: " + redisClusterMode);
		logger.info("initLookupUrl: " + initLookupUrl);
		logger.info("createLookupUrl: " + createLookupUrl);
	}

	public static Map<String, Object> context = new HashMap<>();
	static {
		context.put("timeout", 180000);
		context.put("useOffheap", true);
		context.put("groupByStrategy", "v2");
	}


	//通用Aggregation配置
	public static CountAggregation countAggregation = new CountAggregation("Count(*)");
	public static DateMaxAggregation dateMaxAggregation = new DateMaxAggregation("MAX_BIRTHDAY","birthday");
	public static DateMinAggregation dateMinAggregation = new DateMinAggregation("MIN_BIRTHDAY","birthday");
	public static DoubleMaxAggregation doubleMaxAggregation = new DoubleMaxAggregation("DOUBLE_MAX_SALARY","salary");
	public static DoubleMinAggregation doubleMinAggregation = new DoubleMinAggregation("DOUBLE_MIN_SALARY","salary");
	public static DoubleSumAggregation doubleSumAggregation = new DoubleSumAggregation("DOUBLE_SUM_DIMENSION1","dimension1");
	public static HyperUniqueAggregation hyperUniqueAggregation = new HyperUniqueAggregation("HyperUnique_age","age");
	public static LongMaxAggregation longMaxAggregation = new LongMaxAggregation("LONG_MAX_AGE","age");
	public static LongMinAggregation longMinAggregation = new LongMinAggregation("LONG_MIN_AGE","age");
	public static LongSumAggregation longSumAggregation = new LongSumAggregation("LONG_SUM_AGE","age");
	public static FilteredAggregation filteredAggregation = new FilteredAggregation<>(
																	new CountAggregation("FilteredCount(age < 45)"),
																	new BoundFilter("age",null,"45",
																					false,true,true));

	public static FilteredAggregation dimensionFilteredAggregation1 = new FilteredAggregation<>(
			new CountAggregation("FilteredCount(dim1 < 20)"),
			new BoundFilter("dimension1",null,"20",
					false,true,true));

	/*public static FilteredAggregation dimensionFilteredAggregation2 = new FilteredAggregation<>(
			new CountAggregation("FilteredCount(dim2 < 126)"),
			new BoundFilter("dimension2",null,"126",
					false,true,true));*/

	public static ThetaSketchAggregation thetaSketchAggregation = new ThetaSketchAggregation("ThetaSketchAggregation_Name","age",16,
																							true,true,1);
	public static JavaScriptAggregation javaScriptAggregation =  new JavaScriptAggregation();
	//配置JavaScriptAggregation
	static{
		javaScriptAggregation.setName("JavaScriptAggregation_Max(salary-average)");
		javaScriptAggregation.addFieldName("average");
		javaScriptAggregation.addFieldName("salary");
		javaScriptAggregation.setFnAggregate("function(current,_average,_salary) { return Math.min(current,(parseFloat(_salary)-parseFloat(_average))); }");
		javaScriptAggregation.setFnCombine("function(a,b) { return Math.min(a,b); }");
		javaScriptAggregation.setFnReset("function() { return Infinity; }");
	}


	//通用PostAggregation配置
	public static FieldAccessPostAggregation fieldAccessPostAggregation = new FieldAccessPostAggregation("FieldAcessPostAggregation_MaxSalary","DOUBLE_MAX_SALARY");
	public static HyperUniqueCardinalityPostAggregation
			hyperUniqueCardinalityPostAggregation = new HyperUniqueCardinalityPostAggregation("HyperUniquePostAggregation_age","HyperUnique_age");

	//利用ArithmeticPostAggregation和ConstantPostAggregation计算age<45的人数占总人数的百分比
	public static ArithmeticPostAggregation<BasePostAggregation> arithmeticPostAggregation =
			new ArithmeticPostAggregation<>("ArithmeticPostAggregation_Rate(age < 45)%", "*");
	static{
		ArithmeticPostAggregation<BasePostAggregation> arithmeticPostAggregation1 =
				new ArithmeticPostAggregation<>("ArithmeticPostAggregation_Rate(age < 45)", "/");
		FieldAccessPostAggregation postAggregation1 = new FieldAccessPostAggregation("FieldAcessPostAggregation_Count(age < 45)","FilteredCount(age < 45)");
		FieldAccessPostAggregation postAggregation2 = new FieldAccessPostAggregation("FieldAcessPostAggregation_Count(*)","Count(*)");
		arithmeticPostAggregation1.addPostAggregationFileds(postAggregation1);
		arithmeticPostAggregation1.addPostAggregationFileds(postAggregation2);
		ConstantPostAggregation constantPostAggregation =new ConstantPostAggregation("Constant_Value", 100);
		arithmeticPostAggregation.addPostAggregationFileds(arithmeticPostAggregation1);
		arithmeticPostAggregation.addPostAggregationFileds(constantPostAggregation);
	}
	//利用JavaScriptPostAggregation计算最大salary和最小salary的差
	public static JavaScriptPostAggregation javaScriptPostAggregation = new JavaScriptPostAggregation("JavaScriptPostAggregation(MAX_SALARY - MIN_SALARY)");
	static {
		String function = "function(DOUBLE_MAX_SALARY, DOUBLE_MIN_SALARY) { return Math.abs(DOUBLE_MIN_SALARY - DOUBLE_MAX_SALARY) ; }";
		List<String> jsPostAggregationFieldNames = new ArrayList<>();
		jsPostAggregationFieldNames.add("DOUBLE_MAX_SALARY");
		jsPostAggregationFieldNames.add("DOUBLE_MIN_SALARY");
		javaScriptPostAggregation.setFunction(function);
		javaScriptPostAggregation.setFieldNames(jsPostAggregationFieldNames);
	}

	//通用Having配置
	public static GreaterThanHavingSpec greaterThanHavingSpec = new GreaterThanHavingSpec("FilteredCount(age < 45)",529420);
	public static LessThanHavingSpec lessThanHavingSpec = new LessThanHavingSpec("FilteredCount(dim1 < 20)",176400);
	public static AndHavingSpec andHavingSpec = new AndHavingSpec();
	static{
		GreaterThanHavingSpec _greaterThanHavingSpec = new GreaterThanHavingSpec("FilteredCount(dim1 < 20)",176500);
		LessThanHavingSpec _lessThanHavingSpec = new LessThanHavingSpec("JavaScriptAggregation_Max(salary-average)",2901);

		andHavingSpec.addHavingSpec(_greaterThanHavingSpec);
		andHavingSpec.addHavingSpec(_lessThanHavingSpec);
	}

	public static OrHavingSpec orHavingSpec = new OrHavingSpec();
	static {
		orHavingSpec.addHavingSpec(greaterThanHavingSpec);
		orHavingSpec.addHavingSpec(lessThanHavingSpec);
	}

	public static NotHavingSpec notHavingSpec = new NotHavingSpec(greaterThanHavingSpec);
	public static EqualToHavingSpec equalToHavingSpec = new EqualToHavingSpec("FilteredCount(age < 45)",529420);
	public static DimensionSelectorHavingSpec dimensionSelectorHavingSpec =
											new DimensionSelectorHavingSpec("province","广东省");
	public static AlwaysHavingSpec alwaysHavingSpec = new AlwaysHavingSpec();

	//通用Filter配置
	public static SeletorFilter seletorFilter = new SeletorFilter("dimension2","125");
	public static RegexFilter regexFilter = new RegexFilter("province","省$");
	public static AndFilter andFilter = new AndFilter();
	static{
		andFilter.addFilter(new RegexFilter("province","南\\w?"));
		andFilter.addFilter(regexFilter);
	}
	public static OrFilter orFilter = new OrFilter();
	static{
		orFilter.addFilter(new RegexFilter("province","市$"));
		orFilter.addFilter(new RegexFilter("province","区$"));
	}
	public static NotFilter notFilter = new NotFilter(orFilter);

	public static SearchFilter searchFilter = new SearchFilter("province");
	static {
/*		FragmentSearchQuerySpec fragmentSearchQuerySpec = new FragmentSearchQuerySpec();
		fragmentSearchQuerySpec.addValue("省");
		fragmentSearchQuerySpec.addValue("西");*/

		//RegexSearchQuerySpec regexSearchQuerySpec = new RegexSearchQuerySpec("区$");
		ContainsSearchQuerySpec containsSearchQuerySpec = new ContainsSearchQuerySpec("西");
		searchFilter.setQuery(containsSearchQuerySpec);
	}
	public static  InFilter inFilter = new InFilter();
	static {
		inFilter.setDimension("province");
		inFilter.addValue("广东省");
		inFilter.addValue("上海市");
		inFilter.addValue("浙江省");
		inFilter.addValue("福建省");
		inFilter.addValue("河南省");
		inFilter.addValue("澳门特别行政区");
		inFilter.addValue("天津市");
	}
	public static  BoundFilter boundFilter = new BoundFilter("age","30",null,false,false,true);

	public static JavaScriptFilter javaScriptFilter = new JavaScriptFilter();
	static {
		javaScriptFilter.setDimension("province");
		javaScriptFilter.setFunction("function(x) { return x.length > 3 }");
		javaScriptFilter.setConfig(new JavaScriptConfig(false));
	}

	public static LookupFilter lookupFilter = new LookupFilter("province", UserGroupQueryTest.groupId);

	public static AllFilter allFilter = new AllFilter();
	public static LuceneFilter luceneFilter = new LuceneFilter("age:{20 TO 50}");


}
