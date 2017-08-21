package io.sugo.components.aggregation.base;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class BaseAggregation {
	protected final String type;

	protected BaseAggregation(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}



}
