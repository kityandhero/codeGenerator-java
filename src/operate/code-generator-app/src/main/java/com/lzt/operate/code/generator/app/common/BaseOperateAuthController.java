package com.lzt.operate.code.generator.app.common;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.app.util.CommandUtil;
import com.lzt.operate.code.generator.common.utils.CustomConstants;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.code.generator.entities.ErrorLog;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.BaseOperator;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;

import java.io.File;
import java.util.Optional;

/**
 * @author luzhitao
 */
public abstract class BaseOperateAuthController extends OperateBaseController {

	private final LoadingCache<String, Object> loadingCache;
	private final CustomJsonWebTokenConfig customJsonWebTokenConfig;

	protected BaseOperateAuthController(LoadingCache<String, Object> loadingCache, CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.loadingCache = loadingCache;
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
	}

	protected LoadingCache<String, Object> getLoadingCache() {
		Optional<LoadingCache<String, Object>> optional = Optional.ofNullable(this.loadingCache);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("LoadingCache<String, Object>获取失败");
	}

	protected CustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		Optional<CustomJsonWebTokenConfig> optional = Optional.ofNullable(this.customJsonWebTokenConfig);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("CustomJsonWebTokenConfig获取失败");
	}

	protected Optional<BaseOperator> getOperator() {
		ExecutiveResult<CustomJsonWebToken> resultCustomJsonWebToken = CustomJsonWebToken.getFromCurrentHttpToken(this.getCustomJsonWebTokenConfig());

		if (resultCustomJsonWebToken.getSuccess()) {
			CustomJsonWebToken customJsonWebToken = resultCustomJsonWebToken.getData();

			return customJsonWebToken.getOperator();
		}

		return Optional.empty();
	}

	protected long getOperatorId() {
		Optional<BaseOperator> optional = getOperator();

		return optional.map(BaseOperator::getOperatorId).orElse(0L);
	}

	protected ExecutiveResult<String> createGenerateResultFolder(String folderName) {
		if (!StringAssist.isNullOrEmpty(folderName)) {
			try {
				String folder = StringAssist.merge(getDefaultMainGenerateFolderPath(), StringAssist.trim(folderName), "/");

				File file = new File(folder);

				if (!file.exists()) {
					boolean mkdirsResult = file.mkdirs();

					if (!mkdirsResult) {
						ErrorLog errorLog = new ErrorLog();

						errorLog.setMessage(StringAssist.merge("创建生成文件夹失败，默认文件将路径为：", file.getAbsolutePath()));

						ErrorLogProducerFactory.getInstance().getProducer().push(errorLog);

						return new ExecutiveResult<>(ReturnDataCode.Exception, "");
					}

					return new ExecutiveResult<>(ReturnDataCode.Ok, file.getAbsolutePath());
				}
			} catch (Exception e) {
				ErrorLogProducerFactory.getInstance()
									   .getProducer()
									   .pushException(e, StringAssist.merge("创建生成文件夹失败,文件夹名称", folderName));

				return new ExecutiveResult<>(ReturnDataCode.Exception, e.getMessage());
			}
		}

		return new ExecutiveResult<>(ReturnDataCode.DataError, "文件夹名字无效");
	}

	protected String getDefaultMainGenerateFolderPath() {
		return StringAssist.merge(CommandUtil.getUserHomePath(), CustomConstants.DEFAULT_MAIN_GENERATE_FOLDER);
	}

	protected boolean checkDefaultMainGenerateFolderPathEnable() {
		String defaultMainGenerateFolderPath = StringAssist.merge(CommandUtil.getUserHomePath(), CustomConstants.DEFAULT_MAIN_GENERATE_FOLDER);

		File file = new File(defaultMainGenerateFolderPath);

		return file.exists() && file.isDirectory();
	}

}
