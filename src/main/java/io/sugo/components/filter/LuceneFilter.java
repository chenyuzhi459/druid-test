package io.sugo.components.filter;

import io.sugo.components.filter.base.BaseFilter;

/**
 * Created by qwe on 17-7-28.
 */
public class LuceneFilter extends BaseFilter {
    private static final String FILTER_TYPE = "lucene";
    private String query;

    public LuceneFilter() {
        super(FILTER_TYPE);
    }

    public LuceneFilter(String query) {
        super(FILTER_TYPE);
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
