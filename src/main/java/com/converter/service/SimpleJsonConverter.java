package com.converter.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SimpleJsonConverter<T> implements JsonConverter<T> {
    private ObjectMapper objectMapper;
    private ReaderXml<T> readerXml;


    public SimpleJsonConverter(ObjectMapper objectMapper, ReaderXml<T> readerXml) {
        this.objectMapper = objectMapper;
        this.readerXml = readerXml;
    }

    @Override
    public void convertToJson(File xmlFile, File jsonFile, Class<T> classForConvert) throws IOException {
        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        objectMapper.writeValue(jsonFile, readerXml.readXml(xmlFile, classForConvert));
    }

    @Override
    public void convertToXml(File jsonFile, File fileForConvert, Class<T> classForConvert, GeneratorXml generatorXml) throws IOException, JAXBException {
        T convertedClass = objectMapper.readValue(jsonFile, classForConvert);
        generatorXml.generateXmlJAXB(convertedClass, fileForConvert);
    }
}
