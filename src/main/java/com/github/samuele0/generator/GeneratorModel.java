package com.github.samuele0.generator;

import jdk.nashorn.internal.objects.StringIterator;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Model of a generation Pattern
 */
public class GeneratorModel implements Serializable {
    private List<Map<String, CharInfo>> model;
    private int length;

    public GeneratorModel(List<Map<String, CharInfo>> model, int length) {
        this.model = model;
        this.length = length;
    }

    public String generate(int length, int minOcc) {
        Random random = new Random();
        return generate(length, minOcc, getStart(random), "");
    }

    public String generate(int length, int minOcc, String start, String end) {
        StringBuilder stringBuilder = new StringBuilder(start);
        for (int i = 0; i < length; i++) {
            char baclup;
            for (int j = this.length - 1; j >= 0; j--) {
                if (stringBuilder.length() < j)
                    continue;
                String buff = stringBuilder.substring(stringBuilder.length() - j);
                Map<String, CharInfo> map = model.get(j);
                if (!map.containsKey(buff))
                    continue;
                CharInfo charInfo = map.get(buff);
                if (charInfo.getTotal() < minOcc) {

                    continue;
                }
                stringBuilder.append(pdist(charInfo.getMap().entrySet(), (long) (Math.random() * charInfo.getTotal())));
                break;
            }
        }
        return stringBuilder.append(end).toString();
    }

    private char pdist(Set<Map.Entry<Character, Long>> entrySet, long total) {
        long count = 0;
        for (Map.Entry<Character, Long> entry : entrySet) {
            count += entry.getValue();
            if (count >= total)
                return entry.getKey();
        }
        return 0;
    }

    public String getStart(Random random) {
        Set<String> stringSet = model.get(random.nextInt(model.size())).keySet();
        int item = random.nextInt(stringSet.size());
        int i = 0;
        for (String s : stringSet) {
            if (i == item)
                return s;
            i += 1;
        }
        return "**";
    }
}
