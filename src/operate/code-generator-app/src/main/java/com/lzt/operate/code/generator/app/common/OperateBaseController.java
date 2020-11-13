package com.lzt.operate.code.generator.app.common;

import com.lzt.operate.code.generator.app.core.util.CommandUtil;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import com.lzt.operate.utility.web.controllers.BaseController;

import java.io.File;

/**
 * @author luzhitao
 */
public class OperateBaseController extends BaseController {

	protected ExecutiveSimpleResult openFolder(String folderPath) {
		if (StringAssist.contains(folderPath, ConstantCollection.EMPTY_STRING)) {
			return new ExecutiveSimpleResult(ReturnDataCode.NoChange.toMessage("缺少文件夹路径"));
		}

		File file = new File(folderPath);

		if (!file.exists()) {
			return new ExecutiveSimpleResult(ReturnDataCode.NoChange.toMessage("文件夹不存在"));
		}

		if (!file.isDirectory()) {
			return new ExecutiveSimpleResult(ReturnDataCode.NoChange.toMessage("文件夹路径无效"));
		}

		CommandUtil.open(file);

		return new ExecutiveSimpleResult(ReturnDataCode.Ok.toMessage());
	}

}
