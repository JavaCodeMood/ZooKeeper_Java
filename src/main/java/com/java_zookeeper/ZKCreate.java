package com.java_zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 创建znode节点
 * 
 * 创建文件 ZKCreate.java 。
 *  在main方法中，创建一个类型为 ZooKeeperConnection 的对象，
 *  并调用 connect 方法连接到ZooKeeper集合。
connect方法将返回ZooKeeper对象 zk 。 
现在，请使用自定义路径和数据调用 zk 对象的 create 方法。
 * @author dell
 *
 */
public class ZKCreate {
	//定义节点zk
	private static ZooKeeper zk;
	//创建静态实例
	private static ZooKeeperConnection conn;
	/*
	 ZooKeeper类提供创建方法在ZooKeeper集合中创建一个新的znode。 
	 create 方法的签名如下 -
     create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
            路径 - Znode路径。 例如，/ myapp1，/ myapp2，/ myapp1 / mydata1，myapp2 / mydata1 / myanothersubdata
     data - 要存储在指定znode路径中的数据
     acl - 要创建的节点的访问控制列表。 ZooKeeper API提供了一个静态接口 ZooDefs.Ids 来获取一些基本的acl列表。 例如，ZooDefs.Ids.OPEN_ACL_UNSAFE返回打开znode的acl列表。
     createMode - 节点的类型，短暂，顺序或两者。 这是一个枚举。
	 */
	//创建znode节点
	public static void create(String path,byte[] data) throws KeeperException, InterruptedException{
		zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	} 
	
	public static void main(String[] args) {
		//znode节点路径
		String path="/zookeeper";   //指定路径znode
		//data - 要存储在指定znode路径中的数据
		byte[] data = "My first zookeeper app".getBytes();
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			create(path,data);  //创建数据到指定的路径
			conn.close();  //关闭连接
		} catch (Exception e) {
			//捕获错误消息
			System.out.println(e.getMessage());
		}
	}

}
