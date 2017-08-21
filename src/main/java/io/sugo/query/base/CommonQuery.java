package io.sugo.query.base;

import io.sugo.components.aggregation.base.BaseAggregation;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class CommonQuery extends WithIntervalsQuery {
	protected String granularity;

	public CommonQuery(String queryType) {
		super(queryType);
	}

	public CommonQuery(String queryType, String dataSource) {
		super(queryType, dataSource);
	}



	public CommonQuery(String queryType, String dataSource,String intervals) {
		super(queryType, dataSource,intervals);
	}

	public CommonQuery(String queryType,
					   String dataSource,
					   String intervals,
					   String granularity,
					   Map<String, Object> context)
	{
		super(queryType, dataSource,intervals, context);
		this.granularity = granularity;
	}

	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
}
