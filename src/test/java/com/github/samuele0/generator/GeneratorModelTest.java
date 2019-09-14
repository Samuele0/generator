package com.github.samuele0.generator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.github.samuele0.generator.NamingUtil.*;
import static org.junit.Assert.*;

public class GeneratorModelTest {
    @Test
    public void test() throws IOException {
        System.out.println("FROM TRAINING");
        NamingUtil nu = fromFile(Paths.get("./train.txt"));
        System.out.println(Arrays.toString(nu.generate(1200000)));
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        System.out.println("FROM FILE");
        NamingUtil nu = loadFile(Paths.get("./en-names.model"));
        System.out.println(Arrays.toString(nu.generate(120)));
    }

    @Test
    public void testLarge() throws IOException {
        NamingUtil nu = fromFile(Paths.get("./paroleitaliane/paroleitaliane/9000_nomi_propri.txt"));
        System.out.println(Arrays.toString(nu.generate(120,9,"","",".+[aeiou]")));
        nu.serializeToFile(Paths.get("./it-names.model"));
    }

}