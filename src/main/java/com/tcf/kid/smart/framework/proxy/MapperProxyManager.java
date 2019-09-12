package com.tcf.kid.smart.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tcf.kid.smart.framework.config.Configuration;
import com.tcf.kid.smart.framework.core.SqlSession;
import com.tcf.kid.smart.framework.model.Function;
import com.tcf.kid.smart.framework.model.MapperBean;

/***
 * TODO TCF ӳ�����ӿڶ�̬������
 * TODO TCF ����ע��KidORM ����������Ϣ��SQL�Ự
 * TODO TCF ��������������ڷ������SQL�Ự�е�SQLִ�в���ʵ�����ݳ־û�����
 * @author 71485
 *
 */
public class MapperProxyManager implements InvocationHandler{

	//TODO TCF KidORM ����������Ϣ
	private Configuration configuration;
	
	//TODO TCF SQL�Ự
	private SqlSession sqlSession;
	
	//TODO TCF ����ע��
	public MapperProxyManager(Configuration configuration,SqlSession sqlSession)
	{
		this.configuration=configuration;
		this.sqlSession=sqlSession;
	}
	
	//TODO TCF �����������ڷ������SQL�Ự�е�SQLִ�в���
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		Object invokeResult=null;
		
		try
		{
			//TODO TCF ����ӳ���ļ�������ӳ�����ӿ�ģ��
			MapperBean mapperBean=configuration.loadMapperFile("UserInfoMapper.xml");
			
			if(mapperBean!=null)
			{
				//TODO TCF ӳ�����ӿ���
				String interfaceName=mapperBean.getInterfaceName();
				
				//TODO TCF ӳ�����ӿ�������͵�ǰ����Ŀ�귽������ӳ�����ӿ�����ͬ
				if(StringUtils.isNotEmpty(interfaceName) && interfaceName.equals(method.getDeclaringClass().getName()))
				{
					//TODO TCF ӳ�����ӿ��ж�������г־û�����
					List<Function> functions=mapperBean.getFunctions();
					
					if(functions!=null && functions.size()>0)
					{
						for(Function function:functions)
						{
							//TODO TCF ��ǰ���صķ���������ʹ���Ŀ�귽������ͬ
							if(StringUtils.isNotEmpty(function.getMethodName()) && function.getMethodName().equals(method.getName()))
							{
								//TODO TCF ���ڷ������SQL�Ự�е�SQLִ�в���ʵ�����ݳ־û�����
								invokeResult=sqlSession.selectOne(function.getSql(),args[0]!=null?args[0].toString():"");
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return invokeResult;
	}
}
