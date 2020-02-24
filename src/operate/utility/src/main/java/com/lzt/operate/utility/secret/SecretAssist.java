package com.lzt.operate.utility.secret;

import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.LocalDateTimeEx;
import com.lzt.operate.utility.extensions.StringEx;
import lombok.var;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luzhitao
 */
public class SecretAssist {
	private static final String KEY = "ikjuoperkiosj7p4c68s98eda1ioec5x";

	public static String encrypt(String source) throws Exception {
		if (StringAssist.isNullOrEmpty(source)) {
			throw new Exception("空字符串不允许加密");
		}

		String sourceMix = StringAssist.randomAlphanumeric(4) + source + StringAssist.randomAlphanumeric(4);
		StringEx result = new StringEx(DesAssist.encrypt(KEY, sourceMix));
		result = result.replace("=", "").replace("+", "-").replace("/", "@");

		return result.toString();
	}

	public static String encryptWithExpirationTime(String source, long minute) throws Exception {
		LocalDateTime time = LocalDateTime.now().plusMinutes(minute);
		return encryptWithExpirationTime(source, time);
	}

	private static String encryptWithExpirationTime(String source, LocalDateTime expirationTime) throws Exception {

		if (StringAssist.isNullOrEmpty(source)) {
			throw new Exception("空字符串不允许加密");
		}

		String sourceMix = StringAssist.randomAlphanumeric(4) + ConvertAssist.localDateTimeToString(expirationTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
																			 .substring(0, 19) + source + StringAssist
				.randomAlphanumeric(4);
		StringEx result = new StringEx(DesAssist.encrypt(KEY, sourceMix));
		result = result.replace("=", "").replace("+", "-").replace("/", "@");

		return result.toString();

	}

	public static SecretDecryptResult decrypt(String target) {
		SecretDecryptResult result = new SecretDecryptResult();

		if (!StringAssist.isNullOrEmpty(target)) {
			target = target.replace("%25", "%").replace("%40", "@");
			target = target.replace("-", "+").replace("@", "/");

			int residue = target.length() % 4;

			if (residue > 0) {
				int complement = 4 - residue;

				StringBuilder targetBuilder = new StringBuilder(target);

				for (int i = 0; i < complement; i++) {
					targetBuilder.append("=");
				}

				target = targetBuilder.toString();
			}

			String text = DesAssist.decrypt(KEY, target);

			text = new StringEx(text).substring(0, text.length() - 4).toString();
			text = text.substring(4);

			result.setSuccess(true);
			result.setDecryptResult(text);
			result.setExpired(false);
		}

		return result;
	}

	public static SecretDecryptResult decryptWithExpirationTime(String target) {
		SecretDecryptResult result = new SecretDecryptResult();

		if (!StringAssist.isNullOrEmpty(target)) {
			target = target.replace("%25", "%").replace("%40", "@");
			target = target.replace("-", "+").replace("@", "/");

			int residue = target.length() % 4;

			if (residue > 0) {
				int complement = 4 - residue;

				StringBuilder targetBuilder = new StringBuilder(target);

				for (int i = 0; i < complement; i++) {
					targetBuilder.append("=");
				}

				target = targetBuilder.toString();
			}

			String text = DesAssist.decrypt(KEY, target);
			text = new StringEx(text).substring(0, text.length() - 4).toString();
			text = new StringEx(text).substring(4).toString();

			String timeString = new StringEx(text).substring(0, 19).toString();

			var localDateTime = ConvertAssist.stringToLocalDateTime(timeString);

			var duration = new LocalDateTimeEx(localDateTime).duration(LocalDateTime.now());
			var expired = duration.toMillis() < 0;

			text = text.substring(19);

			result.setSuccess(true);
			result.setDecryptResult(text);
			result.setExpired(expired);
		}

		return result;
	}
}
