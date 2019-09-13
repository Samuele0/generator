package com.github.samuele0.generator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GeneratorModelTest {
    @Test
    public void test() throws IOException {
        System.out.println("FROM TRAINING");
        NamingUtil nu = NamingUtil.fromFile(Paths.get("./train.txt"));
        System.out.println(Arrays.toString(nu.generate(1200000)));
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        System.out.println("FROM FILE");
        NamingUtil nu = NamingUtil.loadFile(Paths.get("./eng-names.model"));
        System.out.println(Arrays.toString(nu.generate(120)));
    }

}