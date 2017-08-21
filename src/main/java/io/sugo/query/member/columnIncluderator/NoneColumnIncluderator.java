package io.sugo.query.member.columnIncluderator;

import io.sugo.query.member.columnIncluderator.base.BaseColumnIncluderator;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class NoneColumnIncluderator extends BaseColumnIncluderator {
	private static final String COLUMN_INCLUDERATOR_TYPE = "none";

	public NoneColumnIncluderator() {
		super(COLUMN_INCLUDERATOR_TYPE);
	}
}
