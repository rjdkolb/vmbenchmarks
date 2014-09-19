package com.github.vmbenchmarks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openjdk.jmh.annotations.Benchmark;

public class RegularExpressions {

public static AtomicInteger counter = new AtomicInteger(1);
    
@Benchmark
public boolean validateEmail(){
    String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern = Pattern.compile(emailreg);
    Matcher matcher = pattern.matcher("richard"+counter.incrementAndGet()+"@GMail.com");
    return matcher.matches();
}
    

    public static void main(String[] args) {
        System.out.println(new RegularExpressions().validateEmail());
    }
}
