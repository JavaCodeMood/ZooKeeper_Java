package com.java_zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * ����znode�ڵ�
 * 
 * �����ļ� ZKCreate.java ��
 *  ��main�����У�����һ������Ϊ ZooKeeperConnection �Ķ���
 *  ������ connect �������ӵ�ZooKeeper���ϡ�
connect����������ZooKeeper���� zk �� 
���ڣ���ʹ���Զ���·�������ݵ��� zk ����� create ������
 * @author dell
 *
 */
public class ZKCreate {
	//����ڵ�zk
	private static ZooKeeper zk;
	//������̬ʵ��
	private static ZooKeeperConnection conn;
	/*
	 ZooKeeper���ṩ����������ZooKeeper�����д���һ���µ�znode�� 
	 create ������ǩ������ -
     create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
            ·�� - Znode·���� ���磬/ myapp1��/ myapp2��/ myapp1 / mydata1��myapp2 / mydata1 / myanothersubdata
     data - Ҫ�洢��ָ��znode·���е�����
     acl - Ҫ�����Ľڵ�ķ��ʿ����б� ZooKeeper API�ṩ��һ����̬�ӿ� ZooDefs.Ids ����ȡһЩ������acl�б� ���磬ZooDefs.Ids.OPEN_ACL_UNSAFE���ش�znode��acl�б�
     createMode - �ڵ�����ͣ����ݣ�˳������ߡ� ����һ��ö�١�
	 */
	//����znode�ڵ�
	public static void create(String path,byte[] data) throws KeeperException, InterruptedException{
		zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	} 
	
	public static void main(String[] args) {
		//znode�ڵ�·��
		String path="/zookeeper";   //ָ��·��znode
		//data - Ҫ�洢��ָ��znode·���е�����
		byte[] data = "My first zookeeper app".getBytes();
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			create(path,data);  //�������ݵ�ָ����·��
			conn.close();  //�ر�����
		} catch (Exception e) {
			//���������Ϣ
			System.out.println(e.getMessage());
		}
	}

}
