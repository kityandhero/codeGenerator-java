package com.baidu.fsg.uid.worker.repository;

import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface WorkerNodeRepository extends JpaRepository<WorkerNodeEntity, Long>, JpaSpecificationExecutor<WorkerNodeEntity> {
}
