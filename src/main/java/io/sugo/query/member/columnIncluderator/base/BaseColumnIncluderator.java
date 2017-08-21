package io.sugo.query.member.columnIncluderator.base;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class BaseColumnIncluderator {
	protected final String type;

	public BaseColumnIncluderator(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
