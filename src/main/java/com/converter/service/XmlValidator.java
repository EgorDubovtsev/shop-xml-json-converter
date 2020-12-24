package com.converter.service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    public void checkValidation(File xmlFile, File schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source xmlFileSource = new StreamSource(xmlFile);
            validator.validate(xmlFileSource);
            System.out.println("XML IS VALID");

        } catch (SAXException | IOException e) {
            e.printStackTrace();
            System.out.println("XML IS INVALID");

        }

    }
}
