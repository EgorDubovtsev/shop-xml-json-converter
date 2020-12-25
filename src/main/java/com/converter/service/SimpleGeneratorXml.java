package com.converter.service;

import com.converter.shop.SimpleShop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleGeneratorXml implements GeneratorXml {

    @Override
    public void generateXmlJAXB(Object object, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SimpleShop.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(file));
            marshaller.marshal(object, xmlStreamWriter);
            xmlStreamWriter.close();

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
