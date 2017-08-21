package io.sugo.query;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.pagingSpec.PagingSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-11.
 */
public class SelectQuery extends CommonQuery {
	private static final String QUERY_TYPE = "lucene_select";
	private List dimensions;
	private BaseFilter filter;
	private boolean descending;
	private PagingSpec pagingSpec;

	public SelectQuery() {
		super(QUERY_TYPE);
	}
	public SelectQuery(String dataSource, String intervals, String granularity,Map<String, Object> context) {
		super(QUERY_TYPE,dataSource,intervals,granularity,context);
	}

	public SelectQuery(String dataSource, String intervals, String granularity,PagingSpec pagingSpec) {
		super(QUERY_TYPE,dataSource,intervals,granularity,null);
		this.pagingSpec = pagingSpec;
	}

	public SelectQuery(String dataSource, String intervals, String granularity,PagingSpec pagingSpec,Map<String, Object> context) {
		super(QUERY_TYPE,dataSource,intervals,granularity,context);
		this.pagingSpec = pagingSpec;
	}

	public List getDimensions() {
		return dimensions;
	}

	public void setDimensions(List dimensions) {
		this.dimensions = dimensions;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public boolean isDescending() {
		return descending;
	}

	public void setDescending(boolean descending) {
		this.descending = descending;
	}

	public PagingSpec getPagingSpec() {
		return pagingSpec;
	}

	public void setPagingSpec(PagingSpec pagingSpec) {
		this.pagingSpec = pagingSpec;
	}

	public void addDimension(Object dimension){
		if(null == this.dimensions){
			this.dimensions = new ArrayList();
		}
		this.dimensions.add(dimension);
	}
}
