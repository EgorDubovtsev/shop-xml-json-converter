package com.converter.service;

import com.converter.shop.Shop;
import com.converter.shop.SimpleShop;

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
            while (!streamReader.getLocalName().equals("simpleShop")) {
                streamReader.nextTag();
            }
            JAXBContext context = JAXBContext.newInstance(futureClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<T> jb = unmarshaller.unmarshal(streamReader,futureClass);
            streamReader.close();

            return jb.getValue();
        } catch (XMLStreamException | FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
