package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;
import mat.unical.it.progettouid2022.model.ImmagineCorrente;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.model.oggettoSynchronized;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class loginSceneController {
    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView logo;

    @FXML
    void registerClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createRegisterScene();
    }

    @FXML
    void resetPasswordClicked(MouseEvent event) throws IOException {
        if(!emailField.getText().equals("")) {
            StudenteCorrente.getInstance().setEmailToReset(emailField.getText());
            SceneHandler.getInstance().createResetPasswordScene();
        }

        else SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Inserire l'email associata all'account", "Errore");
    }

    @FXML
    void loginClicked(MouseEvent event) throws IOException, ConnectionException, InterruptedException {
        String email = emailField.getText();
        String password = PasswordField.getText();

        if(Client.getInstance().login(email, password) != null) {
            Client.getInstance().get("studente", success -> {
                JSONObject studente = success.result();
                JSONArray arrayStudente = studente.getJSONArray("studente");
                JSONObject obj = new JSONObject();
                for (int i = 0; i < arrayStudente.length(); i++) {
                    obj = arrayStudente.getJSONObject(i);
                }
                StudenteCorrente.getInstance().setStudenteOBJ(obj);
                StudenteCorrente.getInstance().setElement_id(obj.getString("element_id"));
                Client.getInstance().retrieveFile(StudenteCorrente.getInstance().getStudenteOBJ().getString("fileId"), success2 -> {
                    byte[] a = success2.fileContent();
                    Image img = new Image(new ByteArrayInputStream(a));
                    ImmagineCorrente.getInstance().setImg(img);
                }, err2 -> err2.printStackTrace());
            }, err -> err.printStackTrace());

            synchronized (oggettoSynchronized.getInstance()) {
                oggettoSynchronized.getInstance().wait();
            }

            try {
                SceneHandler.getInstance().createHomePageStudenteScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        else SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR,"Email o password errata.","Errore");
    }
}



