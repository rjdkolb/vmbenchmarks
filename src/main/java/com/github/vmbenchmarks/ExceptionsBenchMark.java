package com.github.vmbenchmarks;

import org.openjdk.jmh.annotations.Benchmark;

public class ExceptionsBenchMark {

    @Benchmark
    public RuntimeException throwRuntimeException() {
        try {
            if (500 == 500) {
                throw new RuntimeException();
            }
        } catch (RuntimeException rExp) {
            return rExp;
        }
        return null;
    }

}
