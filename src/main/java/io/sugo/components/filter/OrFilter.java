package io.sugo.components.filter;

import io.sugo.components.filter.base.BaseFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwe on 17-7-28.
 */
public class OrFilter extends BaseFilter {
    private static final String FILTER_TYPE  = "or";
    private List<BaseFilter> fields;

    public OrFilter(){
        super(FILTER_TYPE);
    }

    public OrFilter(List<BaseFilter> fields) {
        super(FILTER_TYPE);
        this.fields = fields;
    }

    public List<BaseFilter> getFields() {
        return fields;
    }

    public void setFields(List<BaseFilter> fields) {
        this.fields = fields;
    }

    public void addFilter(BaseFilter filter){
        if(null == this.fields){
            this.fields = new ArrayList<>();
        }
        this.fields.add(filter);
    }
}
