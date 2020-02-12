package com.lzt.operate.codetools.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 访问通道
 *
 * @author luzhitao
 */
@Data
@Entity
@Table(name = "access_way")
@EqualsAndHashCode(callSuper = true)
public class AccessWay extends BaseEntity {

	private static final long serialVersionUID = 7406460829949779042L;

	/**
	 * 名称
	 */
	@Column
	String name;

	/**
	 * 标记
	 */
	@Column
	String tag;

	/**
	 * 相对路径
	 */
	@Column(name = "relative_path")
	String relativePath;

	/**
	 * 扩展子权限设置
	 */
	@Column
	String expand;
}
