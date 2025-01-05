package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Client> parse(String filePath) throws IOException, ParserConfigurationException, SAXException;
}
