package com.github.vmbenchmarks;

import org.openjdk.jmh.annotations.Benchmark;

public class Reflection {

@Benchmark
public static void withoutReflection() throws Exception {
    SomeClass a = new SomeClass();
    a.getString();
}

    @Benchmark
    public static void doReflection() throws Exception {
        SomeClass a = (SomeClass) Class.forName("com.github.vmbenchmarks.SomeClass").newInstance();
        a.getString();
    }
}
