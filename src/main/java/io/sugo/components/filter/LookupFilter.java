package io.sugo.components.filter;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.filter.base.WithDimensionFilter;

/**
 * Created by qwe on 17-7-28.
 */
public class LookupFilter extends WithDimensionFilter {
    private static final String FILTER_TYPE = "lookup";
    private String lookup;

    public LookupFilter() {
        super(FILTER_TYPE);
    }

    public LookupFilter(String dimension) {
        super(FILTER_TYPE, dimension);
    }

    public LookupFilter(String dimension, String lookup) {
        super(FILTER_TYPE, dimension);
        this.lookup = lookup;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }


}
