package io.sugo.query.member.cachingLookup;

/**
 * Created by chenyuzhi on 17-8-18.
 */
public class CachingLookup {

	private  final String type = "cachingLookup";
	private String version;
	private RedisDataFetcher dataFetcher;

	public CachingLookup() {
	}

	public CachingLookup(RedisDataFetcher dataFetcher) {
		this.dataFetcher = dataFetcher;
	}

	public CachingLookup(String version, RedisDataFetcher dataFetcher) {
		this.version = version;
		this.dataFetcher = dataFetcher;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public RedisDataFetcher getDataFetcher() {
		return dataFetcher;
	}

	public void setDataFetcher(RedisDataFetcher dataFetcher) {
		this.dataFetcher = dataFetcher;
	}
}
