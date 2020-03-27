package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.common.pojos.HelpCategoryTreeItem;
import com.lzt.operate.codetools.dao.repositories.HelpCategoryRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.HelpCategory;

import java.util.List;

/**
 * @author luzhitao
 */
public interface HelpCategoryService extends BaseService<HelpCategoryRepository, HelpCategory> {

	/**
	 * 构建treeList
	 *
	 * @return List<HelpCategoryTreeItem>
	 */
	List<HelpCategoryTreeItem> treeList();

}
