package io.sugo.Aggregation;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class HyperUniqueAggregation extends Aggregation{
	private static final String AGGREGATION_TYPE = "lucene_hyperUnique";
	private String fieldName;


	public HyperUniqueAggregation(String name,String fieldName) {
		super(AGGREGATION_TYPE, name);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


}
