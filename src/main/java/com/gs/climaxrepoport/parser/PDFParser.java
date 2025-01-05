package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        File file = new File(filePath);

        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String content = pdfStripper.getText(document);
            String[] lines = content.split("\\r?\\n");

            for (String line : lines) {
                String[] data = line.split("\\s+");
                if (data.length == 5) {
                    try {
                        String nom = data[0];
                        String prenom = data[1];
                        int age = Integer.parseInt(data[2]);
                        String profession = data[3];
                        double salaire = Double.parseDouble(data[4]);

                        Client client = new Client(nom, prenom, age, profession, salaire);
                        clients.add(client);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format (nombre invalide) : " + line);
                    }
                } else {
                    System.err.println("Ligne ignorée (format incorrect) : " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Erreur lors de la lecture du fichier PDF", e);
        }

        return clients;
    }
}
