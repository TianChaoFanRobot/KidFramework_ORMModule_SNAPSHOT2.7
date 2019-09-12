package com.tcf.kid.smart.framework.core;

import java.lang.reflect.Proxy;

import com.tcf.kid.smart.framework.config.Configuration;
import com.tcf.kid.smart.framework.proxy.MapperProxyManager;

/***
 * TODO TCF KidORM SQL�Ự
 * TODO TCF ����SQLִ�в��ԣ�����SQLִ�����е�SQLִ�в��ԣ�ʵ�����ݳ־û�����
 * TODO TCF ����ӳ�����ӿ����ʹ���ӳ�����ӿ�JDK��̬����ʵ�������ô�����
 * @author 71485
 *
 */
public class SqlSession {

	//TODO TCF KidORM ����������Ϣ
	private Configuration configuration=new Configuration();
	
	//TODO TCF SQLִ����
	private Executor executor=new JDBCExecutor();
	
	//TODO TCF ����SQLִ�в���
	//TODO TCF ...��������id��ѯΨһ���
	public Object selectOne(String sql,String parameter)
	{
		return executor.selectOne(sql,parameter);
	}
	
	//TODO TCF ����ӳ�����ӿ����ʹ���ָ��ӳ�����ӿڵ�JDK��̬����ʵ�������ô�����
	public Object getMapper(Class<?> mapperClass)
	{
		return Proxy.newProxyInstance(mapperClass.getClassLoader(), 
				                      new Class[] {mapperClass}, 
				                      new MapperProxyManager(configuration,this));
	}
}
