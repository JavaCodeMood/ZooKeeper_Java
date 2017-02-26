package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * ���ض�����znode����������
 * ZooKeeper���ṩ setData �������޸�ָ��znode�и��ӵ����ݡ�
 *  ��main�����У�ʹ�� ZooKeeperConnection ���󴴽�һ��ZooKeeper���� zk �� 
 *  Ȼ��ʹ��ָ����·���������ݺͽڵ�汾���� zk ����� setData ������
 * @author dell
 *
 */
public class ZKSetData {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	
	//znode�����������ݡ�������getData��û�й۲��ߡ�
	public static void update(String path,byte[] data) throws KeeperException, InterruptedException{
		/*
		 * setData ������ǩ������ :
			setData(String path, byte[] data, int version)
			path- Znode·��
			data - Ҫ�洢��ָ��znode·���е����ݡ�
			version- znode�ĵ�ǰ�汾�� ÿ�����ݸ���ʱ��ZooKeeper�����znode�İ汾�š�
		 */
		zk.setData(path, data, zk.exists(path, true).getVersion());
	}
	
	public static void main(String[] args) {
		String path = "/zookeeper";
		//���ݸ��³ɹ�
		byte[] data = "Success".getBytes();
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//����znode���ݵ�ָ����·��
			update(path,data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
