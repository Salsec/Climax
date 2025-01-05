package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws ParserConfigurationException, IOException, SAXException {
        List<Client> clients = new ArrayList<>();
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("employe");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                String prenom = element.getElementsByTagName("prenom").item(0).getTextContent();
                int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                String profession = element.getElementsByTagName("profession").item(0).getTextContent();
                double salaire = Double.parseDouble(element.getElementsByTagName("salaire").item(0).getTextContent());
                clients.add(new Client(nom, prenom, age, profession, salaire));
            }
        }
        return clients;
    }
}
