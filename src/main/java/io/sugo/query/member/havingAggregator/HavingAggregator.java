package io.sugo.query.member.havingAggregator;

import io.sugo.components.aggregation.base.BaseAggregation;
import io.sugo.components.having.base.BaseHavingSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-15.
 */
public class HavingAggregator {
	private String name;
	private BaseHavingSpec havingSpec;
	private List<BaseAggregation> aggregatorSpecs;

	public HavingAggregator() {
	}

	public HavingAggregator(String name) {
		this.name = name;
	}

	public HavingAggregator(String name, BaseHavingSpec havingSpec) {
		this.name = name;
		this.havingSpec = havingSpec;
	}

	public HavingAggregator(String name, BaseHavingSpec havingSpec, List<BaseAggregation> aggregatorSpecs) {
		this.name = name;
		this.havingSpec = havingSpec;
		this.aggregatorSpecs = aggregatorSpecs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseHavingSpec getHavingSpec() {
		return havingSpec;
	}

	public void setHavingSpec(BaseHavingSpec havingSpec) {
		this.havingSpec = havingSpec;
	}

	public List<BaseAggregation> getAggregatorSpecs() {
		return aggregatorSpecs;
	}

	public void setAggregatorSpecs(List<BaseAggregation> aggregatorSpecs) {
		this.aggregatorSpecs = aggregatorSpecs;
	}

	public void addAggregator(BaseAggregation aggregation){
		if(null == this.aggregatorSpecs){
			this.aggregatorSpecs = new ArrayList<>();
		}
		this.aggregatorSpecs.add(aggregation);
	}
}
