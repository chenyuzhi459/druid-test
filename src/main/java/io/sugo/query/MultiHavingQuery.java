package io.sugo.query;

import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.dimension.base.BaseDimension;
import io.sugo.query.member.havingAggregator.HavingAggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class MultiHavingQuery extends CommonQuery {
	private static final String QUERY_TYPE = "multi_having";
	private BaseFilter filter;
	private List<BaseDimension> dimensions;
	private List<BaseAggregation> aggregations;
	private List<BasePostAggregation> postAggregations;
	private List<HavingAggregator> havingAggregators;

	public MultiHavingQuery() {
		super(QUERY_TYPE);
	}

	public MultiHavingQuery(String dataSource, String intervals, String granularity, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
	}

	public MultiHavingQuery(String dataSource,
							String intervals,
							String granularity,
							BaseFilter filter,
							List<BaseDimension> dimensions,
							List<BaseAggregation> aggregations,
							List<BasePostAggregation> postAggregations,
							List<HavingAggregator> havingAggregators,
							Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.filter = filter;
		this.dimensions = dimensions;
		this.aggregations = aggregations;
		this.postAggregations = postAggregations;
		this.havingAggregators = havingAggregators;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public List<BaseDimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<BaseDimension> dimensions) {
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

	public List<HavingAggregator> getHavingAggregators() {
		return havingAggregators;
	}

	public void setHavingAggregators(List<HavingAggregator> havingAggregators) {
		this.havingAggregators = havingAggregators;
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

	public void addHavingAggregator(HavingAggregator havingAggregator){
		if(null == this.havingAggregators){
			this.havingAggregators = new ArrayList<>();
		}
		this.havingAggregators.add(havingAggregator);
	}

	public void addDimension(BaseDimension dimension){
		if(null == this.dimensions){
			this.dimensions = new ArrayList<>();
		}
		this.dimensions.add(dimension);
	}
}
