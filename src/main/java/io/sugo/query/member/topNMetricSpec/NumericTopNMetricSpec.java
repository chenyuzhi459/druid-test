package io.sugo.query.member.topNMetricSpec;

import io.sugo.query.member.topNMetricSpec.base.BaseTopNMetricSpec;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class NumericTopNMetricSpec extends BaseTopNMetricSpec{
	private static final String TOPN_METRIC_TYPE="numeric";
	private String metric;

	public NumericTopNMetricSpec() {
		super(TOPN_METRIC_TYPE);
	}

	public NumericTopNMetricSpec(String metric) {
		super(TOPN_METRIC_TYPE);
		this.metric = metric;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}
}
