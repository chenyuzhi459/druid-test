package io.sugo.components.aggregation;

import io.sugo.components.aggregation.base.CommonAggregation;

/**
 * Created by chenyuzhi on 17-8-2.
 */
public class LongMaxAggregation extends CommonAggregation {
	private static final String AGGREGATION_TYPE = "lucene_longMax";
	public LongMaxAggregation(String name, String fieldName){
		super(AGGREGATION_TYPE,name,fieldName);
	}

}
