package io.sugo.query.member.searchQuerySpec;

import io.sugo.query.member.searchQuerySpec.base.BaseSearchQuerySpec;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class ContainsSearchQuerySpec extends BaseSearchQuerySpec {
	private static final String SEARCH_QUERY_TYPE = "contains";
	private String value;
	private boolean caseSensitive;

	public ContainsSearchQuerySpec() {
		super(SEARCH_QUERY_TYPE);
	}

	public ContainsSearchQuerySpec(String value) {
		super(SEARCH_QUERY_TYPE);
		this.value = value;
	}

	public ContainsSearchQuerySpec(String value, boolean caseSensitive) {
		super(SEARCH_QUERY_TYPE);
		this.value = value;
		this.caseSensitive = caseSensitive;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
}
