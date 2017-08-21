package io.sugo.components.aggregation;

import io.sugo.components.aggregation.base.CommonAggregation;

/**
 * Created by chenyuzhi on 17-8-3.
 */
public class ThetaSketchAggregation extends CommonAggregation{
	private static final String AGGREGATION_TYPE = "lucene_thetaSketch";
	private int size;
	private boolean shouldFinalize;
	private boolean isInputThetaSketch;
	private int errorBoundsStdDev;

	public ThetaSketchAggregation(String name, String fieldName) {
		super(AGGREGATION_TYPE, name, fieldName);
	}

	public ThetaSketchAggregation(String name, String fieldName, int size,
								  boolean shouldFinalize, boolean isInputThetaSketch,
								  int errorBoundsStdDev){
		this(name,fieldName);
		this.size = size;
		this.shouldFinalize =shouldFinalize;
		this.isInputThetaSketch = isInputThetaSketch;
		this.errorBoundsStdDev = errorBoundsStdDev;
		//this.trunc = trunc;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isShouldFinalize() {
		return shouldFinalize;
	}

	public void setShouldFinalize(boolean shouldFinalize) {
		this.shouldFinalize = shouldFinalize;
	}

	public boolean isIsInputThetaSketch() {
		return isInputThetaSketch;
	}

	public void setIsInputThetaSketch(boolean inputThetaSketch) {
		isInputThetaSketch = inputThetaSketch;
	}

	public int getErrorBoundsStdDev() {
		return errorBoundsStdDev;
	}

	public void setErrorBoundsStdDev(int errorBoundsStdDev) {
		this.errorBoundsStdDev = errorBoundsStdDev;
	}

/*	public boolean isTrunc() {
		return trunc;
	}

	public void setTrunc(boolean trunc) {
		this.trunc = trunc;
	}*/
}
