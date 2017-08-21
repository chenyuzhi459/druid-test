package io.sugo.query;

import io.sugo.components.filter.base.BaseFilter;
import io.sugo.query.base.CommonQuery;
import io.sugo.query.base.WithIntervalsQuery;
import io.sugo.query.member.dimension.base.BaseDimension;
import io.sugo.query.member.periodGranularity.PeriodGranularity;
import io.sugo.query.member.step.CommonStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-19.
 */
public class FunnelQuery extends WithIntervalsQuery {
	private static final String QUERY_TYPE = "funnel";
	private PeriodGranularity granularity;
	private BaseFilter filter;
	private BaseDimension dimension;  //此dimension用于groupBy分组
	private String field;
	private List<CommonStep> steps;
	private PeriodGranularity slidingWindow;

	public FunnelQuery() {
		super(QUERY_TYPE);
	}


	public FunnelQuery( String dataSource, String intervals, Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
	}


	public FunnelQuery(String dataSource,
					   String intervals,
					   PeriodGranularity granularity,
					   BaseFilter filter,
					   BaseDimension dimension,
					   String field,
					   List<CommonStep> steps,
					   PeriodGranularity slidingWindow,
					   Map<String, Object> context) {
		super(QUERY_TYPE, dataSource, intervals, context);
		this.granularity = granularity;
		this.filter = filter;
		this.dimension = dimension;
		this.field = field;
		this.steps = steps;
		this.slidingWindow = slidingWindow;
	}

	public PeriodGranularity getGranularity() {
		return granularity;
	}

	public void setGranularity(PeriodGranularity granularity) {
		this.granularity = granularity;
	}

	public BaseFilter getFilter() {
		return filter;
	}

	public void setFilter(BaseFilter filter) {
		this.filter = filter;
	}

	public BaseDimension getDimension() {
		return dimension;
	}

	public void setDimension(BaseDimension dimension) {
		this.dimension = dimension;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<CommonStep> getSteps() {
		return steps;
	}

	public void setSteps(List<CommonStep> steps) {
		this.steps = steps;
	}

	public PeriodGranularity getSlidingWindow() {
		return slidingWindow;
	}

	public void setSlidingWindow(PeriodGranularity slidingWindow) {
		this.slidingWindow = slidingWindow;
	}

	public void addStep(CommonStep step){
		if(null == this.steps){
			this.steps = new ArrayList<>();
		}
		this.steps.add(step);
	}
}
