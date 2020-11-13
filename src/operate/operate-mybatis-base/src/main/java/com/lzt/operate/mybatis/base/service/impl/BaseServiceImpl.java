package com.lzt.operate.mybatis.base.service.impl;

import com.lzt.operate.mybatis.base.dao.BaseMapper;
import com.lzt.operate.mybatis.base.model.BaseExample;
import com.lzt.operate.mybatis.base.service.BaseService;
import com.lzt.operate.mybatis.common.PageInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public abstract class BaseServiceImpl<T, Example extends BaseExample, ID> implements BaseService<T, Example, ID> {

	private BaseMapper<T, Example, ID> mapper;

	public void setMapper(BaseMapper<T, Example, ID> mapper) {
		this.mapper = mapper;
	}

	@Override
	public long countByExample(Example example) {
		return this.mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(Example example) {
		return this.mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public List<T> selectByExample(Example example) {
		return this.mapper.selectByExample(example);
	}

	@Override
	public Optional<T> selectByCondition(Example example) {
		List<T> list = this.selectByExample(example);

		if (Optional.ofNullable(list).isPresent()) {
			if (list.size() >= 1) {
				return Optional.of(list.get(0));
			}
		}

		return Optional.empty();
	}

	@Override
	public List<T> selectByPageExample(Example example, PageInfo pageInfo) {

		if (pageInfo != null) {

			example.setPageInfo(pageInfo);
			pageInfo.setPageParams(Long.valueOf(this.countByExample(example)).intValue());
		}
		return this.selectByExample(example);
	}

	@Override
	public T selectByPrimaryKey(ID id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(T record, Example example) {
		return this.mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(T record, Example example) {
		return this.mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return this.mapper.updateByPrimaryKey(record);
	}
}