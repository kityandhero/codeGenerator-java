package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.codetools.interfaces.IAccessWay;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 访问通道
 *
 * @author luzhitao
 */
@Entity
@Table(name = "access_way")
@EqualsAndHashCode(callSuper = true)
public class AccessWay extends BaseEntity implements IAccessWay {

	private static final long serialVersionUID = 7406460829949779042L;

	/**
	 * 名称
	 */
	@Column
	private
	String name;

	/**
	 * 名称
	 */
	@Column
	private
	String description;

	/**
	 * 标记
	 */
	@Column
	private
	String tag;

	/**
	 * 相对路径
	 */
	@Column(name = "relative_path")
	private
	String relativePath;

	/**
	 * 扩展子权限设置
	 */
	@Column
	private
	String expand;

	public AccessWay() {
		super();

		this.name = "";
		this.tag = "";
		this.relativePath = "";
		this.expand = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}
}
