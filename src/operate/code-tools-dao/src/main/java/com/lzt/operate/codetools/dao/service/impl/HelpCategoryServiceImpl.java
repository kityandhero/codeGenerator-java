package com.lzt.operate.codetools.dao.service.impl;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.common.pojos.HelpCategoryTreeItem;
import com.lzt.operate.codetools.dao.repositories.HelpCategoryRepository;
import com.lzt.operate.codetools.dao.service.HelpCategoryService;
import com.lzt.operate.codetools.entities.HelpCategory;
import com.lzt.operate.utility.general.ConstantCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
@Service
public class HelpCategoryServiceImpl implements HelpCategoryService {

	private final UidGenService uidGenService;

	private final HelpCategoryRepository repository;

	@Autowired
	public HelpCategoryServiceImpl(UidGenService uidGenService, HelpCategoryRepository repository) {
		this.uidGenService = uidGenService;
		this.repository = repository;
	}

	@Override
	public UidGenService getUidGenService() {
		if (Optional.ofNullable(this.uidGenService).isPresent()) {
			return this.uidGenService;
		}

		throw new RuntimeException("UidGenService不存在");
	}

	@Override
	public HelpCategoryRepository getRepository() {
		if (Optional.ofNullable(this.repository).isPresent()) {
			return this.repository;
		}

		throw new RuntimeException("Repository不存在");
	}

	public List<HelpCategoryTreeItem> buildTreeList(List<HelpCategory> list) {
		List<HelpCategoryTreeItem> listRoot = new ArrayList<>();
		List<HelpCategoryTreeItem> listResult = new ArrayList<>();

		if (Optional.ofNullable(list).isPresent()) {
			List<HelpCategoryTreeItem> listSource = new ArrayList<>();

			for (HelpCategory helpCategory : list) {
				HelpCategoryTreeItem item = new HelpCategoryTreeItem();

				item.setHelpCategoryId(helpCategory.getId());
				item.setName(helpCategory.getName());
				item.setParentId(helpCategory.getParentId());
				item.setDescription(helpCategory.getDescription());

				listSource.add(item);
			}

			for (HelpCategoryTreeItem item : listSource) {
				if (ConstantCollection.ZERO_LONG.equals(item.getParentId())) {
					listRoot.add(item);
				}
			}

			for (HelpCategoryTreeItem item : listRoot) {
				item = buildTreeListItem(item, listSource);

				listResult.add(item);
			}
		}

		return listResult;
	}

	public HelpCategoryTreeItem buildTreeListItem(HelpCategoryTreeItem parentItem, List<HelpCategoryTreeItem> list) {
		List<HelpCategoryTreeItem> listChildren = new ArrayList<>();

		if (Optional.ofNullable(list).isPresent()) {
			Long helpCategoryId = parentItem.getHelpCategoryId();

			for (HelpCategoryTreeItem item : list) {
				if (helpCategoryId.equals(item.getParentId())) {

					item = buildTreeListItem(item, list);

					listChildren.add(item);
				}
			}
		}

		parentItem.setChildren(listChildren);

		return parentItem;
	}

	public List<HelpCategoryTreeItem> buildTreeListChildren(List<HelpCategoryTreeItem> list, Long parentId) {
		List<HelpCategoryTreeItem> result = new ArrayList<>();

		if (Optional.ofNullable(list).isPresent()) {
			for (HelpCategoryTreeItem item : list) {
				if (parentId.equals(item.getParentId())) {
					result.add(item);
				}
			}
		}

		return result;
	}

	@Override
	public List<HelpCategoryTreeItem> treeList() {
		List<HelpCategory> list = list();

		return buildTreeList(list);
	}

}
