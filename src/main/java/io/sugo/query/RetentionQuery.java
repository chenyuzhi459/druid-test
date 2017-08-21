package io.sugo.query;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.member.step.CommonStep;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-18.
 */
public class RetentionQuery extends CommonQuery {
	private static final String QUERY_TYPE = "retention";
	private BaseFilter filter;
	private String field;
	private CommonStep startStep;
	private CommonStep returnStep;

	public RetentionQuery() {
		super(QUERY_TYPE);
	}

	public RetentionQuery( String dataSource, String intervals,String granularity) {
		super(QUERY_TYPE, dataSource, intervals,granularity,null);
	}

	public RetentionQuery(String dataSource, String intervals, String granularity, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, granularity, context);
	}

	public RetentionQuery(String queryType,
						  String dataSource,
						  String intervals,
						  String granularity,
						  BaseFilter filter,
						  String field,
						  CommonStep startStep,
						  CommonStep returnStep,
						  Map<String, Object> context) {
		super(queryType, dataSource, intervals, granularity, context);
		this.filter = filter;
		this.field = field;
		this.startStep = startStep;
		this.returnStep = returnStep;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public CommonStep getStartStep() {
		return startStep;
	}

	public void setStartStep(CommonStep startStep) {
		this.startStep = startStep;
	}

	public CommonStep getReturnStep() {
		return returnStep;
	}

	public void setReturnStep(CommonStep returnStep) {
		this.returnStep = returnStep;
	}
}
