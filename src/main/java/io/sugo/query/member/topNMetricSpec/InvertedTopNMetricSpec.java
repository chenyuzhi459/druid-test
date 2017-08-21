package io.sugo.query.member.topNMetricSpec;

import io.sugo.query.member.topNMetricSpec.base.BaseTopNMetricSpec;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class InvertedTopNMetricSpec extends BaseTopNMetricSpec {
	private static final String TOPN_METRIC_TYPE="inverted";
	private BaseTopNMetricSpec metric;

	public InvertedTopNMetricSpec() {
		super(TOPN_METRIC_TYPE);
	}

	public InvertedTopNMetricSpec(BaseTopNMetricSpec metric) {
		super(TOPN_METRIC_TYPE);
		this.metric = metric;
	}

	public BaseTopNMetricSpec getMetric() {
		return metric;
	}

	public void setMetric(BaseTopNMetricSpec metric) {
		this.metric = metric;
	}
}
