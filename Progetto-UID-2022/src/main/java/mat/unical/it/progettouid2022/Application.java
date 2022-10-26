package mat.unical.it.progettouid2022;

import javafx.stage.Stage;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler.getInstance().init(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}