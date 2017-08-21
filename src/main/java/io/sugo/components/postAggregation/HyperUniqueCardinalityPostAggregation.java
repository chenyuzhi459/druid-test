package io.sugo.components.postAggregation;

import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.components.postAggregation.base.CommonPostAggregation;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class HyperUniqueCardinalityPostAggregation extends CommonPostAggregation {
	private static final String POST_AGGREGATION_TYPE = "lucene_hyperUniqueCardinality";
	public HyperUniqueCardinalityPostAggregation() {
		super(POST_AGGREGATION_TYPE);
	}

	public HyperUniqueCardinalityPostAggregation(String name) {
		super(POST_AGGREGATION_TYPE, name);
	}

	public HyperUniqueCardinalityPostAggregation(String name, String fieldName) {
		super(POST_AGGREGATION_TYPE, name, fieldName);
	}
}
