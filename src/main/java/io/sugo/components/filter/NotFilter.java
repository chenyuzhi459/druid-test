package io.sugo.components.filter;

import io.sugo.components.filter.base.BaseFilter;

/**
 * Created by qwe on 17-7-28.
 */
public class NotFilter extends BaseFilter {
    private static final String FILTER_TYPE = "not";
    private BaseFilter field;

    public NotFilter() {
        super(FILTER_TYPE);
    }

    public NotFilter(BaseFilter field) {
        super(FILTER_TYPE);
        this.field = field;
    }

    public BaseFilter getField() {
        return field;
    }

    public void setField(BaseFilter field) {
        this.field = field;
    }
}
