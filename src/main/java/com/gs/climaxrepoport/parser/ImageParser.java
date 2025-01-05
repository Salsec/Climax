package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageParser implements Parser {

    private final Tesseract tesseract;


    public ImageParser() {
        System.loadLibrary("/usr/local/lib/libtesseract.dylib");
        tesseract = new Tesseract();
        tesseract.setLanguage("fra");
    }

    public ImageParser(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        try {
            File imageFile = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            if (bufferedImage == null) {
                throw new IOException("Impossible de charger l'image à partir du fichier: " + filePath);
            }

            String extractedText = tesseract.doOCR(bufferedImage);

            Pattern pattern = Pattern.compile("([^,]+),\\s*([^,]+),\\s*(\\d+),\\s*([^,]+),\\s*(\\d+(?:\\.\\d+)?)");
            Matcher matcher = pattern.matcher(extractedText);

            while (matcher.find()) {
                try {
                    String lastName = matcher.group(1).trim();
                    String firstName = matcher.group(2).trim();
                    int age = Integer.parseInt(matcher.group(3).trim());
                    String profession = matcher.group(4).trim();
                    double salary = Double.parseDouble(matcher.group(5).trim());

                    clients.add(new Client(lastName, firstName, age, profession, salary));
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de parsing pour une ligne: " + e.getMessage());
                }
            }

        } catch (TesseractException e) {
            throw new IOException("Erreur lors de l'extraction du texte de l'image: " + e.getMessage(), e);
        }

        return clients;
    }
}
