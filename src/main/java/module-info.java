module com.example.javafx_practice {
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
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.javafx_plus_willpower to javafx.fxml;
    exports com.example.javafx_plus_willpower;

    exports com.example.javafx_plus_willpower.utilities;
    opens com.example.javafx_plus_willpower.utilities to javafx.fxml;
    exports com.example.javafx_plus_willpower.controller;
    opens com.example.javafx_plus_willpower.controller to javafx.fxml;
    exports com.example.javafx_plus_willpower.callbacks;
    opens com.example.javafx_plus_willpower.callbacks to javafx.fxml;
}