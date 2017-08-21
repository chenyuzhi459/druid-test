package io.sugo.query.member.topNMetricSpec.base;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class BaseTopNMetricSpec {
	protected final String type;

	public BaseTopNMetricSpec(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
