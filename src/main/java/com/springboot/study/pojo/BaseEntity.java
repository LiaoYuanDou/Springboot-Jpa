package com.springboot.study.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * @Fields field:field:{todo}序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 版本
	 */
	@Column(name = "version", columnDefinition = "int(11) default 1 comment '版本'")
	protected Integer version = 1;

	/**
	 * 创建日期和时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time", columnDefinition = "datetime default null comment '创建日期和时间'")
	protected Date createTime;

	/**
	 * 创建人
	 */
	@Column(name = "create_user", columnDefinition = "bigint(20) default 63 comment '创建人'")
	protected Long createUser;

	/**
	 * 更新日期和时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time", columnDefinition = "datetime default null comment '更新日期和时间'")
	protected Date updateTime;

	/**
	 * 更新人
	 */
	@Column(name = "update_user", columnDefinition = "bigint(20) default 63 comment '更新人'")
	protected Long updateUser;

}
