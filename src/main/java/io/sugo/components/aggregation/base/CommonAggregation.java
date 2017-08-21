package io.sugo.components.aggregation.base;

/**
 * Created by chenyuzhi on 17-8-3.
 */
public class CommonAggregation extends NameableAggregation {
	protected String fieldName;

	protected CommonAggregation(String type) {
		super(type);
	}

	protected CommonAggregation(String type, String name) {
		super(type, name);
	}

	protected CommonAggregation(String type, String name,String fieldName) {
		this(type, name);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
