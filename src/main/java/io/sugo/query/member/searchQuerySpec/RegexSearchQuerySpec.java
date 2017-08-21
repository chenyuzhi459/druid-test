package io.sugo.query.member.searchQuerySpec;

import io.sugo.query.member.searchQuerySpec.base.BaseSearchQuerySpec;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class RegexSearchQuerySpec extends BaseSearchQuerySpec {
	private static final String SEARCH_QUERY_TYPE = "regex";
	private String pattern;
	public RegexSearchQuerySpec() {
		super(SEARCH_QUERY_TYPE);
	}

	public RegexSearchQuerySpec( String pattern) {
		super(SEARCH_QUERY_TYPE);
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
