package io.sugo.query.member.columnIncluderator;

import io.sugo.query.member.columnIncluderator.base.BaseColumnIncluderator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class ListColumnIncluderator extends BaseColumnIncluderator {
	private static final String COLUMN_INCLUDERATOR_TYPE = "list";
	private List<String> columns;




	public ListColumnIncluderator() {
		super(COLUMN_INCLUDERATOR_TYPE);
	}

	public ListColumnIncluderator(List<String> columns) {
		super(COLUMN_INCLUDERATOR_TYPE);
		this.columns = columns;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public void addColumn(String column){
		if(null == this.columns){
			this.columns = new ArrayList<>();
		}
		this.columns.add(column);
	}
}
