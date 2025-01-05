package com.gs.climaxrepoport.parser;

import com.gs.climaxrepoport.models.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TXTParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws IOException {
        List<Client> clients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
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
                    System.err.println("Erreur de format sur la ligne : " + line);
                }
            } else {
                System.err.println("Format incorrect : " + line);
            }
        }
        reader.close();
        return clients;
    }
}
