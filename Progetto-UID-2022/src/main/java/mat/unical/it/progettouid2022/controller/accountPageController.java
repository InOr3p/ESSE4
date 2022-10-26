package mat.unical.it.progettouid2022.controller;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;

import mat.unical.it.progettouid2022.client.util.JSONUtil;
import mat.unical.it.progettouid2022.model.ImmagineCorrente;
import mat.unical.it.progettouid2022.model.Studente;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.model.oggettoSynchronized;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;


public class accountPageController {
    @FXML
    private Button modifyButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField cdsField;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField universityField;

    @FXML
    private TextField universityIDField;

    @FXML
    private TextField yearField;

    @FXML
    private AnchorPane menuRoot;


    @FXML
    void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader.load());
        setLabels();
        loadStyle("textFieldAccount");
    }

    @FXML
    void logoutButtonClicked(MouseEvent event) throws IOException, ConnectionException {
        Client.getInstance().logout();
        ImmagineCorrente.getInstance().setFile(null);
        ImmagineCorrente.getInstance().setImg(null);  // svuoto l'immagine dell'account corrente
        SceneHandler.getInstance().createLoginScene();
    }

    @FXML
    void credentialsButtonClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createModifyCredentialsScene();
    }

    @FXML
    void modifyButtonClicked(MouseEvent event) {
        FontIcon newIcon = new FontIcon("mdi2p-pencil-circle");
        newIcon.setIconSize(25);
        newIcon.getStyleClass().add("iconButton");
        modifyButton.setGraphic(newIcon);
        setTextFields(true);
        loadStyle("textFieldAccount2");
    }

    @FXML
    void saveButtonClicked(MouseEvent event) throws Exception {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/colors.css")));
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/style.css")));
        a.setHeaderText("Sei sicuro di voler modificare l'account?");
        a.showAndWait();

        if(a.getResult() == ButtonType.OK) {
            String elementId = StudenteCorrente.getInstance().getElement_id();
            String matricola = universityIDField.getText();
            String nome = nameField.getText();
            String cognome = surnameField.getText();
            String uni = universityField.getText();
            String cds = cdsField.getText();
            String anno = yearField.getText();
            String fileId = StudenteCorrente.getInstance().getStudenteOBJ().getString("fileId");
            String email = (String) StudenteCorrente.getInstance().getStudenteOBJ().get("email");
            Studente stud = new Studente(elementId, matricola, nome, cognome, uni, cds, anno, fileId, email);
            JSONObject studente = JSONUtil.toJSON(stud);
            JSONArray esami_stud = StudenteCorrente.getInstance().getStudenteOBJ().getJSONArray("esami");
            studente.put("esami", esami_stud);

            Client.getInstance().update("studente", elementId, studente,
                    reference -> { StudenteCorrente.getInstance().setStudenteOBJ(studente);},
                    er -> {er.printStackTrace();});
            synchronized (oggettoSynchronized.getInstance()) {
                oggettoSynchronized.getInstance().wait();
            }
        }
        SceneHandler.getInstance().createAccountScene();
    }

    @FXML
    void cancelButtonClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createAccountScene();
    }

    private void loadStyle(String style){
        nameField.getStyleClass().add(style);
        surnameField.getStyleClass().add(style);
        universityIDField.getStyleClass().add(style);
        universityField.getStyleClass().add(style);
        yearField.getStyleClass().add(style);
        cdsField.getStyleClass().add(style);
    }

    private void setTextFields(boolean b) {
        nameField.setEditable(b);
        surnameField.setEditable(b);
        universityIDField.setEditable(b);
        universityField.setEditable(b);
        yearField.setEditable(b);
        cdsField.setEditable(b);
        saveButton.setVisible(b);
        cancelButton.setVisible(b);
    }

    private void setLabels() {
        JSONObject studente = StudenteCorrente.getInstance().getStudenteOBJ();
        cdsField.setText(studente.getString("cds"));
        nameField.setText(studente.getString("nome"));
        surnameField.setText(studente.getString("cognome"));
        universityField.setText(studente.getString("uni"));
        universityIDField.setText(studente.getString("matricola"));
        yearField.setText(studente.getString("anno"));
        imageView.setImage(ImmagineCorrente.getInstance().getImg());
        FontIcon icon = new FontIcon("mdi2p-pencil-circle-outline");
        icon.setIconSize(25);
        icon.getStyleClass().add("iconButton");
        modifyButton.setGraphic(icon);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
    }
}
