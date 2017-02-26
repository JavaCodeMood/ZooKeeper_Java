package com.java_zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ���znode�ڵ��Ƿ����
 * ZooKeeper���ṩ�� exists���������znode�Ĵ��ڡ� 
 * ���ָ����znode���ڣ��򷵻�znode��Ԫ���ݡ�
 * @author dell
 *
 */
public class ZKExists {
	//����ڵ���zk
	private static ZooKeeper zk;
	//ʹ�á�ZooKeeperConnection"���󴴽�ZooKeeper����zk"
	private static ZooKeeperConnection conn;
	//�ж�znode�ڵ��Ƿ����
	/*
	 ���ڷ�����ǩ������ -
     exists(String path, boolean watcher)
     path- Znode·��
     watcher - ����ֵ������ָ���Ƿ�ۿ�ָ����znode
	 */
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zk.exists(path, true);
	}
	
	public static void main(String[] args) {
		//ָ��znode��·��
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//ͳ�Ƽ��znode��·��
			Stat stat = znode_exists(path);
			
			if(stat != null){
				System.out.println("node�ڵ���ڣ�����ڵ��version�ǣ�"+stat.getVersion());
			}else{
				System.out.println("node�ڵ㲻���ڣ�");
			}
		} catch (Exception e) {
			//���������Ϣ
			System.out.println(e.getMessage());
		}
	}

}
