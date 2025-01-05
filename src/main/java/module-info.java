module com.gs.climaxrepoport {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires json;
    requires com.fasterxml.jackson.databind;
    requires java.xml.bind;
    requires tess4j;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires org.slf4j;
    requires static lombok;

    opens com.gs.climaxrepoport to javafx.fxml;
    exports com.gs.climaxrepoport;
    exports com.gs.climaxrepoport.controller;
    opens com.gs.climaxrepoport.controller to javafx.fxml;
    exports com.gs.climaxrepoport.models;
    requires java.xml;
    requires opencv;


}