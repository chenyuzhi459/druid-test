package io.sugo.components.filter;

import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;
import io.sugo.query.member.searchQuerySpec.base.BaseSearchQuerySpec;

/**
 * Created by qwe on 17-7-28.
 */
public class SearchFilter extends CommonFilter{
    private static final String FILTER_TYPE= "search";
    private BaseSearchQuerySpec query;

    public SearchFilter() {
        super(FILTER_TYPE);
    }

    public SearchFilter(String dimension) {
        super(FILTER_TYPE, dimension);
    }

    public SearchFilter(String dimension, BaseSearchQuerySpec query) {
        super(FILTER_TYPE, dimension);
        this.query = query;
    }

    public SearchFilter(String type, String dimension, BaseSearchQuerySpec query, BaseExtractionFn extractionFn) {
        super(type, dimension, extractionFn);
        this.query = query;
    }

    public BaseSearchQuerySpec getQuery() {
        return query;
    }

    public void setQuery(BaseSearchQuerySpec query) {
        this.query = query;
    }
}
