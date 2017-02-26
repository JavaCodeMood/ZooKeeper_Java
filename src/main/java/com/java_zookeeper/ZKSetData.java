package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 在特定设置znode中设置数据
 * ZooKeeper类提供 setData 方法来修改指定znode中附加的数据。
 *  在main方法中，使用 ZooKeeperConnection 对象创建一个ZooKeeper对象 zk 。 
 *  然后，使用指定的路径，新数据和节点版本调用 zk 对象的 setData 方法。
 * @author dell
 *
 */
public class ZKSetData {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	
	//znode方法更新数据。类似于getData但没有观察者。
	public static void update(String path,byte[] data) throws KeeperException, InterruptedException{
		/*
		 * setData 方法的签名如下 :
			setData(String path, byte[] data, int version)
			path- Znode路径
			data - 要存储在指定znode路径中的数据。
			version- znode的当前版本。 每当数据更改时，ZooKeeper会更新znode的版本号。
		 */
		zk.setData(path, data, zk.exists(path, true).getVersion());
	}
	
	public static void main(String[] args) {
		String path = "/zookeeper";
		//数据更新成功
		byte[] data = "Success".getBytes();
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//更新znode数据到指定的路径
			update(path,data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
