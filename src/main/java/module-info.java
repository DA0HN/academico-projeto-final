module EstudoDeCaso {

    // Javafx
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    // Lombok impl
    requires lombok;
    requires java.xml.bind;
    requires net.bytebuddy;
    // PostgreSQL / JPA
    requires java.sql;
    requires java.persistence;

    opens org.gabriel.academico.controller;
    opens org.gabriel.academico.model;
    opens org.gabriel.academico.model.enums;
    opens org.gabriel.academico.view;
    opens org.gabriel.academico.app;
}