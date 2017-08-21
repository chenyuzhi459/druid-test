package io.sugo.components.filter;

import io.sugo.components.filter.base.CommonFilter;
import io.sugo.query.member.extractionFn.base.BaseExtractionFn;

/**
 * Created by qwe on 17-7-28.
 */
public class BoundFilter extends CommonFilter {
    private static final String FILTER_TYPE = "bound";
    private String lower;
    private String upper;
    private boolean lowerStrict;
    private boolean upperStrict;
    private boolean alphaNumeric;

    public BoundFilter() {
        super(FILTER_TYPE);
    }

    public BoundFilter( String dimension) {
        super(FILTER_TYPE, dimension);
    }

    public BoundFilter( String dimension, boolean alphaNumeric) {
        super(FILTER_TYPE, dimension);
        this.alphaNumeric = alphaNumeric;
    }

    public BoundFilter(String dimension, String lower, String upper, boolean lowerStrict, boolean upperStrict, boolean alphaNumeric) {
        super(FILTER_TYPE, dimension);
        this.lower = lower;
        this.upper = upper;
        this.lowerStrict = lowerStrict;
        this.upperStrict = upperStrict;
        this.alphaNumeric = alphaNumeric;
    }

    public BoundFilter( String dimension, String lower, String upper, boolean lowerStrict, boolean upperStrict, boolean alphaNumeric, BaseExtractionFn extractionFn) {
        super(FILTER_TYPE, dimension, extractionFn);
        this.lower = lower;
        this.upper = upper;
        this.lowerStrict = lowerStrict;
        this.upperStrict = upperStrict;
        this.alphaNumeric = alphaNumeric;
    }

    public String getLower() {
        return lower;
    }

    public void setLower(String lower) {
        this.lower = lower;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public boolean isLowerStrict() {
        return lowerStrict;
    }

    public void setLowerStrict(boolean lowerStrict) {
        this.lowerStrict = lowerStrict;
    }

    public boolean isUpperStrict() {
        return upperStrict;
    }

    public void setUpperStrict(boolean upperStrict) {
        this.upperStrict = upperStrict;
    }

    public boolean isAlphaNumeric() {
        return alphaNumeric;
    }

    public void setAlphaNumeric(boolean alphaNumeric) {
        this.alphaNumeric = alphaNumeric;
    }
}