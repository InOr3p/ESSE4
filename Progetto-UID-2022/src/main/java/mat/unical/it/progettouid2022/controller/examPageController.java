package mat.unical.it.progettouid2022.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.io.IOException;

public class examPageController {

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<Esame, String> CfuColumn;

    @FXML
    private TableView<Esame> ExamTable;

    @FXML
    private TableColumn<Esame, String> NameColumn;

    @FXML
    private TableColumn<Esame, String> VoteColumn;

    @FXML
    private Label averageLabel;

    @FXML
    private Label cfuLabel;

    @FXML
    private ProgressBar cfuProgressBar;

    @FXML
    private AnchorPane menuRoot;

    @FXML
    private Label weightedAverageLabel;

    @FXML
    private Label mediaLabel;

    @FXML
    private Label mediaPonderataLabel;

    @FXML
    private Label cfuL;


    @FXML
    void initialize() throws IOException {
        mediaLabel.getStyleClass().add("importantLabel");
        mediaPonderataLabel.getStyleClass().add("importantLabel");
        cfuLabel.getStyleClass().add("importantLabel");

        StudenteCorrente.getInstance().aggiornaEsami();
        Stats.getInstance().aggiornaStats();
        caricaComponenti();

        weightedAverageLabel.setText(String.valueOf(Stats.getInstance().getMediaPonderata()));
        weightedAverageLabel.setStyle("-fx-text-fill: white");
        averageLabel.setText(String.valueOf(Stats.getInstance().getMedia()));
        averageLabel.setStyle("-fx-text-fill: white");
        cfuL.setText(Stats.getInstance().getCfu() + "/180");
        cfuL.setStyle("-fx-text-fill: white");

        float cfu = (float) Stats.getInstance().getCfu();
        cfuProgressBar.setProgress(cfu/180.0);

        ExamTable.setItems(StudenteCorrente.getInstance().getEsamiConVoto());
        NameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().nome()));
        VoteColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().voto()));
        CfuColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().CFU()));
    }


    @FXML
    void deleteExamClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createDeleteExamScene();
    }

    @FXML
    void newExamClicked(MouseEvent event) throws IOException {
        SceneHandler.getInstance().createAddExamScene();
    }


    private void caricaComponenti() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(Application.class.getResource("nextExamInformation.fxml"));
        root.getChildren().add(loader1.load());
        FXMLLoader loader2 = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader2.load());
    }
}
