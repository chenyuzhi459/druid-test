package io.sugo.query;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.query.base.WithIntervalsQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class ScanQuery extends WithIntervalsQuery {
	private static final String QUERY_TYPE = "lucene_scan";
	private String resultFormat;   //resultFormat有三个值可以选，分别是list,compactedList,valueVector
	private int batchSize;
	private int limit;
	private BaseFilter filter;
	private List<String> columns;

	public ScanQuery() {
		super(QUERY_TYPE);
	}

	public ScanQuery(String dataSource, String intervals, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
	}

	public ScanQuery(String dataSource,
					 String intervals,
					 String resultFormat,
					 int batchSize,
					 int limit,
					 Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
		this.resultFormat = resultFormat;
		this.batchSize = batchSize;
		this.limit = limit;
	}

	public String getResultFormat() {
		return resultFormat;
	}

	public void setResultFormat(String resultFormat) {
		this.resultFormat = resultFormat;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public void addColumn(String column){
		if(null == this.columns){
			this.columns = new ArrayList<>();
		}
		this.columns.add(column);
	}
}
