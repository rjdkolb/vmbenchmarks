package com.github.vmbenchmarks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.openjdk.jmh.annotations.Benchmark;

public class JaxbMashalUnMashal {

    static JAXBContext staticJaxbContext = getStatic();
    static XmlMapper xmlMapperStatic = new XmlMapper();
    static int staticCounter = 1000;

    public static final String XML1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><gender>MALE</gender><name><first>";
    public static final String XML2 = "</first><last>";
    public static final String XML3 = "</last></name><verified>true</verified></user>";

    @Benchmark
    public String mashal() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        User user = new User(User.Gender.MALE, new User.Name(Long.toHexString(staticCounter++), Long.toHexString(staticCounter++)), true, null);

        jaxbMarshaller.marshal(user, stringWriter);
        return stringWriter.toString();
    }

    @Benchmark
    public String mashalStatic() throws JAXBException {
        Marshaller jaxbMarshaller = staticJaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        User user = new User(User.Gender.MALE, new User.Name(Long.toHexString(staticCounter++), Long.toHexString(staticCounter++)), true, null);
        jaxbMarshaller.marshal(user, stringWriter);
        System.out.println(stringWriter.toString());
        return stringWriter.toString();
    }

    @Benchmark
    public User unmashal() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

        User output = (User) jaxbUnMarshaller.unmarshal(new StringReader(XML1 + (staticCounter++) + XML2 + (staticCounter++) + XML3));
        return output;
    }

    @Benchmark
    public User unmashalStatic() throws JAXBException {
        Unmarshaller jaxbUnMarshaller = staticJaxbContext.createUnmarshaller();

        User output = (User) jaxbUnMarshaller.unmarshal(new StringReader(XML1 + (staticCounter++) + XML2 + (staticCounter++) + XML3));
        return output;
    }


    
    private static JAXBContext getStatic() {
        try {
            return JAXBContext.newInstance(User.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}
