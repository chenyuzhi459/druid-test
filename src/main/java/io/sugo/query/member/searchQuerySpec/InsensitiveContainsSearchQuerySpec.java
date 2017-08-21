package io.sugo.query.member.searchQuerySpec;

import io.sugo.query.member.searchQuerySpec.base.BaseSearchQuerySpec;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class InsensitiveContainsSearchQuerySpec extends BaseSearchQuerySpec {
	private static final String SEARCH_QUERY_TYPE = "insensitive_contains";
	private String value;

	public InsensitiveContainsSearchQuerySpec() {
		super(SEARCH_QUERY_TYPE);
	}

	public InsensitiveContainsSearchQuerySpec(String value) {
		super(SEARCH_QUERY_TYPE);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
