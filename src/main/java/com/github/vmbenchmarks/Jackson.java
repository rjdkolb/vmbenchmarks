package com.github.vmbenchmarks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import static com.github.vmbenchmarks.JaxbMashalUnMashal.staticCounter;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import org.openjdk.jmh.annotations.Benchmark;
import com.github.vmbenchmarks.User.Name;
import java.io.IOException;
import javax.xml.bind.JAXBException;

public class Jackson {

    public static AtomicInteger counter = new AtomicInteger(1);

    public static final String user1JsonStart = "{\n"
            + "  \"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack";
    public static final String user1JsonEnd = "\" },\n"
            + "  \"gender\" : \"MALE\",\n"
            + "  \"verified\" : false,\n"
            + "  \"userImage\" : \"Rm9vYmFyIQ==\"\n"
            + "}";
    static XmlMapper xmlMapperStatic = new XmlMapper();
    static int staticCounter = 1000;

    public static final String XML1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><gender>MALE</gender><name><first>";
    public static final String XML2 = "</first><last>";
    public static final String XML3 = "</last></name><verified>true</verified></user>";

    @Benchmark
    public User jsonUnmashal() throws Exception {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        StringBuilder jsonUser = new StringBuilder();
        jsonUser.append(user1JsonStart).append(counter.incrementAndGet()).append(user1JsonEnd);
        User user = mapper.readValue(jsonUser.toString(), User.class);
        return user;
    }

    @Benchmark
    public String jsonMashal() throws Exception {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        return mapper.writeValueAsString(new User(User.Gender.MALE, new Name("Person" + counter, "last"), true, new byte[0]));
    }

    @Benchmark
    public String xmlMashalJackson() throws JAXBException, JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new User.Name(Long.toHexString(staticCounter++), Long.toHexString(staticCounter++)));
        return xml;
    }

    @Benchmark
    public User xmlUnmashalJackson() throws JAXBException, JsonProcessingException, IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xmlValue = XML1 + (staticCounter++) + XML2 + (staticCounter++) + XML3;
        User user = xmlMapper.readValue(xmlValue, User.class);
        return user;
    }

    @Benchmark
    public String xmlMashalJacksonStatic() throws JAXBException, JsonProcessingException {

        String xml = xmlMapperStatic.writeValueAsString(new User.Name(Long.toHexString(staticCounter++), Long.toHexString(staticCounter++)));
        return xml;
    }

    @Benchmark
    public User xmlUnmashalJacksonStatic() throws JAXBException, JsonProcessingException, IOException {

        String xmlValue = XML1 + (staticCounter++) + XML2 + (staticCounter++) + XML3;
        User user = xmlMapperStatic.readValue(xmlValue, User.class);
        return user;
    }

}
