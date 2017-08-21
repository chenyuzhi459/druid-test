package io.sugo.components.postAggregation.base;

/**
 * Created by chenyuzhi on 17-8-4.
 */
public class BasePostAggregation {
	protected final String type;
	protected String name;

	public BasePostAggregation(String type) {
		this.type = type;
	}

	public BasePostAggregation(String type,String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
