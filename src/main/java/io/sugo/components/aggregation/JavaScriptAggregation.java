package io.sugo.components.aggregation;

import io.sugo.components.aggregation.base.NameableAggregation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class JavaScriptAggregation extends NameableAggregation {
	private static final String AGGREGATION_TYPE = "lucene_javascript";
	private List<String> fieldNames;
	private  String fnAggregate;
	private String fnReset;
	private String fnCombine;

	public JavaScriptAggregation() {
		super(AGGREGATION_TYPE);
	}

	public JavaScriptAggregation(String name,String fnAggregate, String fnReset, String fnCombine) {
		super(AGGREGATION_TYPE, name);
		this.fnAggregate = fnAggregate;
		this.fnReset = fnReset;
		this.fnCombine = fnCombine;
	}

	public JavaScriptAggregation(String name,String fnAggregate, String fnReset, String fnCombine, List<String> fieldNames) {
		super(AGGREGATION_TYPE, name);
		this.fnAggregate = fnAggregate;
		this.fnReset = fnReset;
		this.fnCombine = fnCombine;
		this.fieldNames = fieldNames;
	}

	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public String getFnAggregate() {
		return fnAggregate;
	}

	public void setFnAggregate(String fnAggregate) {
		this.fnAggregate = fnAggregate;
	}

	public String getFnReset() {
		return fnReset;
	}

	public void setFnReset(String fnReset) {
		this.fnReset = fnReset;
	}

	public String getFnCombine() {
		return fnCombine;
	}

	public void setFnCombine(String fnCombine) {
		this.fnCombine = fnCombine;
	}

	public void addFieldName(String fieldNmae){
		if(null == this.fieldNames){
			this.fieldNames = new ArrayList<>();
		}
		this.fieldNames.add(fieldNmae);
	}
}
