package io.sugo.query.member.dimension.base;

/**
 * Created by chenyuzhi on 17-8-5.
 */
public class BaseDimension {
	protected final String type;
	protected String dimension;
	protected String outputName;
	public BaseDimension(String type){
		this.type = type;
	}

	public BaseDimension(String type, String dimension) {
		this.type = type;
		this.dimension = dimension;
	}

	public BaseDimension(String type, String dimension, String outputName) {
		this.type = type;
		this.dimension = dimension;
		this.outputName = outputName;
	}

	public String getType() {
		return type;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
}
