package io.sugo.query;

import io.sugo.query.base.WithIntervalsQuery;
import io.sugo.query.member.columnIncluderator.base.BaseColumnIncluderator;

import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class SegmentMetadataQuery extends WithIntervalsQuery {
	private static final String QUERY_TYPE = "lucene_segmentMetadata";
	private BaseColumnIncluderator toInclude;
	private boolean merge;
	//private Set analysisTypes;    暂时不用设置
	private boolean usingDefaultInterval;
	private boolean lenientAggregatorMerge;


	public SegmentMetadataQuery() {
		super(QUERY_TYPE);
	}

	public SegmentMetadataQuery(String dataSource, String intervals) {
		super(QUERY_TYPE,dataSource,intervals,null);
	}

	public SegmentMetadataQuery(String dataSource,
								String intervals,
								BaseColumnIncluderator toInclude,
								boolean merge,
								boolean usingDefaultInterval,
								boolean lenientAggregatorMerge) {
		super(QUERY_TYPE, dataSource, intervals);
		this.toInclude = toInclude;
		this.merge = merge;
		this.usingDefaultInterval = usingDefaultInterval;
		this.lenientAggregatorMerge = lenientAggregatorMerge;
	}

	public SegmentMetadataQuery( String dataSource,
								 String intervals,
								 BaseColumnIncluderator toInclude,
								 boolean merge,
								 boolean usingDefaultInterval,
								 boolean lenientAggregatorMerge,
								 Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
		this.toInclude = toInclude;
		this.merge = merge;
		this.usingDefaultInterval = usingDefaultInterval;
		this.lenientAggregatorMerge = lenientAggregatorMerge;
	}

	public BaseColumnIncluderator getToInclude() {
		return toInclude;
	}

	public void setToInclude(BaseColumnIncluderator toInclude) {
		this.toInclude = toInclude;
	}

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public boolean isUsingDefaultInterval() {
		return usingDefaultInterval;
	}

	public void setUsingDefaultInterval(boolean usingDefaultInterval) {
		this.usingDefaultInterval = usingDefaultInterval;
	}

	public boolean isLenientAggregatorMerge() {
		return lenientAggregatorMerge;
	}

	public void setLenientAggregatorMerge(boolean lenientAggregatorMerge) {
		this.lenientAggregatorMerge = lenientAggregatorMerge;
	}
}
