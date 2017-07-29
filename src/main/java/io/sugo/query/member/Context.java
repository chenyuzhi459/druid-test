package io.sugo.query.member;

/**
 * Created by chenyuzhi on 17-7-28.
 */
public class Context {
	private int timeout;
	private boolean useOffheap;
	private String groupByStrategy;

	public Context(int timeout, boolean useOffheap, String groupByStrategy) {
		this.timeout = timeout;
		this.useOffheap = useOffheap;
		this.groupByStrategy = groupByStrategy;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean isUseOffheap() {
		return useOffheap;
	}

	public void setUseOffheap(boolean useOffheap) {
		this.useOffheap = useOffheap;
	}

	public String getGroupByStrategy() {
		return groupByStrategy;
	}

	public void setGroupByStrategy(String groupByStrategy) {
		this.groupByStrategy = groupByStrategy;
	}
}
