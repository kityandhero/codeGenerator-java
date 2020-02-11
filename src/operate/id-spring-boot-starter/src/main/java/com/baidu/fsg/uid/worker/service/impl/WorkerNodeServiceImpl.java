package com.baidu.fsg.uid.worker.service.impl;

import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity;
import com.baidu.fsg.uid.worker.repository.WorkerNodeRepository;
import com.baidu.fsg.uid.worker.service.WorkerNodeService;
import com.lzt.operate.utility.ReflectAssist;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 */
@Service
public class WorkerNodeServiceImpl implements WorkerNodeService {
	private final WorkerNodeRepository repository;

	@Autowired
	public WorkerNodeServiceImpl(WorkerNodeRepository repository) {
		this.repository = repository;
	}

	@Override
	public WorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port) {
		Specification<WorkerNodeEntity> spec = new Specification<WorkerNodeEntity>() {
			private static final long serialVersionUID = -2260955832137429306L;

			@Override
			public Predicate toPredicate(Root<WorkerNodeEntity> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();
//               1.等于   选择name是“张三”的用户  Predicate equal(Expression<?> var1, Object var2);
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(WorkerNodeEntity::getHostName)), host));
				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(WorkerNodeEntity::getPort)), port));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		var op = repository.findOne(spec);

		return op.orElse(null);
	}

	@Override
	public void addWorkerNode(WorkerNodeEntity workerNodeEntity) {

	}
}
