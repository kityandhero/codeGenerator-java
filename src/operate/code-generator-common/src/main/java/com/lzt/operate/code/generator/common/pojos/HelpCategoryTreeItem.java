package com.lzt.operate.code.generator.common.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 */
public class HelpCategoryTreeItem implements Serializable {

	private static final long serialVersionUID = 4632492095267520355L;

	private long helpCategoryId;

	private String name;

	private long parentId;

	private String description;

	private List<HelpCategoryTreeItem> children;

	public HelpCategoryTreeItem() {
		this.helpCategoryId = 0;
		this.name = "";
		this.parentId = 0;
		this.description = "";
		this.children = new ArrayList<>();
	}

	public long getHelpCategoryId() {
		return helpCategoryId;
	}

	public void setHelpCategoryId(long helpCategoryId) {
		this.helpCategoryId = helpCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<HelpCategoryTreeItem> getChildren() {
		return children;
	}

	public void setChildren(List<HelpCategoryTreeItem> children) {
		this.children = children;
	}
}
