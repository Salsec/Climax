package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFOCRParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        File file = new File(filePath);

        try (PDDocument document = PDDocument.load(file)) {
            PDFRenderer renderer = new PDFRenderer(document);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("tessdata");
            tesseract.setLanguage("fra+eng");

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300);
                String text = tesseract.doOCR(image);
                parseTextToClients(text, clients);
            }
        } catch (TesseractException e) {
            throw new IOException("Erreur OCR lors de la lecture du PDF", e);
        }

        return clients;
    }

    private void parseTextToClients(String text, List<Client> clients) {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            String[] data = line.split("\\s+");
            if (data.length == 5) {
                try {
                    String nom = data[0];
                    String prenom = data[1];
                    int age = Integer.parseInt(data[2]);
                    String profession = data[3];
                    double salaire = Double.parseDouble(data[4]);

                    clients.add(new Client(nom, prenom, age, profession, salaire));
                } catch (NumberFormatException e) {
                    System.err.println("Format incorrect : " + line);
                }
            } else {
                System.err.println("Ligne ignorée (format invalide) : " + line);
            }
        }
    }
}
