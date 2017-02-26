package com.java_zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ��node�ڵ��ȡ���� ZooKeeper���ṩ getData ��������ȡָ��znode�и��ӵ����ݼ���״̬��
 * 
 * @author dell
 *
 */
public class ZKGetData {
	// ����ڵ���zk
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	// �ж�node�ڵ��Ƿ����
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
		return zk.exists(path, true);
	}

	public static void main(String[] args) {
		// ָ��znode��·��
		String path = "/zookeeper";
		// CountDownLatch ����ֹͣ(�ȴ�)�����̣�ֱ���ͻ�����ZooKeeper�������ӡ�
		final CountDownLatch connectedSignal = new CountDownLatch(1);

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//ͳ�Ƽ��node��·��
			Stat stat = znode_exists(path);

			if (stat != null) {
				/*
				 * getData ������ǩ������ -
					getData(String path, Watcher watcher, Stat stat)
					path - Znode·����
					watcher - ���͹۲��ߵĻص������� ��ָ����znode�����ݸı�ʱ��ZooKeeper���Ͻ�ͨ��Watcher�ص�֪ͨ�� ����һ����֪ͨ��
					stat - ����znode��Ԫ���ݡ�
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
				System.out.println("Node�ڵ㲻���ڣ���");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
