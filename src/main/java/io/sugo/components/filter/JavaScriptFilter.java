package io.sugo.components.filter;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;
import io.sugo.query.member.javaScriptConfig.JavaScriptConfig;

/**
 * Created by qwe on 17-7-28.
 */
public class JavaScriptFilter extends CommonFilter {
    private static final String FILTER_TYPE = "javascript";
    private String function;
    private JavaScriptConfig config;

    public JavaScriptFilter() {
        super(FILTER_TYPE);
    }

    public JavaScriptFilter(String dimension) {
        super(FILTER_TYPE, dimension);
        this.config = new JavaScriptConfig(false);
    }

    public JavaScriptFilter(String dimension, String function) {
        super(FILTER_TYPE, dimension);
        this.function = function;
        this.config = new JavaScriptConfig(false);
    }

    public JavaScriptFilter(String dimension, String function, JavaScriptConfig config) {
        super(FILTER_TYPE, dimension);
        this.function = function;
        this.config = config;
    }

    public JavaScriptFilter(String dimension, String function, JavaScriptConfig config, BaseExtractionFn extractionFn) {
        super(FILTER_TYPE, dimension, extractionFn);
        this.function = function;
        this.config = config;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public JavaScriptConfig getConfig() {
        return config;
    }

    public void setConfig(JavaScriptConfig config) {
        this.config = config;
    }
}
