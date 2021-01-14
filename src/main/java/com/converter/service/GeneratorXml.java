package com.converter.service;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface GeneratorXml<T> {
    void generateXmlJAXB(T objectForWriting, File fileForGeneration, Class<T> classOfObjectForWriting) throws JAXBException;
}
