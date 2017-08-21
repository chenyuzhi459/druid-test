package io.sugo.query;

import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.having.base.BaseHavingSpec;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.redisIOConfig.RedisIOConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class UserGroupQuery extends CommonQuery {
	private static final String QUERY_TYPE = "user_group";
	private RedisIOConfig dataConfig;
	private BaseFilter filter;
	private BaseHavingSpec having;
	private String dimension;
	private List<BaseAggregation> aggregations;
	private List<BasePostAggregation> postAggregations;

	public UserGroupQuery() {
		super(QUERY_TYPE);
	}

	public UserGroupQuery(String dataSource, String intervals, String granularity) {
		super(QUERY_TYPE, dataSource, intervals, granularity, null);
	}

	public UserGroupQuery(String dataSource, String intervals, String granularity,Map<String, Object> context ,String dimension) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.dimension = dimension;
	}

	public UserGroupQuery(String dataSource,
						  String intervals,
						  String granularity,
						  RedisIOConfig dataConfig,
						  BaseFilter filter,
						  BaseHavingSpec having,
						  String dimension,
						  List<BaseAggregation> aggregations,
						  List<BasePostAggregation> postAggregations,
						  Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.dataConfig = dataConfig;
		this.filter = filter;
		this.having = having;
		this.dimension = dimension;
		this.aggregations = aggregations;
		this.postAggregations = postAggregations;
	}

	public RedisIOConfig getDataConfig() {
		return dataConfig;
	}

	public void setDataConfig(RedisIOConfig dataConfig) {
		this.dataConfig = dataConfig;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public BaseHavingSpec getHaving() {
		return having;
	}

	public void setHaving(BaseHavingSpec having) {
		this.having = having;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<BaseAggregation> getAggregations() {
		return aggregations;
	}

	public void setAggregations(List<BaseAggregation> aggregations) {
		this.aggregations = aggregations;
	}

	public List<BasePostAggregation> getPostAggregations() {
		return postAggregations;
	}

	public void setPostAggregations(List<BasePostAggregation> postAggregations) {
		this.postAggregations = postAggregations;
	}

	public void addAggregation(BaseAggregation aggregation){
		if(null == this.aggregations){
			this.aggregations = new ArrayList<>();
		}
		this.aggregations.add(aggregation);
	}

	public void addPostAggregation(BasePostAggregation postAggregation){
		if(null == this.postAggregations){
			this.postAggregations = new ArrayList<>();
		}
		this.postAggregations.add(postAggregation);
	}

}
