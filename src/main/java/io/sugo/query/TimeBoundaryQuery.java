package io.sugo.query;

import io.sugo.query.base.BaseQuery;
import io.sugo.query.base.WithIntervalsQuery;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-12.
 */
public class TimeBoundaryQuery extends WithIntervalsQuery {
	public static final String BOUND_MAXTIME = "maxTime";
	public static final String BOUND_MINTIME = "minTime";
	private static final String QUERY_TYPE = "lucene_timeBoundary";
	private String bound;
	public TimeBoundaryQuery() {
		super(QUERY_TYPE);
	}

	public TimeBoundaryQuery(String dataSource, String intervals, String bound) {
		super(QUERY_TYPE, dataSource, intervals);
		this.bound = bound;
	}

	public TimeBoundaryQuery(String dataSource, String intervals, String bound, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
		this.bound = bound;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}
}
