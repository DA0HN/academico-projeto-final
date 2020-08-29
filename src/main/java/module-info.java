module EstudoDeCaso {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;

    opens org.gabriel.academico.controller;
    opens org.gabriel.academico.model;
    opens org.gabriel.academico.view;
    opens org.gabriel.academico.app;
}