package com.tcf.kid.smart.framework.model;

import java.util.List;
import java.util.Map;

/***
 * TODO TCF 封装映射器接口信息
 * @author 71485
 *
 */
public class MapperBean {

	//TODO TCF 映射器接口名
	private String interfaceName;
	
	//TODO TCF 映射器接口中定义的所有持久化方法
	private List<Function> functions;

	// TODO TCF 这是一个钩子
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
