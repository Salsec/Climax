package com.gs.climaxrepoport.services;

import com.gs.climaxrepoport.models.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryService {
    public static Map<String, Double> calculateAverageSalaryByProfession(List<Client> clients) {
        Map<String, Double> salarySum = new HashMap<>();
        Map<String, Integer> professionCount = new HashMap<>();

        for (Client client : clients) {
            String profession = client.getProfession();
            double salary = client.getSalaire();
            salarySum.put(profession, salarySum.getOrDefault(profession, 0.0) + salary);
            professionCount.put(profession, professionCount.getOrDefault(profession, 0) + 1);
        }

        Map<String, Double> averageSalaries = new HashMap<>();
        for (String profession : salarySum.keySet()) {
            double avgSalary = salarySum.get(profession) / professionCount.get(profession);

            averageSalaries.put(profession, avgSalary);
        }

        return averageSalaries;
    }
}
