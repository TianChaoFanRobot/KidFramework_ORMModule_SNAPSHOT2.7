package com.tcf.kid.smart.framework.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tcf.kid.smart.framework.config.Configuration;
import com.tcf.kid.smart.framework.entity.UserInfo;

/***
 * TODO TCF ����JDBC��װ��SQLִ������ʵ��SQLִ�����ӿ�
 * @author 71485
 *
 */
public class JDBCExecutor implements Executor{

	//TODO TCF KidORM ����������Ϣ
	private Configuration configuration=new Configuration();
	
	@Override
	public Object selectOne(String sql, String parameter) 
	{
		Object result=null;
		
		//TODO TCF JDBC���Ĳ���API
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		try
		{
			connection=configuration.loadDataSource("dataSource.xml");
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,parameter);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet!=null)
			{
				while(resultSet.next())
				{
					UserInfo userInfo=new UserInfo();
					userInfo.setId(resultSet.getString("ID"));
					userInfo.setName(resultSet.getString("Name"));
					userInfo.setSex(resultSet.getString("Sex"));
					userInfo.setAge(resultSet.getInt("Age"));
					
					result=userInfo;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//TODO TCF �ر����ӣ��ͷ���Դ
			try
			{
				if(resultSet!=null)
				{
					resultSet.close();
				}
				if(preparedStatement!=null)
				{
					preparedStatement.close();
				}
				if(connection!=null)
				{
					connection.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
