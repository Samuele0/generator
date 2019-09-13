package com.github.samuele0.generator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CharInfo implements Serializable {
    private Map<Character, Long> map;
    private long total;

    public CharInfo() {
        map = new HashMap<>();
    }

    public void add(char c) {
        if (!map.containsKey(c))
            map.put(c, 0L);
        long val = map.get(c);
        total += 1;
        map.put(c, val + 1);
    }

    public Map<Character, Long> getMap() {
        return map;
    }

    public void setMap(Map<Character, Long> map) {
        this.map = map;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
