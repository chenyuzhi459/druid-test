package io.sugo.query;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.dimension.base.BaseDimension;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class FirstNQuery extends CommonQuery {
	private static final String QUERY_TYPE = "lucene_firstN";
	private BaseDimension dimension;
	private int threshold;
	private boolean formatted;
	private BaseFilter filter;

	public FirstNQuery() {
		super(QUERY_TYPE);
	}

	public FirstNQuery(String dataSource, String intervals, String granularity, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
	}

	public FirstNQuery( String dataSource,
						String intervals,
						String granularity,
						int threshold,
						boolean formatted, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.threshold = threshold;
		this.formatted = formatted;
	}

	public FirstNQuery( String dataSource,
						String intervals,
						String granularity,
						BaseDimension dimension,
						int threshold,
						boolean formatted,
						BaseFilter filter,
						Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
		this.dimension = dimension;
		this.threshold = threshold;
		this.formatted = formatted;
		this.filter = filter;
	}

	public BaseDimension getDimension() {
		return dimension;
	}

	public void setDimension(BaseDimension dimension) {
		this.dimension = dimension;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public boolean isFormatted() {
		return formatted;
	}

	public void setFormatted(boolean formatted) {
		this.formatted = formatted;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}
}
