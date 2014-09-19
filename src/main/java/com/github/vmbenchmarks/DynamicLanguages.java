package com.github.vmbenchmarks;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.io.BufferedReader;
import java.io.StringReader;
import org.openjdk.jmh.annotations.Benchmark;

public class DynamicLanguages {

@Benchmark
public Object groovy() {
    Binding binding = new Binding();
    binding.setVariable("foo", new Integer(2));
    GroovyShell shell = new GroovyShell(binding);

    return shell.evaluate("x = 123; return foo * 10 +x ");
}


    public static void main(String[] args) {
        System.out.println(new DynamicLanguages().groovy());
    }

}
