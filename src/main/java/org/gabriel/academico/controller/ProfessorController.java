package org.gabriel.academico.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.gabriel.academico.service.ProfessorService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ProfessorController implements Initializable {
    private ProfessorService service;
    private Stage stage;

    public void setService(ProfessorService service) {
        this.service = service;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO (daohn, 01/09/2020): Método gerado automaticamente

    }
}
