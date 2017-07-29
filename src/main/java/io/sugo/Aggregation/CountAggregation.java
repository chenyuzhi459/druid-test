package io.sugo.Aggregation;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class CountAggregation extends Aggregation{
	private static final String AGGREGATION_TYPE = "lucene_count";


	public CountAggregation(String name) {
		super(AGGREGATION_TYPE,name);
	}


}
