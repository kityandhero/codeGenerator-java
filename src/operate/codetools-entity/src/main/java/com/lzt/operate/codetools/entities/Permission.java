package com.lzt.operate.codetools.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Data
@Entity
@Table(name = "permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseDomain {
	/**
	 * 名称
	 */
	@Column(length = 50)
	String name;

	/**
	 * 唯一标识符
	 */
	@Column(length = 32, unique = true)
	String guidTag;

	/**
	 * 路径
	 */
	@Column(length = 500)
	String relativePath;

	/**
	 * 扩展权限说明
	 */
	@Column(length = 500)
	String expand;
}
