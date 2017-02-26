package com.java_zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 从node节点获取数据 ZooKeeper类提供 getData 方法来获取指定znode中附加的数据及其状态。
 * 
 * @author dell
 *
 */
public class ZKGetData {
	// 定义节点名zk
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	// 判断node节点是否存在
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}

	public static void main(String[] args) {
		// 指定znode的路径
		String path = "/zookeeper";
		// CountDownLatch 用于停止(等待)主进程，直到客户端与ZooKeeper集合连接。
		final CountDownLatch connectedSignal = new CountDownLatch(1);

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//统计检查node的路径
			Stat stat = znode_exists(path);

			if (stat != null) {
				/*
				 * getData 方法的签名如下 -
					getData(String path, Watcher watcher, Stat stat)
					path - Znode路径。
					watcher - 类型观察者的回调函数。 当指定的znode的数据改变时，ZooKeeper集合将通过Watcher回调通知。 这是一次性通知。
					stat - 返回znode的元数据。
				 * */
				byte[] b = zk.getData(path, new Watcher() {

					public void process(WatchedEvent we) {

						if (we.getType() == Event.EventType.None) {
							switch (we.getState()) {
							case Expired:
								connectedSignal.countDown();
								break;
							}

						} else {
							String path = "/zookeeper";

							try {
								byte[] bn = zk.getData(path, false, null);
								String data = new String(bn, "UTF-8");
								System.out.println(data);
								connectedSignal.countDown();

							} catch (Exception ex) {
								System.out.println(ex.getMessage());
							}
						}
					}
				}, null);

				String data = new String(b, "UTF-8");
				System.out.println(data);
				connectedSignal.await();

			} else {
				System.out.println("Node节点不存在！！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
