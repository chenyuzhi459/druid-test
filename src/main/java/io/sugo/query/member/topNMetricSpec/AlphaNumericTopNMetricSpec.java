package io.sugo.query.member.topNMetricSpec;

import io.sugo.query.member.topNMetricSpec.base.BaseTopNMetricSpec;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class AlphaNumericTopNMetricSpec extends BaseTopNMetricSpec {
	private static final String TOPN_METRIC_TYPE="alphaNumeric";
	private String previousStop;

	public AlphaNumericTopNMetricSpec() {
		super(TOPN_METRIC_TYPE);
	}

	public AlphaNumericTopNMetricSpec(String previousStop) {
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
