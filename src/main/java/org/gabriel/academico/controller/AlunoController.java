package org.gabriel.academico.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.gabriel.academico.controller.util.MensagemUtil;
import org.gabriel.academico.model.Aluno;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.Endereco;
import org.gabriel.academico.model.enums.Sexo;
import org.gabriel.academico.service.AlunoService;
import org.gabriel.academico.service.CursoService;
import org.gabriel.academico.service.ServiceException;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class AlunoController implements Initializable {

    private final CursoService cursoService;
    private List<Curso> cursos;

    private AlunoService service;
    private Stage stage;

    // Ação
    @FXML private Button botaoSalvar;
    @FXML private Button botaoSair;
    @FXML private Button botaoIncluir;
    // Status para usuário
    @FXML private Label labelRodape;
    @FXML private AnchorPane painelRodape;
    @FXML private GridPane gridCampos;

    // Dados do usuário
    @FXML private ComboBox<Endereco> comboBoxEndereco;
    @FXML private ComboBox<Curso> comboBoxCurso;
    @FXML private ComboBox<Sexo> comboBoxSexo;
    @FXML private DatePicker datePicker;
    @FXML private TextField campoNome;
    @FXML private TextField campoMatricula;

    public AlunoController() {
        this.cursoService = new CursoService();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setService(AlunoService service) {
        this.service = service;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gridCampos.setDisable(true);
        this.botaoIncluir.setDisable(false);
        this.botaoSalvar.setDisable(true);

        inicializarComboBox();
    }

    private void inicializarComboBox() {
        this.comboBoxCurso.setItems(FXCollections.observableArrayList(this.cursos));
        this.comboBoxSexo.setItems(FXCollections.observableArrayList(Sexo.values()));
    }

    private void iniciarInclusao() {
        this.botaoIncluir.setDisable(true);
        this.botaoSalvar.setDisable(false);
        this.campoNome.clear();
        this.gridCampos.setDisable(false);
        this.campoNome.requestFocus();
        this.labelRodape.setText("Inclusão em andamento...");
    }

    private void processarInclusao() {
        var aluno = new Aluno();
        aluno.setMatricula(Integer.valueOf(this.campoMatricula.getText()));
        aluno.setDataNascimento(this.datePicker.getValue());
        aluno.setNome(this.campoNome.getText());
        aluno.setSexo(this.comboBoxSexo.getValue());
        aluno.setEndereco(this.comboBoxEndereco.getValue());

        try {
            this.service.save(aluno);
            this.botaoIncluir.setDisable(false);
            this.botaoSalvar.setDisable(true);
            this.gridCampos.setDisable(true);
            this.labelRodape.setText("Inclusão realizada com sucesso!");
        }
        catch(ServiceException e) {
            MensagemUtil.mensagemErro(e.getMessage());
            this.botaoIncluir.setDisable(true);
            this.botaoSalvar.setDisable(false);
            this.campoNome.requestFocus();
        }
    }

    private void sair() {
        this.stage.close();
    }

    @FXML public void botaoIncluirAction(ActionEvent actionEvent) {
        this.iniciarInclusao();
    }

    @FXML public void botaoSairAction(ActionEvent actionEvent) {
        this.sair();
    }

    @FXML public void botaoSalvarAction(ActionEvent actionEvent) {
        this.processarInclusao();
    }
}
