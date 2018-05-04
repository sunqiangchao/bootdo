package com.bootdo.common.annotation;
@FirstAnno("这是类注释")
public class Anno {
	@FieldAnno("成员变量注释")
	private String test = "";
	
	@MethodAnno("test方法注释")
	public String test(){
		return "hello world";
	}
	
	@MethodAnno("test2方法注释")
	public String test2(String name){
		return "hello world";
	}

}
