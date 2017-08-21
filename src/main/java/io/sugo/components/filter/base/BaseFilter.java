package io.sugo.components.filter.base;

/**
 * Created by chenyuzhi on 17-8-9.
 */
public class BaseFilter {
	protected final String type;

	public BaseFilter(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
