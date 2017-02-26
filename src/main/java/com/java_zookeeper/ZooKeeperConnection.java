package com.java_zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * 创建一个新的帮助类 ZooKeeperConnection ，并添加一个方法 connect 。 
 * connect 方法创建一个ZooKeeper对象，连接到ZooKeeper集合，然后返回对象。
这里 CountDownLatch 用于停止(等待)主进程，直到客户端与ZooKeeper集合连接。
 * @author dell
 *
 */
public class ZooKeeperConnection {
	private ZooKeeper  zoo;
	final CountDownLatch connectedSignal = new CountDownLatch(1);
	 
	/*
	ZooKeeper类通过其构造函数提供连接功能。 构造函数的签名如下 :
	ZooKeeper(String connectionString, int sessionTimeout, Watcher watcher)
	connectionString - ZooKeeper集成主机。
	sessionTimeout - 会话超时(以毫秒为单位)。
	watcher - 实现“观察者"界面的对象。 ZooKeeper集合通过观察者对象返回连接状态。"
	*/		
	//建立连接
	public ZooKeeper connect(String host) throws IOException,InterruptedException{
		//创建ZooKeeper对象
		zoo = new ZooKeeper(host,50000,new Watcher(){
			//实现Watch接口
			public void process(WatchedEvent we){
				if(we.getState() == KeeperState.SyncConnected){
					connectedSignal.countDown();
				}
			}
		});
		connectedSignal.await();
		return zoo;
	}
	
	//关闭连接
	public void close() throws InterruptedException{
		zoo.close();
	}

}
