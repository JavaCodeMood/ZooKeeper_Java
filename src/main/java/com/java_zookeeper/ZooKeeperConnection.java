package com.java_zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * ����һ���µİ����� ZooKeeperConnection �������һ������ connect �� 
 * connect ��������һ��ZooKeeper�������ӵ�ZooKeeper���ϣ�Ȼ�󷵻ض���
���� CountDownLatch ����ֹͣ(�ȴ�)�����̣�ֱ���ͻ�����ZooKeeper�������ӡ�
 * @author dell
 *
 */
public class ZooKeeperConnection {
	private ZooKeeper  zoo;
	final CountDownLatch connectedSignal = new CountDownLatch(1);
	 
	/*
	ZooKeeper��ͨ���乹�캯���ṩ���ӹ��ܡ� ���캯����ǩ������ :
	ZooKeeper(String connectionString, int sessionTimeout, Watcher watcher)
	connectionString - ZooKeeper����������
	sessionTimeout - �Ự��ʱ(�Ժ���Ϊ��λ)��
	watcher - ʵ�֡��۲���"����Ķ��� ZooKeeper����ͨ���۲��߶��󷵻�����״̬��"
	*/		
	//��������
	public ZooKeeper connect(String host) throws IOException,InterruptedException{
		//����ZooKeeper����
		zoo = new ZooKeeper(host,50000,new Watcher(){
			//ʵ��Watch�ӿ�
			public void process(WatchedEvent we){
				if(we.getState() == KeeperState.SyncConnected){
					connectedSignal.countDown();
				}
			}
		});
		connectedSignal.await();
		return zoo;
	}
	
	//�ر�����
	public void close() throws InterruptedException{
		zoo.close();
	}

}
