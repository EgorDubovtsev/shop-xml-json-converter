package com.converter.service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleGeneratorXml<T> implements GeneratorXml<T> {

    @Override
    public void generateXmlJAXB(T objectForWriting, File fileForGeneration, Class<T> classOfObjectForWriting) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classOfObjectForWriting);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(fileForGeneration));
            marshaller.marshal(objectForWriting, xmlStreamWriter);
            xmlStreamWriter.close();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
