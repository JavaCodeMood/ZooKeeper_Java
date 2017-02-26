package com.java_zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ��ȡznode���ӽڵ�
 * ZooKeeper���ṩ getChildren ��������ȡ�ض�znode�������ӽڵ�
 * @author dell
 *
 */
public class ZKGetChildren {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;
	//�ж�zk�ڵ��Ƿ����
	public static Stat znode_exists(String path) throws KeeperException, InterruptedException{
		return zk.exists(path, true);
	}
	
	public static void main(String[] args) {
		//ָ��·��znode
		String path = "/zookeeper";
		
		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect("localhost");
			//ͳ�Ƽ���·��
			Stat stat = znode_exists(path);
			
			if(stat != null){
				/*
				 * getChildren ������ǩ������ -
					getChildren(String path, Watcher watcher)
					path - Znode·����
					watcher - ����Ϊ��Watcher"�Ļص������� 
					��ָ����znode��ɾ����znode�µ��ӽڵ㱻����/ɾ��ʱ��
					ZooKeeper���Ͻ�֪ͨ�� ����һ����֪ͨ��
				 */
				List<String> children = zk.getChildren(path, false);
				for(int i=0;i<children.size(); i++){
					System.out.println(children.get(i));
				}
			}else{
				System.out.println("Node�ڵ㲻���ڣ���");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
