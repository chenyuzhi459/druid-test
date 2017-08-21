package io.sugo.components.having;

import io.sugo.components.having.base.BaseHavingSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-8.
 */
public class NotHavingSpec extends BaseHavingSpec{
	private static final String HAVING_TYPE = "not";
	private BaseHavingSpec havingSpec;

	public NotHavingSpec() {
		super(HAVING_TYPE);
	}

	public NotHavingSpec(BaseHavingSpec havingSpec) {
		super(HAVING_TYPE);
		this.havingSpec = havingSpec;
	}

	public BaseHavingSpec getHavingSpec() {
		return havingSpec;
	}

	public void setHavingSpec(BaseHavingSpec havingSpec) {
		this.havingSpec = havingSpec;
	}

}
