package com.gs.climaxrepoport.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.climaxrepoport.models.Client;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONParser implements Parser {
    @Override
    public List<Client> parse(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clients = objectMapper.readValue(new File(filePath), new TypeReference<List<Client>>(){});
        return clients;
    }
}
