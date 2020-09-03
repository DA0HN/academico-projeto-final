package org.gabriel.academico.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.gabriel.academico.controller.AcademicoController;
import org.gabriel.academico.controller.AlunoController;
import org.gabriel.academico.database.DatabaseException;
import org.gabriel.academico.service.AlunoService;
import org.gabriel.academico.service.ServiceException;
import org.gabriel.academico.view.IncluirAluno;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public class Academico extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage stage) {
        try {
            ValueObjectFactory.create();

            Parent parent;
            var view = new FXMLLoader();
            parent = view.load(new FileInputStream("src/main/resources/view/Academico.fxml"));
            AcademicoController controller = view.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(parent));
            stage.setTitle("Academico");
            stage.setResizable(false);
            stage.show();
        }
        catch(ServiceException | DatabaseException | IOException e) {
            e.printStackTrace();
        }
    }
}
