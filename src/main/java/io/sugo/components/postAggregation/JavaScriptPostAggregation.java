package io.sugo.components.postAggregation;

import io.sugo.components.postAggregation.base.BasePostAggregation;
import io.sugo.components.postAggregation.base.CommonPostAggregation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class JavaScriptPostAggregation extends BasePostAggregation {
	private static final String POST_AGGREGATION_TYPE = "javascript";
	private List<String> fieldNames;
	private String function;

	public JavaScriptPostAggregation() {
		super(POST_AGGREGATION_TYPE);
	}

	public JavaScriptPostAggregation(String name) {
		super(POST_AGGREGATION_TYPE, name);
	}

	public JavaScriptPostAggregation(String name, String function) {
		super(POST_AGGREGATION_TYPE, name);
		this.function = function;
	}

	public JavaScriptPostAggregation(String name, String function, List<String> fieldNames) {
		super(POST_AGGREGATION_TYPE, name);
		this.function = function;
		this.fieldNames = fieldNames;
	}


	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void addFieldName(String fieldName){
		if(null == this.fieldNames){
			this.fieldNames = new ArrayList<>();
		}
		this.fieldNames.add(fieldName);
	}
}
