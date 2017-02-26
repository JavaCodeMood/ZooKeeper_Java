package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 检查znode节点是否存在
 * ZooKeeper类提供了 exists方法来检查znode的存在。 
 * 如果指定的znode存在，则返回znode的元数据。
 * @author dell
 *
 */
public class ZKExists {
	//定义节点名zk
	private static ZooKeeper zk;
	//使用“ZooKeeperConnection"对象创建ZooKeeper对象“zk"
	private static ZooKeeperConnection conn;
	//判断znode节点是否存在
	/*
	 存在方法的签名如下 -
     exists(String path, boolean watcher)
     path- Znode路径
     watcher - 布尔值，用于指定是否观看指定的znode
	 */
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zk.exists(path, true);
	}
	
	public static void main(String[] args) {
		//指定znode的路径
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//统计检查znode的路径
			Stat stat = znode_exists(path);
			
			if(stat != null){
				System.out.println("node节点存在，这个节点的version是："+stat.getVersion());
			}else{
				System.out.println("node节点不存在！");
			}
		} catch (Exception e) {
			//捕获错误消息
			System.out.println(e.getMessage());
		}
	}

}
