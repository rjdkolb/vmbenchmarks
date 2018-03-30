package com.github.vmbenchmarks;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MicroBenchMarks {

    private static double n = 10;
    static List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10);

    @Benchmark
    public void deadCodeElimination() {
        @SuppressWarnings("unused")
        double r = n * Math.log(n) / 2;
    }

    @Benchmark
    public void noDeadCodeElimination() {
        double r = n * Math.log(n) / 2;
        if (r == n && r == 0) {
            throw new IllegalStateException();
        }
    }

    private static double x = Math.PI;

    @Benchmark
    public double calculateLogPiCodeElimination() {
        //code elimination
        return Math.log(Math.PI);
    }

    @Benchmark
    public double calculateLogPi() {
        return Math.log(x);
    }

    private static double[] A = new double[2048];

    @Benchmark
    public double loopThatCanBeUnrolled() {
        double sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }

    @Benchmark
    public double manualUnrollOfLoop() {
        double sum = 0;
        for (int i = 0; i < A.length; i += 4) {
            sum += A[i] + A[i + 1] + A[i + 2] + A[i + 3];
        }
        return sum;
    }

    @Benchmark
    public List<Integer> arrayListForeach() {
        for (Integer i : arrayList) {
        }
        return arrayList;
    }

    @Benchmark
    public Iterator<Integer> arrayListIterator() {
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        return iterator;
    }
}
