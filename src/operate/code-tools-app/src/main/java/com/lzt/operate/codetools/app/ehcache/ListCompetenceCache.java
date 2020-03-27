package com.lzt.operate.codetools.app.ehcache;

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

	public static void setCache(long accountId, List<Competence> competenceList, @NotNull CustomEhcacheManager customEhcacheManager) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		try {
			customEhcacheManager.put(key, ConvertAssist.serialize(competenceList));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Optional<List<Competence>> getCache(long accountId, @NotNull CustomEhcacheManager customEhcacheManager) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		String json = customEhcacheManager.getAsString(key);

		List<Competence> competenceList;

		try {
			competenceList = ConvertAssist.deserializeToList(json, Competence.class);
		} catch (Exception ex) {
			ex.printStackTrace();

			competenceList = null;
		}

		return Optional.ofNullable(competenceList);
	}

	public static void removeCache(long accountId, @NotNull CustomEhcacheManager customEhcacheManager) {
		String key = ListCompetenceCache.getCacheKey(accountId);

		customEhcacheManager.evict(key);
	}

}
