package io.sugo.query.member.pagingSpec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyuzhi on 17-8-11.
 */
public class PagingSpec {
	private Map<String,Integer> pagingIdentifiers;
	private int threshold;
	private boolean fromNext;

	public PagingSpec(int threshold) {
		this.threshold = threshold;
	}

	public PagingSpec(int threshold, boolean fromNext) {
		this.threshold = threshold;
		this.fromNext = fromNext;
	}

	public PagingSpec(int threshold, boolean fromNext,Map<String, Integer> pagingIdentifiers) {
		this.threshold = threshold;
		this.fromNext = fromNext;
		this.pagingIdentifiers = pagingIdentifiers;
	}

	public Map<String, Integer> getPagingIdentifiers() {
		return pagingIdentifiers;
	}

	public void setPagingIdentifiers(Map<String, Integer> pagingIdentifiers) {
		this.pagingIdentifiers = pagingIdentifiers;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public boolean isFromNext() {
		return fromNext;
	}

	public void setFromNext(boolean fromNext) {
		this.fromNext = fromNext;
	}

	public void putPagingIdentifier(String name, int offset){
		if(null == this.pagingIdentifiers){
			this.pagingIdentifiers = new HashMap<>();
		}
		this.pagingIdentifiers.put(name,offset);
	}
}
