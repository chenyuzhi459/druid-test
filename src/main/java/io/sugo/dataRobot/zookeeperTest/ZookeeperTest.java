package io.sugo.dataRobot.zookeeperTest;


import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperTest implements Watcher{
	private static final int SESSION_TIMEOUT=5000;

	private ZooKeeper zk;
	private CountDownLatch connectedSignal=new CountDownLatch(1);
	@Override
	public void process(WatchedEvent event) {
		if(event.getState()==KeeperState.SyncConnected){
			connectedSignal.countDown();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZookeeperTest createGroup = new ZookeeperTest();
		createGroup.connect("localhost:2181");
		createGroup.create("zoo2");
		createGroup.close();
	}

	private void close() throws InterruptedException {
		zk.close();
	}

	private void create(String groupName) throws KeeperException, InterruptedException {
		String path="/"+groupName;
		if(zk.exists(path, false)== null){
			zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		System.out.println("Created:"+path);
	}

	private void connect(String hosts) throws IOException, InterruptedException {
		zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
		connectedSignal.await();
	}

	public void println(Object o){
		System.out.println(o);
	}
}