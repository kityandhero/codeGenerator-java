package com.lzt.operate.entities;

import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.interfaces.IObjectExtensionMethod;
import lombok.Data;
import lombok.var;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lzt
 */
@Data
public class Competence implements IObjectExtensionMethod {
    /**
     * 名称
     */
    StringEx name;

    /**
     * 唯一标识符
     */
    StringEx guidTag;

    /**
     * 路径
     */
    StringEx relativePath;

    /**
     * 扩展权限说明
     */
    StringEx explain;

    /**
     * 扩展权限设置
     */
    StringEx expansionSet;

    public Competence() {
        this.guidTag = new StringEx("");
        this.name = new StringEx("");
        this.relativePath = new StringEx("");
        this.explain = new StringEx("");
        this.expansionSet = new StringEx("");
    }

    public static Competence plus(Competence competenceBefore, Competence competenceAfter) throws Exception {
        if (competenceBefore.guidTag != competenceAfter.guidTag) {
            throw new Exception("合并的两个权限体必须标识一致");
        }

        var mergerResult = merger(competenceBefore, competenceAfter);
        var result = new Competence();

        result.setGuidTag(competenceBefore.guidTag);
        result.setExpansionSet(new StringEx(mergerResult));

        return result;
    }

    private static String merger(Competence competenceBefore, Competence competenceAfter) {
        return merger(competenceBefore.expansionSet.toString(), competenceAfter.expansionSet.toString());
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
            list.add(item.getGuidTag() + (item.getExpansionSet()
                                              .isNullOrEmpty() ? "" : "|" + item.getExpansionSet()));
        }

        return StringEx.join(",", list).toString();
    }

    public List<SerializableData> getExpansionSetCollection() {
        var result = new ArrayList<SerializableData>();

        var listExpand = explain.split('|').stream().filter(o -> !StringEx.isNullOrEmpty(o)).toArray();

        var listExpansionSet = Stream.of(expansionSet.toString().toCharArray()).map(Object::toString).toArray();

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
