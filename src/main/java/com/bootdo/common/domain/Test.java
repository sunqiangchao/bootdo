package com.bootdo.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 测试表
 * 
 * @author sunqc
 * @email 1992lcg@163.com
 * @date 2018-01-21 17:20:07
 */
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//姓名
	private String username;
	//年龄
	private Integer age;
	//性别
	private String sex;
	//地址
	private String address;
	//标识
	private String delFlag;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 设置：删除标记
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标记
	 */
	public String getDelFlag() {
		return delFlag;
	}

	@Override
	public String toString() {
		return "Test{" +
				"id=" + id +
				", username='" + username + '\'' +
				", age='" + age + '\'' +
				", sex='" + sex + '\'' +
				", address='" + address + '\'' +
				", delFlag='" + delFlag + '\'' +
				'}';
	}
}
