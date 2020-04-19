package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.DataColumnStatus;
import com.lzt.operate.code.generator.dao.repositories.DataColumnRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.entities.DataColumn;
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
import java.util.Collection;
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
            dataColumn.setChannel(Channel.CodeGenerator);

            return dataColumn;
        }

        Optional<DataColumn> optional = this.get((id));

        if (optional.isPresent()) {
            DataColumn dataColumnConfig = optional.get();

            String aliasName = dataColumnConfig.getAliasName();

            dataColumnConfig.setColumnName(dataColumn.getColumnName());
            dataColumnConfig.setColumnType(dataColumn.getColumnType());

            if (StringAssist.isNullOrEmpty(aliasName)) {
                dataColumnConfig.setAliasName(dataColumn.getColumnName());
            }

            return dataColumnConfig;
        }

        dataColumn.setStatus(DataColumnStatus.NoCustom, DataColumnStatus::getFlag, DataColumnStatus::getName);
        dataColumn.setChannel(Channel.CodeGenerator);

        return dataColumn;
    }

    /**
     * @param connectionConfigId connectionConfigId
     * @param tableName          tableName
     * @return Optional<DataColumn>
     */
    default List<DataColumn> findByConnectionConfigIdAndTableName(long connectionConfigId, String tableName) {
        Specification<DataColumn> specification = new Specification<DataColumn>() {

            private static final long serialVersionUID = 6317428223679296973L;

            @Override
            public Predicate toPredicate(@NonNull Root<DataColumn> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getConnectionConfigId)), connectionConfigId));
                list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getTableName)), tableName));

                Predicate[] p = new Predicate[list.size()];

                return criteriaBuilder.and(list.toArray(p));
            }
        };

        return this.getRepository().findAll(specification);
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
                list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getColumnName)), name));

                Predicate[] p = new Predicate[list.size()];

                return criteriaBuilder.and(list.toArray(p));
            }
        };

        return this.getRepository().findOne(specification);
    }

    /**
     * @param connectionConfigId connectionConfigId
     * @param tableName          tableName
     * @param nameCollection     nameCollection
     * @return Optional<DataColumn>
     */
    default List<DataColumn> findByConnectionConfigIdAndTableNameAndNames(long connectionConfigId, String tableName, Collection<String> nameCollection) {
        Specification<DataColumn> specification = new Specification<DataColumn>() {

            private static final long serialVersionUID = 6317428223679296973L;

            @Override
            public Predicate toPredicate(@NonNull Root<DataColumn> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getConnectionConfigId)), connectionConfigId));
                list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataColumn::getTableName)), tableName));

                CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get(ReflectAssist.getFieldName(DataColumn::getColumnName)));

                for (String v : nameCollection) {
                    if (!StringAssist.isNullOrEmpty(v)) {
                        in.value(v);
                    }
                }

                list.add(in);

                Predicate[] p = new Predicate[list.size()];

                return criteriaBuilder.and(list.toArray(p));
            }
        };

        return this.getRepository().findAll(specification);
    }

}
