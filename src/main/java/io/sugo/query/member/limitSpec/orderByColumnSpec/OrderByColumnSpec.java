package io.sugo.query.member.limitSpec.orderByColumnSpec;

/**
 * Created by chenyuzhi on 17-8-7.
 */
public class OrderByColumnSpec {
	//public enum Direction{ ASCENDING, DESCENDING;}
	public static final String DIRECTION_ASCENDING = "ASCENDING";
	public static final String DIRECTION_DESCENDING = "DESCENDING";

	private String dimension;

	/**
	 * dimensionOrder:
	 * 指定比较方法，只能在StringComparator的静态属性中选取
	 * 默认为StringComparator.LEXICOGRAPHIC_NAME = "lexicographic"
	 */
	private String dimensionOrder;
	private String direction;

	public OrderByColumnSpec() {
	}

	public OrderByColumnSpec(String dimension, String dimensionOrder, String direction) {
		this.dimension = dimension;
		this.dimensionOrder = dimensionOrder;
		this.direction = direction;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getDimensionOrder() {
		return dimensionOrder;
	}

	public void setDimensionOrder(String dimensionOrder) {
		this.dimensionOrder = dimensionOrder;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
