package io.sugo.query.member.limitSpec;

import io.sugo.query.member.limitSpec.orderByColumnSpec.OrderByColumnSpec;
import io.sugo.query.member.limitSpec.base.BaseLimitSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyuzhi on 17-8-7.
 */
public class DefaultLimitSpec extends BaseLimitSpec{
	private final String type = "default";
	private List<OrderByColumnSpec> columns;
	private Integer limit;

	public DefaultLimitSpec() {
	}

	public DefaultLimitSpec(Integer limit) {
		this.limit = limit;
	}

	public DefaultLimitSpec(Integer limit, List<OrderByColumnSpec> columns) {
		this.limit = limit;
		this.columns = columns;
	}

	public String getType() {
		return type;
	}

	public List<OrderByColumnSpec> getColumns() {
		return columns;
	}

	public void setColumns(List<OrderByColumnSpec> columns) {
		this.columns = columns;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void addOrderByColumnSpec(OrderByColumnSpec orderByColumnSpec){
		if(null == this.columns){
			columns = new ArrayList<>();
		}
		this.columns.add(orderByColumnSpec);
	}
}
