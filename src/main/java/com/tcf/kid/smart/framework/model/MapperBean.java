package com.tcf.kid.smart.framework.model;

import java.util.List;

/***
 * TODO TCF ��װӳ�����ӿ���Ϣ
 * @author 71485
 *
 */
public class MapperBean {

	//TODO TCF ӳ�����ӿ���
	private String interfaceName;
	
	//TODO TCF ӳ�����ӿ��ж�������г־û�����
	private List<Function> functions;
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
}
