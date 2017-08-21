package io.sugo.query.member.step;

/**
 * Created by chenyuzhi on 17-8-18.
 * 此类主要用于支持FunnelQuery和RetentionQuery的步骤设置
 */
public class CommonStep {
	private String name;
	private String filter;

	public CommonStep() {
	}

	public CommonStep(String name, String filter) {
		this.name = name;
		this.filter = filter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
}
