package com.github.samuele0.generator;

import java.util.*;
import java.util.stream.Stream;

/**
 * Singleton Trainer to generate a GeneratorModel from a Stream of Strings.
 */
public enum ModelTrainer {
    I;
    private List<Map<String, CharInfo>> model;
    private int track;

    public GeneratorModel train(Stream<String> dataset, int track) {
        this.track = track;
        initMap();
        dataset.forEach(this::trainString);
        return new GeneratorModel(model, track);
    }

    private void trainString(String s) {
        s = s.toLowerCase();
        int len = s.length();
        StringBuilder buffer = new StringBuilder(s.substring(0, Math.min(len, track)));
        for (char c : s.substring(Math.min(len, track)).toCharArray()) {
            for (int i = 0; i < Math.min(len, track); i++) {
                Map<String, CharInfo> lmodel = model.get(i);
                String lbuffer = buffer.substring(buffer.length() - i, buffer.length());
                if (!lmodel.containsKey(lbuffer))
                    lmodel.put(lbuffer, new CharInfo());
                CharInfo cinfo = lmodel.get(lbuffer);
                cinfo.add(c);
            }
            buffer.delete(0, 1).append(c);
        }
    }

    private void initMap() {
        model = new ArrayList<>(track);
        for (int i = 0; i < track; i++) {
            model.add(new HashMap<>());
        }
    }

}
