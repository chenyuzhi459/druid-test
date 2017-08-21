package io.sugo.query.member.cachingLookup;

/**
 * Created by chenyuzhi on 17-8-18.
 */
public class RedisDataFetcher {
	private  final String type = "redis";
	private String hostAndPorts;
	private boolean clusterMode;
	private String password;
	private String groupId;

	public RedisDataFetcher() {
	}

	public RedisDataFetcher(String hostAndPorts, boolean clusterMode, String groupId) {
		this.hostAndPorts = hostAndPorts;
		this.clusterMode = clusterMode;
		this.groupId = groupId;
	}

	public RedisDataFetcher(String hostAndPorts, boolean clusterMode, String password, String groupId) {
		this.hostAndPorts = hostAndPorts;
		this.clusterMode = clusterMode;
		this.password = password;
		this.groupId = groupId;
	}

	public String getType() {
		return type;
	}

	public String getHostAndPorts() {
		return hostAndPorts;
	}

	public void setHostAndPorts(String hostAndPorts) {
		this.hostAndPorts = hostAndPorts;
	}

	public boolean isClusterMode() {
		return clusterMode;
	}

	public void setClusterMode(boolean clusterMode) {
		this.clusterMode = clusterMode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
