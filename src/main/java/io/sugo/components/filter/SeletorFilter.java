package io.sugo.components.filter;

import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

/**
 * Created by qwe on 17-7-28.
 */
public class SeletorFilter extends CommonFilter{
    private static final String FILTER_TYPE = "selector";
    private String value;

    public SeletorFilter() {
        super(FILTER_TYPE);
    }

    public SeletorFilter(String dimension,String value) {
        super(FILTER_TYPE, dimension);
        this.value = value;
    }

    public SeletorFilter(String type, String dimension, String value, BaseExtractionFn extractionFn) {
        super(type, dimension, extractionFn);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
