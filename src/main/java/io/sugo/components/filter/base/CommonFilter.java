package io.sugo.components.filter.base;

import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

/**
 * Created by chenyuzhi on 17-8-9.
 */
public class CommonFilter extends WithDimensionFilter {
	protected BaseExtractionFn extractionFn;
	public CommonFilter(String type) {
		super(type);
	}

	public CommonFilter(String type, String dimension) {
		super(type, dimension);
	}

	public CommonFilter(String type, String dimension, BaseExtractionFn extractionFn) {
		super(type, dimension);
		this.extractionFn = extractionFn;
	}

	public BaseExtractionFn getExtractionFn() {
		return extractionFn;
	}

	public void setExtractionFn(BaseExtractionFn extractionFn) {
		this.extractionFn = extractionFn;
	}
}
