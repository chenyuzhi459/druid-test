package io.sugo.query.member.topNMetricSpec;

import io.sugo.query.member.topNMetricSpec.base.BaseTopNMetricSpec;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class LexicographicTopNMetricSpec extends BaseTopNMetricSpec {
	private static final String TOPN_METRIC_TYPE="lexicographic";
	private String previousStop;

	public LexicographicTopNMetricSpec() {
		super(TOPN_METRIC_TYPE);
	}

	public LexicographicTopNMetricSpec(String previousStop) {
		super(TOPN_METRIC_TYPE);
		this.previousStop = previousStop;
	}

	public String getPreviousStop() {
		return previousStop;
	}

	public void setPreviousStop(String previousStop) {
		this.previousStop = previousStop;
	}
}
