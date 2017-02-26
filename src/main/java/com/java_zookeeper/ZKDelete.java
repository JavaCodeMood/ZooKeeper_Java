package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * ɾ���ڵ�
 * ZooKeeper���ṩ�� delete ������ɾ��ָ����znode��
 * @author dell
 *
 */
public class ZKDelete {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	
	//���znode����,���znode�������״̬
	public static void delete(String path) throws InterruptedException, KeeperException{
		/*
		 * ɾ��������ǩ������ -
			delete(String path, int version)
			path - Znode·����
			version - znode�ĵ�ǰ�汾��
		 */
		zk.delete(path, zk.exists(path, true).getVersion());
	}
	
	public static void main(String[] args) {
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			delete(path);//ɾ��ָ��·���Ľڵ�
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
