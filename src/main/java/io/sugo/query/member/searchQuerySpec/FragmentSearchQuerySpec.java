package io.sugo.query.member.searchQuerySpec;

import io.sugo.query.member.searchQuerySpec.base.BaseSearchQuerySpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-10.
 */
public class FragmentSearchQuerySpec extends BaseSearchQuerySpec {
	private static final String SEARCH_QUERY_TYPE = "fragment";
	private List<String> values;
	private boolean caseSensitive;
	public FragmentSearchQuerySpec() {
		super(SEARCH_QUERY_TYPE);
	}

	public FragmentSearchQuerySpec(boolean caseSensitive) {
		super(SEARCH_QUERY_TYPE);
		this.caseSensitive = caseSensitive;
	}

	public FragmentSearchQuerySpec(boolean caseSensitive, List<String> values) {
		super(SEARCH_QUERY_TYPE);
		this.values = values;
		this.caseSensitive = caseSensitive;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void addValue(String value){
		if(null == this.values){
			this.values = new ArrayList<>();
		}
		this.values.add(value);
	}
}
