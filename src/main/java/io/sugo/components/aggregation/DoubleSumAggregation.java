package io.sugo.components.aggregation;

import io.sugo.components.aggregation.base.CommonAggregation;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class DoubleSumAggregation extends CommonAggregation {
	private static final String AGGREGATION_TYPE = "lucene_doubleSum";
	public DoubleSumAggregation() {
		super(AGGREGATION_TYPE);
	}

	public DoubleSumAggregation(String name, String fieldName) {
		super(AGGREGATION_TYPE, name, fieldName);
	}
}
