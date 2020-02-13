/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baidu.fsg.uid.worker.entity;

import com.baidu.fsg.uid.worker.WorkerNodeType;
import lombok.Data;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Data
@Entity
@Table(name = "worker_node")
public class WorkerNodeEntity {

	/**
	 * Entity unique id (table unique)
	 */
	@Id
	@Column(nullable = false)
	@GeneratedValue
	long id;

	/**
	 * Type of CONTAINER: HostName, ACTUAL : IP.
	 */
	String hostName;

	/**
	 * Type of CONTAINER: Port, ACTUAL : Timestamp + Random(0-10000)
	 */
	String port;

	/**
	 * type of {@link WorkerNodeType}
	 */
	int type;

	/**
	 * Worker launch date, default now
	 */
	Date launchDate = new Date();

	/**
	 * Created time
	 */
	Date created;

	/**
	 * Last modified
	 */
	Date modified;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
