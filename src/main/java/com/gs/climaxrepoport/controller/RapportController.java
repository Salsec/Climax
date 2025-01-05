package com.gs.climaxrepoport.controller;

import com.gs.climaxrepoport.models.Client;
import com.gs.climaxrepoport.parser.Parser;
import com.gs.climaxrepoport.services.ParserFactoryService;
import com.gs.climaxrepoport.services.SalaryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RapportController {
    @FXML
    private TableView<Client> rapportTable;

    @FXML
    private TableColumn<Client, String> nomColumn;

    @FXML
    private TableColumn<Client, String> prenomColumn;

    @FXML
    private TableColumn<Client, Integer> ageColumn;

    @FXML
    private TableColumn<Client, String> professionColumn;

    @FXML
    private TableColumn<Client, String> salaireColumn;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Label totalLabel;
    @FXML
    private Button moyenneSalaireButton;

    @FXML
    private void initialize() {
        moyenneSalaireButton.setVisible(false);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
    }



    @FXML
    private void onImportButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers XML", "*.xml"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers JSON", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Excel", "*.xls", "*.xlsx"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            fileNameLabel.setText(file.getName());
            try {
                Parser parser = ParserFactoryService.getParser(file.getPath());
                List<Client> clients = parser.parse(file.getPath());
                StringBuilder rapport = new StringBuilder();
                for (Client client : clients) {
                    rapport.append(client.toString()).append("\n");
                }
                ObservableList<Client> clientData = FXCollections.observableArrayList(clients);
                rapportTable.setItems(clientData);
                int total = rapportTable.getItems().size();
                totalLabel.setText("Total: " + total + " élément(s)");
                if (total > 0) {
                    moyenneSalaireButton.setVisible(true);
                } else {
                    moyenneSalaireButton.setVisible(false);
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de l'importation du fichier");
                alert.setContentText("Une erreur est survenue lors de l'importation du fichier : " + e.getMessage());
                System.err.println(e.getMessage());
                alert.showAndWait();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onShowAverageSalaryByProfession() {
        List<Client> clients = rapportTable.getItems();
        var moyenneSalaire = SalaryService.calculateAverageSalaryByProfession(clients);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Moyenne des salaires par profession");
        alert.setHeaderText("Moyenne des salaires pour chaque profession");
        TableView<Map.Entry<String, Double>> tableView = new TableView<>();
        TableColumn<Map.Entry<String, Double>, String> professionColumn = new TableColumn<>("Profession");
        TableColumn<Map.Entry<String, Double>, String> salaireColumn = new TableColumn<>("Moyenne Salaire (K€)");
        professionColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        salaireColumn.setCellValueFactory(param -> {
            Double salaire = param.getValue().getValue();
            String salaireFormatted = String.format("%.2f K€", salaire);
            return new SimpleStringProperty(salaireFormatted);
        });

        tableView.getColumns().add(professionColumn);
        tableView.getColumns().add(salaireColumn);
        ObservableList<Map.Entry<String, Double>> data = FXCollections.observableArrayList(moyenneSalaire.entrySet());
        tableView.setItems(data);
        VBox vbox = new VBox();
        vbox.getChildren().add(tableView);
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }


}