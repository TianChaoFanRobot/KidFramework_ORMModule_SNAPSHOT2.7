package com.tcf.kid.smart.framework.model;

import java.util.List;
import java.util.Map;

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

	// TODO TCF ����һ������
	private Map<String,Object> hookMap;
	
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

	public Map<String, Object> getHookMap() {
		return hookMap;
	}

	public void setHookMap(Map<String, Object> hookMap) {
		this.hookMap = hookMap;
	}
}
