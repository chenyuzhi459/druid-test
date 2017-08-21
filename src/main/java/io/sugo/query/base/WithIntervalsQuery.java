package io.sugo.query.base;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class WithIntervalsQuery extends  BaseQuery {
	protected String intervals;

	public WithIntervalsQuery(String queryType) {
		super(queryType);
	}

	public WithIntervalsQuery(String queryType, String dataSource) {
		super(queryType, dataSource);
	}

	public WithIntervalsQuery(String queryType, String dataSource,String intervals) {
		super(queryType, dataSource,null);
		this.intervals = intervals;
	}

	public WithIntervalsQuery(String queryType, String dataSource, String intervals, Map<String, Object> context) {
		super(queryType, dataSource,context);
		this.intervals = intervals;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}
}
