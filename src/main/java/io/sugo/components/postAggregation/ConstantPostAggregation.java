package io.sugo.components.postAggregation;

import io.sugo.components.postAggregation.base.BasePostAggregation;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class ConstantPostAggregation extends BasePostAggregation {
	private static final String POST_AGGREGATION_TYPE = "constant";
	private Number value;


	public ConstantPostAggregation() {
		super(POST_AGGREGATION_TYPE);
	}

	public ConstantPostAggregation(String name) {
		super(POST_AGGREGATION_TYPE, name);
	}

	public ConstantPostAggregation(String name,Number value) {
		super(POST_AGGREGATION_TYPE, name);
		this.value = value;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

}
