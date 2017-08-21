package io.sugo.components.filter;

import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwe on 17-7-28.
 */
public class InFilter extends CommonFilter {
    private static final String FILTER_TYPE = "in";
    private List<String> values;

    public InFilter() {
        super(FILTER_TYPE);
    }

    public InFilter(String dimension) {
        super(FILTER_TYPE, dimension);
    }

    public InFilter(String dimension, List<String> values) {
        super(FILTER_TYPE, dimension);
        this.values = values;
    }

    public InFilter(String dimension, List<String> values,BaseExtractionFn extractionFn) {
        super(FILTER_TYPE, dimension, extractionFn);
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void addValue(String value){
        if(null == this.values){
            this.values = new ArrayList<>();
        }
        this.values.add(value);
    }
}