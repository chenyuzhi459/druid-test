package io.sugo.components.having;

import io.sugo.components.having.base.BaseHavingSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-8.
 */
public class AndHavingSpec extends BaseHavingSpec{
	private static final String HAVING_TYPE = "and";
	private List<BaseHavingSpec> havingSpecs;

	public AndHavingSpec() {
		super(HAVING_TYPE);
	}

	public AndHavingSpec(List<BaseHavingSpec> havingSpecs) {
		super(HAVING_TYPE);
		this.havingSpecs = havingSpecs;
	}

	public List<BaseHavingSpec> getHavingSpecs() {
		return havingSpecs;
	}

	public void setHavingSpecs(List<BaseHavingSpec> havingSpecs) {
		this.havingSpecs = havingSpecs;
	}

	public void addHavingSpec(BaseHavingSpec havingSpec){
		if(null == havingSpecs){
			havingSpecs = new ArrayList<>();
		}
		this.havingSpecs.add(havingSpec);
	}
}
