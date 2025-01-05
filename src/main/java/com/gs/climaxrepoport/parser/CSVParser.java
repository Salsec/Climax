package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length == 5) {
                    try {
                        String nom = data[0].trim();
                        String prenom = data[1].trim();
                        int age = Integer.parseInt(data[2].trim());
                        String profession = data[3].trim();
                        double salaire = Double.parseDouble(data[4].trim());
                        Client client = new Client(nom, prenom, age, profession, salaire);
                        clients.add(client);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format (nombre invalide) dans la ligne : " + line);
                    }
                } else {
                    System.err.println("Ligne ignorée (format incorrect) : " + line);
                }
            }
        }
        return clients;
    }
}
