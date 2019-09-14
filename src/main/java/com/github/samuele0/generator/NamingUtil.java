package com.github.samuele0.generator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NamingUtil {
    private GeneratorModel model;

    public NamingUtil(GeneratorModel model) {
        this.model = model;
    }

    public static NamingUtil fromFile(Path p) throws IOException {
        Stream<String> stringStream = Files.newBufferedReader(p).lines();
        return new NamingUtil(ModelTrainer.I.train(stringStream, 5));
    }

    public static NamingUtil loadFile(Path p) throws IOException, ClassNotFoundException {
        return new NamingUtil((GeneratorModel) new ObjectInputStream(Files.newInputStream(p)).readObject());
    }

    public void serializeToFile(Path p) throws IOException {
        new ObjectOutputStream(Files.newOutputStream(p)).writeObject(model);
    }

    public String[] generate(int count, int length, String start, String end) {
        Random random = new Random();
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            names[i] = model.generate(3 + random.nextInt(length - 3), 4, start, end);
        }
        return names;
    }

    public String generate() {
        return model.generate(3 + new Random().nextInt(7 - 3), 4);
    }

    public String[] generate(int count) {
        return generate(count, 7, "", "");
    }

    public String[] generate(int count, int length, String start, String end, String pattern) {
        return Arrays.stream(generate(count * 1000, length, start, end)).filter(s -> s.matches(pattern)).limit(count).toArray(String[]::new);
    }

}

