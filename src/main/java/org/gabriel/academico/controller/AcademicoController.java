package org.gabriel.academico.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.gabriel.academico.view.IncluirAluno;
import org.gabriel.academico.view.IncluirProfessor;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author daohn on 02/09/2020
 * @project EstudoDeCaso
 */
public class AcademicoController implements Initializable {

    @FXML private Button botaoAluno;
    @FXML private Button botaoProfessor;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        botaoAluno.setOnAction(value -> {
            var alunoView = new IncluirAluno();
            alunoView.start(stage);
        });
        botaoProfessor.setOnAction(value -> {
            var professorView = new IncluirProfessor();
            professorView.start(stage);
        });
    }
}
