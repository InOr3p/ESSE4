package mat.unical.it.progettouid2022.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.util.JSONUtil;
import mat.unical.it.progettouid2022.model.ImmagineCorrente;
import mat.unical.it.progettouid2022.model.Studente;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.model.emailVerificationHandler;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class registerPageController {
    @FXML
    private TextField annoField;

    @FXML
    private TextField cdsField;

    @FXML
    private PasswordField checkPasswordField;

    @FXML
    private TextField cognomeField;

    @FXML
    private TextField emailField;

    @FXML
    private ImageView fotoViewer;

    @FXML
    private TextField nomeField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField uniField;

    @FXML
    private TextField matricolaField;

    @FXML
    void loadButtonClicked(MouseEvent event) {  // pulsante per caricare un'immagine
        File file = SceneHandler.getInstance().createLoadImageScene();

        if(file != null) {
            ImmagineCorrente.getInstance().setFile(file);
            Image image = new Image(ImmagineCorrente.getInstance().getFile().toURI().toString());
            ImmagineCorrente.getInstance().setImg(image);
            fotoViewer.setImage(image);
        }
        else SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING, "Foto non disponibile", "Attenzione");
    }

    @FXML
    void backButtonClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createLoginScene();
    }

    @FXML
    void registerButtonClicked(MouseEvent event) throws Exception {
        String email = emailField.getText();
        String password1 = passwordField.getText();
        String password2 = checkPasswordField.getText();
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String matricola = matricolaField.getText();
        String cds = cdsField.getText();
        String uni = uniField.getText();
        String anno = annoField.getText();

        Pattern pattern = Pattern.compile("\\d+");

        if(!pattern.matcher(anno).matches() || !pattern.matcher(matricola).matches()) {
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Anno e matricola devono essere numeri!", "Errore");
        }
        else if(ImmagineCorrente.getInstance().getFile() == null) {
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Inserire una foto", "Errore");
        }
        else if (!email.equals("") && !password1.equals("") && !password2.equals("") && !nome.equals("") &&
                !cognome.equals("") && !matricola.equals("") && !cds.equals("") && !uni.equals("") && !anno.equals("")) {

            Studente stud = new Studente(null, matricola, nome, cognome, uni, cds, anno, "0", email);
            JSONObject obj = JSONUtil.toJSON(stud);
            StudenteCorrente.getInstance().setStudenteOBJ(obj);

            if (!password1.equals(password2)) {   //se le due password (password e checkPassword) non sono uguali
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Le due password non coincidono.", "Errore");
            } else if (password1.length() < 6) {   //se la lunghezza della password è < 6
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING, "La password deve essere lunga almeno 6 caratteri!", "Attenzione");
            } else {
                Task<Boolean> t1 = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        if (Client.getInstance().register(emailField.getText(), password1) != null) {  //se la registrazione è andata a buon fine
                            Client.getInstance().sendEmailVerification();
                            return true;
                        }
                        return false;
                    }
                };
                Thread t = new Thread(t1);
                t.start();

                t1.setOnSucceeded(e -> {
                    if (t1.getValue()) {
                        try {
                            emailVerificationHandler.getInstance().setVerificationNumber(0);
                            SceneHandler.getInstance().createEmailVerificationScene();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING, "Email non valida o già associata ad un altro account", "Attenzione");
                    }
                });
            }
        }
        else SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Compilare tutti i campi", "Errore");
    }
}
