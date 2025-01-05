package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordParser implements Parser {

    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XWPFDocument document = new XWPFDocument(fis)) {
            for (int i = 0; i < document.getParagraphs().size(); i++) {
                String paragraphText = document.getParagraphs().get(i).getText();
                if (paragraphText != null && !paragraphText.trim().isEmpty()) {
                    String[] clientData = paragraphText.split("\\s+");
                    if (clientData.length == 5) {
                        try {
                            String nom = clientData[0].trim();
                            String prenom = clientData[1].trim();
                            int age = Integer.parseInt(clientData[2].trim());
                            String profession = clientData[3].trim();
                            double salaire = Double.parseDouble(clientData[4].trim().replaceAll("\\u00A0", "").replaceAll(",", "."));
                            Client client = new Client(nom, prenom, age, profession, salaire);
                            clients.add(client);
                        } catch (NumberFormatException e) {
                            System.out.println("Erreur de formatage dans le paragraphe : " + paragraphText);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ouverture du fichier : " + e.getMessage());
            throw e;
        }

        return clients;
    }
}