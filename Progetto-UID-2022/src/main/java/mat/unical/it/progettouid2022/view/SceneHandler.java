package mat.unical.it.progettouid2022.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;

import java.io.File;
import java.io.IOException;

public class SceneHandler {
    private static SceneHandler instance = new SceneHandler();
    public static SceneHandler getInstance(){
        return instance;
    }
    private SceneHandler(){}

    Stage mainStage;
    Stage examStage;

    Stage credentialsStage;

    public void init(Stage stage){
        try{
            if (mainStage == null){
                mainStage = stage;
            }
            mainStage.setTitle("ESSE4");
            createLoginScene();
            mainStage.show();
        }catch(Exception e){
            e.printStackTrace();

        }
        mainStage.setOnCloseRequest(e -> {
            try {
                Client.getInstance().close(); //quando chiudo lo stage, disconnetto l'utente corrente
            } catch (IOException | ConnectionException ex) {
                throw new RuntimeException(ex);
            }
        });
}

    public void createLoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("loginScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 350, 350);
        loadGraphics(scene);
        anchorPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createHomePageStudenteScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("homePageStudenteScene.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 700, 600);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createExamPageScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("examPageScene.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 700, 600);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createRegisterScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("registrationScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 500, 700);
        loadGraphics(scene);
        anchorPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createResetPasswordScene() throws IOException {
        credentialsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("resetPasswordScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 300, 150);
        loadGraphics(scene);
        root.getStyleClass().add("pane");
        credentialsStage.setScene(scene);
        credentialsStage.setResizable(false);
        credentialsStage.requestFocus();
        credentialsStage.initModality(Modality.APPLICATION_MODAL);
        credentialsStage.show();
    }

    //schermata di verifica dell'email
    public void createEmailVerificationScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("emailVerificationScene.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 300,150);
        loadGraphics(scene);
        root.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createAccountScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("accountScene.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 700,600);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public File createLoadImageScene() {
        FileChooser chooser = new FileChooser();
        return chooser.showOpenDialog(mainStage);
    }

    public void createCalendarPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("calendarPage.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 1000,600);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createAddExamScene() throws IOException {
        examStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("addExamScene.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 500,500);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        examStage.setScene(scene);
        examStage.setResizable(false);
        examStage.requestFocus();
        examStage.initModality(Modality.APPLICATION_MODAL);
        examStage.show();
    }

    public void createDeleteExamScene() throws IOException {
        examStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("deleteExamScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane, 500,500);
        loadGraphics(scene);
        anchorPane.getStyleClass().add("pane");
        examStage.setScene(scene);
        examStage.setResizable(false);
        examStage.requestFocus();
        examStage.initModality(Modality.APPLICATION_MODAL);
        examStage.show();
    }

    public void createExamScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("examScene.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 700, 600);
        loadGraphics(scene);
        root.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);

    }

    public void createStatsScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("StatsScene.fxml"));
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 700, 600);
        loadGraphics(scene);
        borderPane.getStyleClass().add("pane");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
    }

    public void createModifyCredentialsScene() throws IOException {
        credentialsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("modifyCredentialsScene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 400, 250);
        loadGraphics(scene);
        root.getStyleClass().add("pane");
        credentialsStage.setScene(scene);
        credentialsStage.setResizable(false);
        credentialsStage.requestFocus();
        credentialsStage.initModality(Modality.APPLICATION_MODAL);
        credentialsStage.show();
    }


    public void closeCredentialsStage() { credentialsStage.close(); }
    public void closeExamStage() {
        examStage.close();
    }

    public File savePDFFile() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fc.getExtensionFilters().add(filter);
        File outputfile = fc.showSaveDialog(mainStage);
        if (outputfile != null)
            return outputfile;
        else
            return null;
    }
    public void createAlertScene(Alert.AlertType type, String text, String title){
        Alert a = new Alert(type);
        DialogPane dialogPane = a.getDialogPane();
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/colors.css")));
        dialogPane.getStylesheets().add(String.valueOf(Application.class.getResource("css/style.css")));
        a.setHeaderText(text);
        a.setTitle(title);
        a.show();
    }

    public void loadGraphics(Scene scene){
        scene.getStylesheets().add(String.valueOf(Application.class.getResource("css/colors.css")));
        scene.getStylesheets().add(String.valueOf(Application.class.getResource("css/style.css")));
    }
}