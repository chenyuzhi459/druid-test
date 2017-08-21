package io.sugo.components.postAggregation;

import io.sugo.components.postAggregation.base.BasePostAggregation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class ArithmeticPostAggregation <T extends BasePostAggregation> extends BasePostAggregation {
	private static final String POST_AGGREGATION_TYPE = "arithmetic";
	private String fn;
	private String ordering;
	private List<T> fields = new LinkedList();

	public ArithmeticPostAggregation() {
		super(POST_AGGREGATION_TYPE);
	}

	public ArithmeticPostAggregation(String name, String fn) {
		super(POST_AGGREGATION_TYPE,name);
		this.fn = fn;
	}

	public ArithmeticPostAggregation(String name, String fn, String ordering) {
		super(POST_AGGREGATION_TYPE,name);
		this.fn = fn;
		this.ordering =ordering;
	}

	public ArithmeticPostAggregation(String name, String fn, String ordering, List<T> fields) {
		super(POST_AGGREGATION_TYPE, name);
		this.fn = fn;
		this.ordering = ordering;
		this.fields = fields;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public List<T> getFields() {
		return fields;
	}

	public void setFields(List<T> fields) {
		this.fields = fields;
	}

	public void addPostAggregationFileds(T field){
		this.fields.add(field);
	}
}
