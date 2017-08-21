package io.sugo.query;



import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.query.base.CommonQuery;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


/**
 * Created by chenyuzhi on 17-7-28.
 */

public class TimeseriesQuery <T extends BaseAggregation, P extends BasePostAggregation> extends CommonQuery {
	private static final String QUERY_TYPE = "lucene_timeseries";
	private BaseFilter filter;
	private List<T> aggregations = new ArrayList<>();
	private List<P> postAggregations = new ArrayList<>();


	public TimeseriesQuery(){
		super(QUERY_TYPE);
	}

	public TimeseriesQuery(String dataSource, String granularity, String intervals,Map<String, Object> context) {
		super(QUERY_TYPE,dataSource,intervals,granularity,context);

	}




	public TimeseriesQuery(String dataSource,
						   String granularity,
						   String intervals,
						   Map<String, Object> context,
						   BaseFilter filter,
						   List<T> aggregations,
						   List<P> postAggregations) {
		super(QUERY_TYPE,dataSource,intervals,granularity,context);
		this.aggregations = aggregations;
		this.postAggregations = postAggregations;
		this.filter = filter;
	}

	public void setAggregations(List<T> aggregations) {
		this.aggregations = aggregations;
	}

	public  List<T> getAggregations() {
		return aggregations;
	}

	public List<P> getPostAggregations() {
		return postAggregations;
	}

	public void setPostAggregations(List<P> postAggregations) {
		this.postAggregations = postAggregations;
	}

	public  void addAggregation(T t){
		if(null == this.aggregations){
			this.aggregations = new ArrayList<>();
		}
		this.aggregations.add(t);
	}

	public void addPostAggregation(P p){
		if(null == this.postAggregations){
			this.postAggregations = new ArrayList<>();
		}
		this.postAggregations.add(p);
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

}
