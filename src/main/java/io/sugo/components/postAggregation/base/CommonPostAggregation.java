package io.sugo.components.postAggregation.base;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class CommonPostAggregation extends BasePostAggregation {
	protected String fieldName;

	public CommonPostAggregation(String type) {
		super(type);
	}

	public CommonPostAggregation(String type, String name) {
		super(type, name);
	}

	public CommonPostAggregation(String type, String name, String fieldName) {
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
