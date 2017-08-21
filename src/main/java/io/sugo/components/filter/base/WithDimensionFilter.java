package io.sugo.components.filter.base;

/**
 * Created by chenyuzhi on 17-8-9.
 */
public class WithDimensionFilter extends BaseFilter {
	protected String dimension;
	public WithDimensionFilter(String type) {
		super(type);
	}

	public WithDimensionFilter(String type, String dimension) {
		super(type);
		this.dimension = dimension;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
}
