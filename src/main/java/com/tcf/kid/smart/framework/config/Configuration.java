package com.tcf.kid.smart.framework.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tcf.kid.smart.framework.model.Function;
import com.tcf.kid.smart.framework.model.MapperBean;

/***
 * TODO TCF KidORM ����������Ϣ
 * @author 71485
 *
 */
public class Configuration {

	//TODO TCF ��ȡ�������
	public ClassLoader getClassLoader()
	{
		return Thread.currentThread().getContextClassLoader();
	}
	
	//TODO TCF ��������ԴXML�����ļ�����������Դ������Ϣ
	public Connection loadDataSource(String dataSourceFileName)
	{
		Connection connection=null;
		
		try
		{
			//TODO TCF ����DOM4J��������ԴXML�����ļ�
			InputStream inputStream=getClassLoader().getResourceAsStream(dataSourceFileName);
			
			if(inputStream!=null)
			{
				SAXReader reader=new SAXReader();
				Document document=reader.read(inputStream);
				Element rootElement=document.getRootElement();
				
				if(rootElement!=null)
				{
					if(StringUtils.isNotEmpty(rootElement.getName()) && rootElement.getName().equals("dataSource"))
					{
						//TODO TCF ����Դ������Ϣ
						//TODO TCF ���ݿ������ַ���
						String url="";
						
						//TODO TCF �û���
						String userName="";
						
						//TODO TCF ����
						String password="";
						
						for(Object node:rootElement.elements())
						{
							Element element=(Element)node;
							
							if(element!=null)
							{
								//TODO TCF �ӽڵ�name����ֵ
								String name=element.attributeValue("name");
								
								//TODO TCF �ӽڵ�������Ϣ
								String value=element.getText();
								
								if(StringUtils.isNotEmpty(name))
								{
									if(name.equals("url"))
									{
										//TODO TCF ���ݿ������ַ���
										url=value;
									}
									else if (name.equals("userName"))
									{
										//TODO TCF �û���
										userName=value;
									}
									else if (name.equals("password")) 
									{
										//TODO TCF ����
										password=value;
									}
								}
							}
						}
						
						if(StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password))
						{
							connection=DriverManager.getConnection(url,userName,password);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//TODO TCF ����ӳ���ļ�������ӳ�����ӿ�ģ��
	public MapperBean loadMapperFile(String mapperFileName)
	{
		MapperBean mapperBean=new MapperBean();
		
		try
		{
		    //TODO TCF ����DOM4J����XMLӳ���ļ�
			InputStream inputStream=getClassLoader().getResourceAsStream(mapperFileName);
			
			if(inputStream!=null)
			{
				SAXReader reader=new SAXReader();
				Document document=reader.read(inputStream);
				Element rootElement=document.getRootElement();
				
				if(rootElement!=null)
				{
					//TODO TCF �����ռ�(ӳ�����ӿ���)
					String namespace=rootElement.attributeValue("namespace");
					mapperBean.setInterfaceName(namespace);
					
					//TODO TCF ӳ�����ӿ��ж�������г־û�����
					List<Function> functions=new ArrayList<Function>();
					for(Object node:rootElement.elements())
					{
						Element element=(Element)node;
						
						if(element!=null)
						{
							//TODO TCF SQL����
							String sqlType=element.getName();
							
							//TODO TCF SQL
							String sql=element.getText();
							
							//TODO TCF ������
							String methodName=element.attributeValue("id");
							
							//TODO TCF ��������
							String parameterType=element.attributeValue("parameterType");
							
							//TODO TCF ����ֵ����
							String resultType=element.attributeValue("resultType");
							
							Function function=new Function();
							function.setSqlType(sqlType);
							function.setSql(sql);
							function.setMethodName(methodName);
							function.setParameterType(parameterType);
							function.setResultType(resultType);
							
							functions.add(function);
						}
					}
					
					//TODO TCF ӳ�����ӿ��ж�������г־û�����
					mapperBean.setFunctions(functions);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mapperBean;
	}
}
