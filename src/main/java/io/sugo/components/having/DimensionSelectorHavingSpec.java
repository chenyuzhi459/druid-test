package io.sugo.components.having;

import io.sugo.components.having.base.BaseHavingSpec;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

/**
 * Created by chenyuzhi on 17-8-8.
 */
public class DimensionSelectorHavingSpec extends BaseHavingSpec {
	private static final String HAVING_TYPE = "dimSelector";
	private String dimension;
	private String value;
	private BaseExtractionFn extractionFn;

	public DimensionSelectorHavingSpec() {
		super(HAVING_TYPE);
	}

	public DimensionSelectorHavingSpec( String dimension, String value) {
		super(HAVING_TYPE);
		this.dimension = dimension;
		this.value = value;
	}

	public DimensionSelectorHavingSpec( String dimension, String value, BaseExtractionFn extractionFn) {
		super(HAVING_TYPE);
		this.dimension = dimension;
		this.value = value;
		this.extractionFn = extractionFn;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BaseExtractionFn getExtractionFn() {
		return extractionFn;
	}

	public void setExtractionFn(BaseExtractionFn extractionFn) {
		this.extractionFn = extractionFn;
	}
}
