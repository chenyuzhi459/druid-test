package io.sugo.components.having;

import io.sugo.components.having.base.BaseHavingSpec;

/**
 * Created by chenyuzhi on 17-8-8.
 */
public class EqualToHavingSpec extends BaseHavingSpec {
	private static final String HAVING_TYPE = "equalTo";
	private String aggregation;
	private Number value;
	public EqualToHavingSpec() {
		super(HAVING_TYPE);
	}

	public EqualToHavingSpec(String aggregation, Number value) {
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
