package org.gabriel.academico.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.gabriel.academico.view.IncluirAluno;

/**
 * @author daohn on 22/08/2020
 * @project EstudoDeCaso
 */
public class Academico extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage stage) throws Exception {
        new IncluirAluno().start(stage);
    }
}
