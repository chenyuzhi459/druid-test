package io.sugo.query;

import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.having.base.BaseHavingSpec;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.limitSpec.base.BaseLimitSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class GroupByQuery extends CommonQuery {
	private static final String QUERY_TYPE = "lucene_groupBy";
	private List dimensions;
	private List<BaseAggregation> aggregations;
	private List<BasePostAggregation> postAggregations;
	private BaseFilter filter;
	private BaseHavingSpec having;
	private BaseLimitSpec limitSpec;

	public GroupByQuery() {
		super(QUERY_TYPE);
	}

	public GroupByQuery(List dimensions) {
		super(QUERY_TYPE);
		this.dimensions = dimensions;
	}

	public GroupByQuery(String dataSource, String intervals, String granularity, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
	}

	public List getDimensions() {
		return dimensions;
	}

	public void setDimensions(List dimensions) {
		this.dimensions = dimensions;
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

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public BaseLimitSpec getLimitSpec() {
		return limitSpec;
	}

	public void setLimitSpec(BaseLimitSpec limitSpec) {
		this.limitSpec = limitSpec;
	}

	public BaseHavingSpec getHaving() {
		return having;
	}

	public void setHaving(BaseHavingSpec having) {
		this.having = having;
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

	public void addDimension(Object dimension){
		if(null == this.dimensions){
			this.dimensions = new ArrayList();
		}
		this.dimensions.add(dimension);
	}
}
