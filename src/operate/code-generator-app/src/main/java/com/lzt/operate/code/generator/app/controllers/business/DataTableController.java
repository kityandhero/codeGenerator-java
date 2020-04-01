package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.assists.ConnectionConfigAssist;
import com.lzt.operate.code.generator.app.assists.DatabaseAssist;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.pojos.DataTable;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.DataColumnService;
import com.lzt.operate.code.generator.dao.service.DataTableGeneratorConfigService;
import com.lzt.operate.code.generator.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.DataBaseGeneratorConfigServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import com.lzt.operate.utility.pojo.results.PageListResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/dataTable")
@Api(tags = {"数据库表管理"})
public class DataTableController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据库表管理/";

	private final ConnectionConfigAssist connectionConfigAssist;

	@Autowired
	public DataTableController(CustomJsonWebTokenConfig customJsonWebTokenConfig,
							   ConnectionConfigServiceImpl connectionConfigService,
							   DataBaseGeneratorConfigServiceImpl databaseGeneratorConfigService,
							   DataTableGeneratorConfigService dataTableGeneratorConfigService,
							   DataColumnService dataColumnService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigAssist = new ConnectionConfigAssist(
				connectionConfigService,
				databaseGeneratorConfigService,
				dataTableGeneratorConfigService,
				dataColumnService);
	}

	@ApiOperation(value = "数据库表列表", notes = "数据库表列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_NAME),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库表列表", description = "数据库表列表", tag = "c501156d-5ff4-4ebd-89f2-2a1692cef567", config = {"显示路径", "显示子权限"})
	public ResultListData pageList(@RequestBody Map<String, Object> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.pageDataEmpty();
		}

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String name = paramJson.getStringByKey(GlobalString.DATA_TABLE_NAME);

		Optional<ConnectionConfig> optional = this.connectionConfigAssist.getConnectionConfigService()
																		 .get(connectionConfigId);

		if (optional.isPresent()) {
			ConnectionConfig connectionConfig = optional.get();

			List<DataTable> listDataTable = DatabaseAssist.listDataTable(connectionConfig);

			if (!StringAssist.isNullOrEmpty(name)) {
				listDataTable = listDataTable.stream()
											 .filter(o -> o.getName().contains(name))
											 .collect(Collectors.toList());
			}

			Optional<DatabaseGeneratorConfig> optionalDatabaseGeneratorConfig = this.connectionConfigAssist.getDatabaseGeneratorConfigService()
																										   .findByConnectionConfigId(connectionConfigId);

			List<DataTableGeneratorConfig> dataTableGeneratorConfigList = new ArrayList<>();

			if (optionalDatabaseGeneratorConfig.isPresent()) {
				DatabaseGeneratorConfig databaseGeneratorConfig = optionalDatabaseGeneratorConfig.get();

				List<String> tableNameList = new ArrayList<>();

				listDataTable.forEach(o -> tableNameList.add(o.getName()));

				Specification<DataTableGeneratorConfig> specification = new Specification<DataTableGeneratorConfig>() {

					private static final long serialVersionUID = 5662977671980526652L;

					@Override
					public Predicate toPredicate(@NonNull Root<DataTableGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
						List<Predicate> list = new ArrayList<>();

						list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getConnectionConfigId)), connectionConfigId));

						list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getDatabaseGeneratorConfigId)), databaseGeneratorConfig
								.getId()));

						CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get(ReflectAssist.getFieldName(DataTableGeneratorConfig::getTableName)));

						tableNameList.forEach(in::value);

						list.add(in);

						Predicate[] p = new Predicate[list.size()];

						return criteriaBuilder.and(list.toArray(p));
					}
				};

				dataTableGeneratorConfigList = this.connectionConfigAssist.getDataTableGeneratorConfigService()
																		  .list(specification);
			}

			PageListResult<DataTable> pager = PageListResult.buildFromList(listDataTable, pageNo, pageSize);

			List<IGetter<DataTableGeneratorConfig>> dataTableGeneratorConfigGetterList = new ArrayList<>();

			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getConnectionConfigId);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getDatabaseGeneratorConfigId);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getTableName);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getDomainObjectName);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getMapperName);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getComment);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getChannel);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getChannelNote);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getStatus);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getStatusNote);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getCreateTime);
			dataTableGeneratorConfigGetterList.add(DataTableGeneratorConfig::getUpdateTime);

			List<DataTableGeneratorConfig> finalDataTableGeneratorConfigList = dataTableGeneratorConfigList;
			List<SerializableData> list = pager.getList()
											   .stream()
											   .map(o -> {
												   List<IGetter<DataTable>> getterList = new ArrayList<>();

												   getterList.add(DataTable::getName);

												   SerializableData data = SerializableData.toSerializableData(o, getterList);

												   Optional<DataTableGeneratorConfig> optionalDataTableGeneratorConfig = finalDataTableGeneratorConfigList
														   .stream()
														   .filter(one -> one.getTableName()
																			 .equals(o.getName()))
														   .findFirst();

												   DataTableGeneratorConfig dataTableGeneratorConfigTemp;
												   boolean initialized = false;

												   if (optionalDataTableGeneratorConfig.isPresent()) {
													   dataTableGeneratorConfigTemp = optionalDataTableGeneratorConfig.get();
													   initialized = true;
												   } else {
													   dataTableGeneratorConfigTemp = new DataTableGeneratorConfig();
												   }

												   SerializableData dataTableGeneratorConfigData = SerializableData.toSerializableData(dataTableGeneratorConfigTemp, dataTableGeneratorConfigGetterList);

												   data.append(StringAssist.toFirstLower(DataTableGeneratorConfig.class.getSimpleName()), dataTableGeneratorConfigData);

												   if (!initialized) {
													   try {
														   ExecutiveResult<DataTableGeneratorConfig> result = this.connectionConfigAssist
																   .initializeDataTableGeneratorConfig(connectionConfigId, o
																		   .getName());

														   if (result.getSuccess()) {
															   initialized = true;
														   }
													   } catch (Exception e) {
														   e.printStackTrace();
													   }
												   }

												   data.append("initialized", initialized ? 1 : 0);

												   return data;
											   })
											   .collect(Collectors.toList());

			int pageIndex = pager.getPageIndex();
			long totalPages = pager.getTotalSize();

			return this.pageData(list, pageIndex, pageSize, totalPages);
		}

		return this.pageDataEmpty();
	}

}
