package io.sugo.query.member.searchQuerySpec.base;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class BaseSearchQuerySpec {
	protected final String type;

	public BaseSearchQuerySpec(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
