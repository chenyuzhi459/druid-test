package io.sugo.Aggregation;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public abstract class Aggregation {
	protected final String type;
	protected String name;

	protected Aggregation(String type, String name) {
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
