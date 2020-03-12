package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.DataColumnStatus;
import com.lzt.operate.codetools.dao.repositories.DataColumnRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.DataColumn;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public interface DataColumnService extends BaseService<DataColumnRepository, DataColumn> {

	/**
	 * fillFromDataColumnConfig
	 *
	 * @param dataColumn dataColumn
	 * @return DataColumn
	 */
	default DataColumn fillFromDataColumnConfig(@NotNull DataColumn dataColumn) {
		Long id = dataColumn.getId();

		if (id.equals(ConstantCollection.ZERO_LONG)) {
			dataColumn.setStatus(DataColumnStatus.NoCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
			dataColumn.setChannel(Channel.CodeTools);

			return dataColumn;
		}

		Optional<DataColumn> optional = get((id));

		if (optional.isPresent()) {
			DataColumn dataColumnConfig = optional.get();

			String aliasName = dataColumnConfig.getAliasName();

			dataColumnConfig.setName(dataColumn.getName());
			dataColumnConfig.setType(dataColumn.getType());

			if (StringAssist.isNullOrEmpty(aliasName)) {
				dataColumnConfig.setAliasName(dataColumn.getName());
			}

			return dataColumnConfig;
		}

		dataColumn.setStatus(DataColumnStatus.NoCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
		dataColumn.setChannel(Channel.CodeTools);

		return dataColumn;
	}

	/**
	 * @param connectionConfigId connectionConfigId
	 * @param tableName          tableName
	 * @param name               name
	 * @return Optional<DataColumn>
	 */
	default Optional<DataColumn> findByConnectionConfigIdAndTableNameAndName(long connectionConfigId, String tableName, String name) {
		Specification<DataColumn> specification = new Specification<DataColumn>() {

			private static final long serialVersionUID = 6317428223679296973L;

			@Override
			public Predicate toPredicate(@NonNull Root<DataColumn> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getConnectionConfigId)), connectionConfigId));
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getTableName)), tableName));
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getName)), name));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		return getRepository().findOne(specification);
	}

}
