package com.lzt.operate.code.generator.app.caches;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.common.enums.ErrorLogDataType;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducer;
import com.lzt.operate.code.generator.custommessagequeue.errorlog.ErrorLogProducerFactory;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.pojo.Competence;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class ListCompetenceCache {

	public static String getCacheKey(long accountId) {
		return StringAssist.merge("competences_", ConvertAssist.longToString(accountId));
	}

	public static void setCache(long accountId, List<Competence> competenceList, @NotNull LoadingCache<String, Object> loadingCache) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		try {
			loadingCache.put(key, ConvertAssist.serialize(competenceList));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Optional<List<Competence>> getCache(long accountId, @NotNull LoadingCache<String, Object> loadingCache) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		String json = Optional.ofNullable(loadingCache.get(key)).orElse("").toString();

		if (StringAssist.isNullOrEmpty(json)) {
			return Optional.empty();
		}

		List<Competence> competenceList;

		try {
			competenceList = ConvertAssist.deserializeToList(json, Competence.class);
		} catch (Exception ex) {
			ex.printStackTrace();

			ErrorLogProducer errorLogProducer = ErrorLogProducerFactory.getInstance().getProducer();

			errorLogProducer.pushException(ex, json, ErrorLogDataType.CommonValue);

			competenceList = null;
		}

		return Optional.ofNullable(competenceList);
	}

	public static void removeCache(long accountId, @NotNull LoadingCache<String, Object> loadingCache) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		loadingCache.invalidate(key);
	}

}
