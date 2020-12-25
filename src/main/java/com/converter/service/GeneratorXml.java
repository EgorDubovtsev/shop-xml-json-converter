package com.converter.service;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface GeneratorXml {
    void generateXmlJAXB(Object object, File fileForGeneration) throws JAXBException;
}
