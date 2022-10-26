package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.io.IOException;

public class resetPasswordSceneController {

    @FXML
    void initialize() throws IOException, ConnectionException {
        Client.getInstance().resetPassword(StudenteCorrente.getInstance().getEmailToReset());
    }

    @FXML
    void confirmButtonClicked(MouseEvent event) {
        SceneHandler.getInstance().closeCredentialsStage();
    }
}
