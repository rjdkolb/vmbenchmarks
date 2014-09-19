package com.github.vmbenchmarks;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import org.openjdk.jmh.annotations.Benchmark;
import com.github.vmbenchmarks.User.Name;

public class Jackson {

    public static AtomicInteger counter = new AtomicInteger(1);
    
public static final String user1JsonStart = "{\n"
        + "  \"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack";
public static final String user1JsonEnd ="\" },\n"
        + "  \"gender\" : \"MALE\",\n"
        + "  \"verified\" : false,\n"
        + "  \"userImage\" : \"Rm9vYmFyIQ==\"\n"
        + "}";

@Benchmark
public User jsonUnmashal() throws Exception {
    ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    StringBuilder jsonUser = new StringBuilder();
    jsonUser.append(user1JsonStart).append( counter.incrementAndGet()).append(user1JsonEnd);
    User user = mapper.readValue(jsonUser.toString(), User.class);
    return user;
}
    @Benchmark
    public String jsonMashal() throws Exception {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        return mapper.writeValueAsString(new User(User.Gender.MALE, new Name("Person"+counter,"last"), true, new byte[0]));
    }
}
