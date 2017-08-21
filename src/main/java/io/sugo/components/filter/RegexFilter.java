package io.sugo.components.filter;

import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

/**
 * Created by qwe on 17-7-28.
 */
public class RegexFilter extends CommonFilter {
    private static final String FILTER_TYPE = "regex";
    private String pattern;
    //private String pattern = "^c2.*";

    public RegexFilter() {
        super(FILTER_TYPE);
    }

    public RegexFilter(String dimension, String pattern) {
        super(FILTER_TYPE, dimension);
        this.pattern = pattern;
    }

    public RegexFilter(String type, String dimension, String pattern, BaseExtractionFn extractionFn) {
        super(type, dimension, extractionFn);
        this.pattern = pattern;
    }


    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
