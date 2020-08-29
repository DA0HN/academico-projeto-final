package org.gabriel.academico.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gabriel.academico.controller.AlunoController;
import org.gabriel.academico.service.AlunoService;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class IncluirAluno extends Application {
    @Override public void start(Stage stage) {
        try {
            Parent parent;
            var view = new FXMLLoader();
            parent = view.load(new FileInputStream("src/main/resources/view/Aluno.fxml"));
            AlunoController controller = view.getController();
            controller.setPalcoOrigem(stage);
            controller.setService(new AlunoService());
            stage.setScene(new Scene(parent));
            stage.setTitle("Incluir Curso");
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
