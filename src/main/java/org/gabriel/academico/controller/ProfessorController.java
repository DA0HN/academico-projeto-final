package org.gabriel.academico.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.gabriel.academico.controller.util.MensagemUtil;
import org.gabriel.academico.model.Curso;
import org.gabriel.academico.model.Endereco;
import org.gabriel.academico.model.Estado;
import org.gabriel.academico.model.Municipio;
import org.gabriel.academico.model.Professor;
import org.gabriel.academico.model.Telefone;
import org.gabriel.academico.model.enums.Sexo;
import org.gabriel.academico.model.enums.TipoLogradouro;
import org.gabriel.academico.model.enums.TipoTelefone;
import org.gabriel.academico.model.enums.Titulacao;
import org.gabriel.academico.service.CursoService;
import org.gabriel.academico.service.EstadoService;
import org.gabriel.academico.service.MunicipioService;
import org.gabriel.academico.service.ProfessorService;
import org.gabriel.academico.service.ServiceException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class ProfessorController implements Initializable {

    private final CursoService     cursoService;
    private final EstadoService    estadoService;
    private final MunicipioService municipioService;

    // Listas para popular Combo Box
    private List<Curso>     cursos;
    private List<Estado>    estados;
    private List<Municipio> municipios;

    private ProfessorService service;
    private Stage            stage;

    // Ação
    @FXML private Button     botaoSalvar;
    @FXML private Button     botaoSair;
    @FXML private Button     botaoIncluir;
    // Status para usuário
    @FXML private Label      labelRodape;
    @FXML private AnchorPane painelRodape;
    @FXML private GridPane   gridCampos;

    // Bloco de inserção de telefone
    @FXML private ListView<Telefone>     listTelefonesInseridos;
    @FXML private ComboBox<TipoTelefone> comboTipoTelefone;
    @FXML private AnchorPane             paneTelefone;
    @FXML private Button                 botaoAdicionarTelefone;

    // Bloco de inserção de curso
    @FXML private ComboBox<Curso> comboCurso;
    @FXML private ListView<Curso> listCursosInseridos;
    @FXML private Button          botaoAdicionarCurso;
    @FXML private AnchorPane      paneCurso;


    @FXML private DatePicker               datePicker;
    @FXML private ComboBox<Estado>         comboEstado;
    @FXML private ComboBox<Sexo>           comboSexo;
    @FXML private ComboBox<Municipio>      comboMunicipio;
    @FXML private ComboBox<TipoLogradouro> comboTipoLogradouro;
    @FXML private ComboBox<Titulacao>      comboTitulacao;
    @FXML private TextField                fieldNome;
    @FXML private TextField                fieldNumero;
    @FXML private TextField                fieldLogradouro;
    @FXML private TextField                fieldTelefone;
    @FXML private TextField                fieldBairro;
    @FXML private TextField                fieldFormacao;


    public ProfessorController() {
        this.cursoService     = new CursoService();
        this.estadoService    = new EstadoService();
        this.municipioService = new MunicipioService();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setService(ProfessorService service) {
        this.service = service;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        initialState();
    }

    private void initialState() {
        // Desativa o grid até o botão de salvar sofrer alteração
        this.gridCampos.setDisable(true);
        // Estado do botão incluir e botão salvar, alternam entre si
        this.botaoIncluir.setDisable(false);
        this.botaoSalvar.setDisable(true);

        // Combo municipio é ativada quando a combo Estado é selecionada
        this.comboMunicipio.setVisible(false);
        this.comboMunicipio.setDisable(true);
        // Inicia o bloco de inserção de telefone
        this.paneTelefone.setDisable(true);
        this.listTelefonesInseridos.setVisible(false);
        // Inicia o bloco de inserção de curso
        this.paneCurso.setDisable(true);
        this.listCursosInseridos.setVisible(false);
        this.initPrompt();
    }

    private void resetState() {

        // Altera os estados dos botões
        this.botaoIncluir.setDisable(false);
        this.botaoSalvar.setDisable(true);
        // Desabilita grid
        this.gridCampos.setDisable(true);

        this.comboCurso.getItems().clear();
        this.comboSexo.getItems().clear();
        this.comboTipoTelefone.getItems().clear();
        this.comboEstado.getItems().clear();
        this.comboMunicipio.getItems().clear();
        this.comboTipoLogradouro.getItems().clear();

        this.fieldFormacao.clear();
        this.fieldLogradouro.clear();
        this.fieldNome.clear();
        this.fieldTelefone.clear();
        this.fieldNumero.clear();

        // Volta a combo Municipio para o estado inicial
        this.comboMunicipio.setVisible(false);
        this.comboMunicipio.setDisable(true);

        // Volta o pane de telefone para o estado inicial
        this.paneTelefone.setDisable(true);
        this.listTelefonesInseridos.getItems().clear();
        this.listTelefonesInseridos.setVisible(false);

        // Volta o pane de curso para o estado inicial
        this.paneCurso.setDisable(true);
        this.listCursosInseridos.getItems().clear();
        this.listCursosInseridos.setVisible(false);

        this.comboEstado.getSelectionModel().clearSelection();
        this.comboCurso.getSelectionModel().clearSelection();
    }

    private void initPrompt() {
        this.comboSexo.setPromptText("Selecione o sexo");
        this.comboTipoLogradouro.setPromptText("Tipo do logradouro");
        this.comboCurso.setPromptText("Selecione o curso");
        this.comboEstado.setPromptText("Selecione o estado");
        this.comboTipoTelefone.setPromptText("Tipo do telefone");
    }

    private void initializeComboBox() {
        try {
            this.cursos  = this.cursoService.buscarTodos();
            this.estados = this.estadoService.buscarTodos();
            this.comboCurso
                    .setItems(FXCollections.observableArrayList(this.cursos));
            this.comboSexo
                    .setItems(FXCollections.observableArrayList(Sexo.values()));
            this.comboTipoTelefone
                    .setItems(FXCollections.observableArrayList(TipoTelefone.values()));
            this.comboEstado
                    .setItems(FXCollections.observableArrayList(this.estados));
            this.comboTipoLogradouro
                    .setItems(FXCollections.observableArrayList(TipoLogradouro.values()));
            this.comboTitulacao
                    .setItems(FXCollections.observableArrayList(Titulacao.values()));
            initPrompt();
        }
        catch(ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void iniciarInclusao() {
        initializeComboBox();

        // Alterna os estados dos botões Incluir/Salvar
        this.botaoIncluir.setDisable(true);
        this.botaoSalvar.setDisable(false);

        this.fieldNome.requestFocus();
        // Altera status do rodapé
        this.labelRodape.setText("Inclusão em andamento...");
        // Ativa o bloco grid
        this.gridCampos.setDisable(false);
        // Ativa o bloco do telefone
        this.paneTelefone.setDisable(false);
        // Ativa o bloco de curso
        this.paneCurso.setDisable(false);

        initPrompt();
    }

    private Professor createProfessor() {
        var professor = new Professor();
        professor.setFormacao(this.fieldFormacao.getText());
        professor.setTitulacao(comboTitulacao.getValue());
        professor.setDataNascimento(this.datePicker.getValue());
        professor.setNome(this.fieldNome.getText());
        professor.setSexo(this.comboSexo.getValue());
        var endereco = new Endereco(this.fieldLogradouro.getText(),
                                    this.comboTipoLogradouro.getValue(),
                                    Integer.parseInt(this.fieldNumero.getText()),
                                    this.fieldBairro.getText(),
                                    this.comboMunicipio.getValue()
        );
        professor.setEndereco(endereco);
        professor.addCursos(listCursosInseridos.getItems());
        professor.addTelefones(listTelefonesInseridos.getItems());
        return professor;
    }

    private void processarInclusao() {
        var aluno = createProfessor();
        try {
            this.service.save(aluno);
            resetState();
            MensagemUtil.mensagemInformacao("Aluno " + aluno.getNome() + " foi salvo com sucesso.");
            this.labelRodape.setText("Inclusão realizada com sucesso!");
        }
        catch(ServiceException e) {
            MensagemUtil.mensagemErro(e.getMessage());
            this.botaoIncluir.setDisable(true);
            this.botaoSalvar.setDisable(false);
            this.fieldNome.requestFocus();
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


    @FXML public void comboEstadoChange(ActionEvent actionEvent) {
        try {
            Estado selectedItem = this.comboEstado.getValue();
            if(selectedItem != null) {
                var municipios = municipioService.findByEstado(selectedItem);
                comboMunicipio.setItems(FXCollections.observableArrayList(municipios));
                comboMunicipio.setVisible(true);
                comboMunicipio.setDisable(false);
            }
        }
        catch(ServiceException e) {
            e.printStackTrace();
        }
    }

    @FXML public void adicionarCursoOnAction(ActionEvent actionEvent) {
        var curso = comboCurso.getValue();
        if(curso != null && !this.listCursosInseridos.getItems().contains(curso)) {
            // Ativa a visibilidade da combo de telefones
            this.listCursosInseridos.setVisible(true);
            this.listCursosInseridos.getItems().add(curso);
        }
    }

    @FXML public void adicionarTelefoneOnAction(ActionEvent actionEvent) {
        var numero = fieldTelefone.getText();
        var tipo = comboTipoTelefone.getValue();
        var telefone = new Telefone(numero, tipo);
        if(numero != null && tipo != null && !this.listTelefonesInseridos.getItems().contains(
                telefone)) {
            this.listTelefonesInseridos.getItems().add(telefone);
            // Ativa a visibilidade da combo de telefones
            this.listTelefonesInseridos.setVisible(true);
        }
    }
}
