package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 删除节点
 * ZooKeeper类提供了 delete 方法来删除指定的znode。
 * @author dell
 *
 */
public class ZKDelete {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	
	//如果znode可用,检查znode及其存在状态
	public static void delete(String path) throws InterruptedException, KeeperException{
		/*
		 * 删除方法的签名如下 -
			delete(String path, int version)
			path - Znode路径。
			version - znode的当前版本。
		 */
		zk.delete(path, zk.exists(path, true).getVersion());
	}
	
	public static void main(String[] args) {
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			delete(path);//删除指定路径的节点
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
