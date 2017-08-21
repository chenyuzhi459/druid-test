package io.sugo.components.postAggregation;

import io.sugo.components.postAggregation.base.CommonPostAggregation;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class FieldAccessPostAggregation extends CommonPostAggregation {
	private static final String POST_AGGREGATION_TYPE = "fieldAccess";
	public FieldAccessPostAggregation() {
		super(POST_AGGREGATION_TYPE);
	}

	public FieldAccessPostAggregation(String name) {
		super(POST_AGGREGATION_TYPE, name);
	}

	public FieldAccessPostAggregation(String name, String fieldName) {
		super(POST_AGGREGATION_TYPE, name, fieldName);
	}
}
