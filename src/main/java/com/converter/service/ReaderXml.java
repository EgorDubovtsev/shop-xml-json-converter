package com.converter.service;

import java.io.File;

public interface ReaderXml<T> {
    T readXml(File file, Class<T> futureClass);
}
