package org.gabriel.academico.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gabriel.academico.controller.AlunoController;
import org.gabriel.academico.controller.ProfessorController;
import org.gabriel.academico.service.ProfessorService;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author daohn on 29/08/2020
 * @project EstudoDeCaso
 */
public class IncluirProfessor extends Application {
    @Override public void start(Stage stage) {
        try {
            Parent parent;
            var view = new FXMLLoader();
            parent = view.load(new FileInputStream("src/main/resources/view/Professor.fxml"));
            ProfessorController controller = view.getController();
            controller.setStage(stage);
            controller.setService(new ProfessorService());
            stage.setScene(new Scene(parent));
            stage.setTitle("Incluir professor");
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
