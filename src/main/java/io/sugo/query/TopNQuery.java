package io.sugo.query;

import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.dimension.base.BaseDimension;
import io.sugo.query.member.topNMetricSpec.base.BaseTopNMetricSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class TopNQuery  extends CommonQuery {
	private static final String QUERY_TYPE = "lucene_topN";
	private BaseDimension dimension;
	private BaseTopNMetricSpec metric;
	private int threshold;
	private BaseFilter filter;
	private List<BaseAggregation> aggregations;
	private List<BasePostAggregation> postAggregations;

	public TopNQuery() {
		super(QUERY_TYPE);
	}

	public TopNQuery( String dataSource, String intervals, String granularity) {
		super(QUERY_TYPE, dataSource, intervals, granularity, null);
	}

	public TopNQuery(String dataSource, String intervals, String granularity, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
	}

	public TopNQuery(String dataSource,
					 String intervals,
					 String granularity,
					 BaseDimension dimension,
					 BaseTopNMetricSpec metric,
					 int threshold,
					 BaseFilter filter,
					 List<BaseAggregation> aggregations,
					 List<BasePostAggregation> postAggregations,
					 Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.dimension = dimension;
		this.metric = metric;
		this.threshold = threshold;
		this.filter = filter;
		this.aggregations = aggregations;
		this.postAggregations = postAggregations;
	}


	public BaseDimension getDimension() {
		return dimension;
	}

	public void setDimension(BaseDimension dimension) {
		this.dimension = dimension;
	}

	public BaseTopNMetricSpec getMetric() {
		return metric;
	}

	public void setMetric(BaseTopNMetricSpec metric) {
		this.metric = metric;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
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
