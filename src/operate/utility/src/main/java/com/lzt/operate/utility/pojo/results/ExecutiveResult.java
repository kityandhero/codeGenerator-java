package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ReturnMessage;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author lzt
 */
@EqualsAndHashCode(callSuper = true)
public class ExecutiveResult<T> extends ExecutiveSimpleResult {

	/**
	 * getOptionalData
	 */
	private T data;

	public Optional<T> getOptionalData() {
		return Optional.of(data);
	}

	/**
	 * hasData
	 *
	 * @return boolean
	 */
	public boolean hasData() {
		return Optional.ofNullable(data).isPresent();
	}

	/**
	 * GetData
	 *
	 * @return T
	 */
	public T getData() {
		return Optional.of(data).orElse(null);
	}

	public ExecutiveResult(@NonNull ReturnMessage returnDataCode) {
		super(returnDataCode);

		data = null;
	}

	public ExecutiveResult(T resultData) {
		super();

		data = resultData;
	}

	public ExecutiveResult(ReturnDataCode returnDataCode, T resultData) {
		super(returnDataCode.toMessage());

		data = resultData;
	}

	public ExecutiveResult(ReturnMessage returnDataCode, T resultData) {
		super(returnDataCode);

		data = resultData;
	}

	public ExecutiveResult(ReturnDataCode returnDataCode, String customMessage, T resultData) {
		super(returnDataCode.toMessage(), customMessage);

		data = resultData;
	}

	public ExecutiveResult(@NonNull ReturnMessage returnDataCode, String customMessage, T resultData) {
		super(returnDataCode, customMessage);

		data = resultData;
	}

	public ExecutiveSimpleResult transferToExecutiveSimpleResult() {
		return new ExecutiveSimpleResult(this.getCode());
	}

}
