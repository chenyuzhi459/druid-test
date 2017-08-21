package io.sugo.query.member.redisIOConfig;

/**
 * Created by chenyuzhi on 17-8-14.
 */
public class RedisIOConfig {
	private static final String type = "redis";
	private String hostAndPorts;
	private boolean clusterMode;
	private String groupId;

	public RedisIOConfig() {
	}

	public RedisIOConfig(String hostAndPorts, boolean clusterMode, String groupId) {
		this.hostAndPorts = hostAndPorts;
		this.clusterMode = clusterMode;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
