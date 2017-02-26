package com.java_zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 获取znode的子节点
 * ZooKeeper类提供 getChildren 方法来获取特定znode的所有子节点
 * @author dell
 *
 */
public class ZKGetChildren {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	//判断zk节点是否存在
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zk.exists(path, true);
	}
	
	public static void main(String[] args) {
		//指定路径znode
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//统计检查的路径
			Stat stat = znode_exists(path);
			
			if(stat != null){
				/*
				 * getChildren 方法的签名如下 -
					getChildren(String path, Watcher watcher)
					path - Znode路径。
					watcher - 类型为“Watcher"的回调函数。 
					当指定的znode被删除或znode下的子节点被创建/删除时，
					ZooKeeper集合将通知。 这是一次性通知。
				 */
				List<String> children = zk.getChildren(path, false);
				for(int i=0;i<children.size(); i++){
					System.out.println(children.get(i));
				}
			}else{
				System.out.println("Node节点不存在！！");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
