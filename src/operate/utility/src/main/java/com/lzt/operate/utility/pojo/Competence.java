package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.interfaces.IObjectExtensionMethod;
import lombok.Data;
import lombok.var;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author luzhitao
 */
@Data
public class Competence implements IObjectExtensionMethod {
	private static final long serialVersionUID = -6020503546833401610L;
	/**
	 * 名称
	 */
	String name;

	/**
	 * 唯一标识符
	 */
	String tag;

	/**
	 * 路径
	 */
	String relativePath;

	/**
	 * 扩展权限说明
	 */
	String explain;

	/**
	 * 扩展权限设置
	 */
	String expansionSet;

	public Competence() {
		tag = "";
		name = "";
		relativePath = "";
		explain = "";
		expansionSet = "";
	}

	public static Competence plus(Competence competenceBefore, Competence competenceAfter) {
		if (!competenceBefore.tag.equals(competenceAfter.tag)) {
			throw new RuntimeException("合并的两个权限体必须标识一致");
		}

		var mergerResult = merger(competenceBefore, competenceAfter);
		var result = new Competence();

		result.setTag(competenceBefore.tag);
		result.setExpansionSet(mergerResult);

		return result;
	}

	private static String merger(Competence competenceBefore, Competence competenceAfter) {
		return merger(competenceBefore.expansionSet, competenceAfter.expansionSet);
	}

	private static String merger(String expansionSetBefore, String expansionSetAfter) {
		List<Character> beforeList = new ArrayList<>();
		List<Character> afterList = new ArrayList<>();

		var beforeArray = expansionSetBefore.toCharArray();

		for (var c : beforeArray) {
			beforeList.add(c);
		}

		var afterArray = expansionSetAfter.toCharArray();

		for (var c : afterArray) {
			afterList.add(c);
		}

		List<Character> maxList;
		List<Character> minList;
		if (beforeList.size() >= afterList.size()) {
			maxList = beforeList;
			minList = afterList;
		} else {
			maxList = afterList;
			minList = beforeList;
		}

		for (var c : minList) {
			if (maxList.contains(c)) {
				var index = maxList.indexOf(c);
				char be = (maxList.get(index) == '1' ? '1' : '0');
				char af = (c == '1' ? '1' : '0');
				if (be == '0' && af == '0') {
					maxList.set(index, '0');
				} else {
					maxList.set(index, '1');
				}
			} else {
				maxList.add(c);
			}
		}

		return maxList.toString();
	}

	public static String buildCompetence(List<Competence> listCompetence) {
		var list = new ArrayList<String>();

		for (var item : listCompetence) {
			list.add(item.getTag() + (StringAssist.isNullOrEmpty(item.getExpansionSet())
					? "" : "|" + item.getExpansionSet()));
		}

		return StringAssist.join(list, ",");
	}

	public List<SerializableData> getExpansionSetCollection() {
		var result = new ArrayList<SerializableData>();

		var listExpand = StringAssist.split(explain, '|')
									 .stream()
									 .filter(o -> !StringAssist.isNullOrEmpty(o))
									 .toArray();

		var listExpansionSet = Stream.of(expansionSet.toCharArray()).map(Object::toString).toArray();

		if (listExpand.length > 0) {
			var count = listExpand.length;

			if (listExpand.length != listExpansionSet.length) {

				for (var i = 0; i < count; i++) {
					var s = new SerializableData();

					s.append("indexNo", i);
					s.append("name", (Serializable) listExpand[i]);
					s.append("value", "0");

					result.add(s);
				}
			} else {
				for (var i = 0; i < count; i++) {
					var s = new SerializableData();

					s.append("indexNo", i);
					s.append("name", (Serializable) listExpand[i]);
					s.append("value", listExpansionSet[i] == "1" ? "1" : "0");

					result.add(s);
				}
			}
		}

		return result;
	}
}
