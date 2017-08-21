package io.sugo.components.having;

import io.sugo.components.having.base.BaseHavingSpec;

/**
 * Created by chenyuzhi on 17-8-7.
 */
public class GreaterThanHavingSpec extends BaseHavingSpec {
	private static final String HAVING_TYPE = "greaterThan";
	private String aggregation;
	private Number value;
	public GreaterThanHavingSpec() {
		super(HAVING_TYPE);
	}

	public GreaterThanHavingSpec(String aggregation, Number value) {
		super(HAVING_TYPE);
		this.aggregation = aggregation;
		this.value = value;
	}

	public String getAggregation() {
		return aggregation;
	}

	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}
}
