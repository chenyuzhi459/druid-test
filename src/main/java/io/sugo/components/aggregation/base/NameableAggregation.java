package io.sugo.components.aggregation.base;

import io.sugo.components.aggregation.base.BaseAggregation;

/**
 * Created by chenyuzhi on 17-8-3.
 */
public class NameableAggregation extends BaseAggregation {
	protected String name;

	protected NameableAggregation(String type) {
		super(type);
	}

	protected NameableAggregation(String type,String name) {
		this(type);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
