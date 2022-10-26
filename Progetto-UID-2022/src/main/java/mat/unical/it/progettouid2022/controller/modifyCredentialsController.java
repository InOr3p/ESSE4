package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;
import mat.unical.it.progettouid2022.model.emailVerificationHandler;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.io.IOException;

public class modifyCredentialsController {

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void cancelButtonClicked(MouseEvent event) {
        SceneHandler.getInstance().closeCredentialsStage();
    }

    @FXML
    void saveButtonClicked(MouseEvent event) throws IOException, ConnectionException {
        if(showAlert()) {
            if(Client.getInstance().changeEmail(emailField.getText()) != null && Client.getInstance().sendEmailVerification()) {
                emailVerificationHandler.getInstance().setVerificationNumber(1);
                SceneHandler.getInstance().createEmailVerificationScene();
            }
            else if(!emailField.getText().equals(""))
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Impossibile cambiare email", "Errore");

            if(passwordField.getText().equals(confirmPasswordField.getText()) && passwordField.getText().length() >= 6)
                Client.getInstance().changePassword(passwordField.getText());

            else if(!passwordField.getText().equals(confirmPasswordField.getText()) || !passwordField.getText().equals(""))
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Impossibile cambiare password", "Errore");

            SceneHandler.getInstance().closeCredentialsStage();
        }
    }

    private boolean showAlert() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/colors.css")));
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/style.css")));
        a.setHeaderText("Sei sicuro di voler modificare le credenziali di accesso?");
        a.showAndWait();

        return a.getResult() == ButtonType.OK;
    }
}
