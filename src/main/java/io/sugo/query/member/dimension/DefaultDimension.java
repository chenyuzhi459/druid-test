package io.sugo.query.member.dimension;

import io.sugo.query.member.dimension.base.BaseDimension;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class DefaultDimension extends BaseDimension{
	private static final String DIMENSION_TYPE = "default";

	public DefaultDimension() {
		super(DIMENSION_TYPE);
	}

	public DefaultDimension(String dimension) {
		super(DIMENSION_TYPE, dimension);
	}

	public DefaultDimension(String dimension, String outputName) {
		super(DIMENSION_TYPE, dimension, outputName);
	}

}
