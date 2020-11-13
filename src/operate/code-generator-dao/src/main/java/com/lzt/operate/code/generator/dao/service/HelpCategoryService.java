package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.common.pojos.HelpCategoryTreeItem;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.dao.repositories.HelpCategoryRepository;
import com.lzt.operate.code.generator.entities.HelpCategory;

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
