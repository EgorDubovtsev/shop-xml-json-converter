package com.converter.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SimpleReaderXml<T> implements ReaderXml<T> {
    @Override
    public T readXml(File file, Class<T> futureClass) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileReader(file));
            streamReader.nextTag();

            JAXBContext context = JAXBContext.newInstance(futureClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<T> jaxbElement = unmarshaller.unmarshal(streamReader, futureClass);
            streamReader.close();

            return jaxbElement.getValue();
        } catch (XMLStreamException | FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
