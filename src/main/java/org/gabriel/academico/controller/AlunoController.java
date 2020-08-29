package org.gabriel.academico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.gabriel.academico.service.AlunoService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class AlunoController implements Initializable {

    private AlunoService service;
    private Stage stage;
    @FXML private Label labelRodape;
    @FXML private TextField campoNome;
    @FXML private GridPane gridCampos;
    @FXML private AnchorPane painelRodape;
    @FXML private Button botaoSalvar;
    @FXML private Button botaoSair;
    @FXML private Button botaoIncluir;

    public void setPalcoOrigem(Stage stage) {
        this.stage = stage;
    }

    public void setService(AlunoService service) {
        this.service = service;
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void botaoIncluirAction(ActionEvent actionEvent) {
    }

    @FXML public void botaoSairAction(ActionEvent actionEvent) {
    }

    @FXML public void botaoSalvarAction(ActionEvent actionEvent) {
    }
}
