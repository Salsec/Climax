package com.gs.climaxrepoport.services;

import com.gs.climaxrepoport.parser.*;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.io.IOException;

public class ParserFactoryService {
    public static Parser getParser(String filePath) throws IOException {
        String extension = getFileExtension(filePath).toLowerCase();
        return switch (extension) {
            case "csv" -> new CSVParser();
            case "txt" -> new TXTParser();
            case "json" -> new JSONParser();
            case "xml" -> new XMLParser();
            case "pdf" -> new PDFParser();
            case "xls", "xlsx" -> new ExcelParser();
            case "docx" -> new WordParser();
            //case "png", "jpg", "jpeg" -> new ImageParser();
            default -> {
                showErrorDialog(filePath);
                throw new IOException("Format de fichier non supporté : " + filePath);
            }
        };
    }

    private static String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf('.');
        if (lastIndex == -1 || lastIndex == filePath.length() - 1) {
            return "";
        }
        return filePath.substring(lastIndex + 1);
    }

    private static void showErrorDialog(String filePath) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");

        String message = "Le format du fichier \"" + filePath + "\" n'est pas pris en charge.\nVeuillez utiliser un fichier avec un format valide (csv, txt, json, xml, pdf, xls, xlsx, docx).";
        alert.setHeaderText(message);
    }
}
