package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.interfaces.IObjectExtensionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author luzhitao
 */
public class Competence implements IObjectExtensionMethod {
	private static final long serialVersionUID = -6020503546833401610L;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 唯一标识符
	 */
	private String tag;

	/**
	 * 路径
	 */
	private String relativePath;

	/**
	 * 扩展权限说明
	 */
	private String explain;

	/**
	 * 扩展权限设置
	 */
	private String expansionSet;

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

		String mergerResult = merger(competenceBefore, competenceAfter);
		Competence result = new Competence();

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

		char[] beforeArray = expansionSetBefore.toCharArray();

		for (char c : beforeArray) {
			beforeList.add(c);
		}

		char[] afterArray = expansionSetAfter.toCharArray();

		for (char c : afterArray) {
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

		for (Character c : minList) {
			if (maxList.contains(c)) {
				int index = maxList.indexOf(c);
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
		List<String> list = new ArrayList<>();

		for (Competence item : listCompetence) {
			list.add(item.getTag() + (StringAssist.isNullOrEmpty(item.getExpansionSet())
					? "" : "|" + item.getExpansionSet()));
		}

		return StringAssist.join(list, ",");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	private String getExpansionSet() {
		return expansionSet;
	}

	public void setExpansionSet(String expansionSet) {
		this.expansionSet = expansionSet;
	}

	public List<SerializableData> getExpansionSetCollection() {
		List<SerializableData> result = new ArrayList<>();

		Object[] listExpand = StringAssist.split(explain, '|')
										  .stream()
										  .filter(o -> !StringAssist.isNullOrEmpty(o))
										  .toArray();

		Object[] listExpansionSet = Stream.of(expansionSet.toCharArray()).map(Object::toString).toArray();

		if (listExpand.length > 0) {
			int count = listExpand.length;

			if (listExpand.length != listExpansionSet.length) {

				for (int i = 0; i < count; i++) {
					SerializableData s = new SerializableData();

					s.append("indexNo", i);
					s.append("name", listExpand[i]);
					s.append("value", "0");

					result.add(s);
				}
			} else {
				for (int i = 0; i < count; i++) {
					SerializableData s = new SerializableData();

					s.append("indexNo", i);
					s.append("name", listExpand[i]);
					s.append("value", listExpansionSet[i] == "1" ? "1" : "0");

					result.add(s);
				}
			}
		}

		return result;
	}
}
