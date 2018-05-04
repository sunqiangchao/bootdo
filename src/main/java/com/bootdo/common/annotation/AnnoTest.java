package com.bootdo.common.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnoTest {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		
		Class<?> clazz = Class.forName("com.bootdo.common.annotation.Anno");
		
		boolean flag = clazz.isAnnotationPresent(MethodAnno.class);
		System.out.println("类上面是否存在FirstAnno注解："+flag);
		
		FirstAnno typeAnno = clazz.getAnnotation(FirstAnno.class);
		
		System.out.println("类注释值："+typeAnno.value());
		
		Method testM = clazz.getMethod("test");
		
		MethodAnno methodAnno = testM.getAnnotation(MethodAnno.class);
		System.out.println("方法test注释值："+methodAnno.value());
		
		
		Method test2M = clazz.getMethod("test2", String.class);
		
		MethodAnno methodAnno2 = test2M.getAnnotation(MethodAnno.class);
		System.out.println("方法test2注释值："+methodAnno2.value());
		
		Field field = clazz.getDeclaredField("test");
		
		boolean fieldFlag = field.isAnnotationPresent(FieldAnno.class);
		System.out.println("是否存在字段注释："+fieldFlag);
		
		FieldAnno fieldAnno  = field.getAnnotation(FieldAnno.class);
		
		System.out.println("字段注释："+fieldAnno.value());
	}
}
