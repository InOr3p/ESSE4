package mat.unical.it.progettouid2022.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class examSceneController {
    @FXML
    private AnchorPane menuRoot;

    @FXML
    private Button backButton;

    @FXML
    private Label cfuLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label examNameLabel;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea noteArea;

    @FXML
    private Label profLabel;

    @FXML
    private Label notesLabel;

    @FXML
    private Label labelProf;

    @FXML
    private Label labelDate;


    @FXML
    private void initialize() throws IOException {
        labelProf.getStyleClass().add("importantLabel");
        notesLabel.getStyleClass().add("importantLabel");
        examNameLabel.getStyleClass().add("importantLabel");
        labelDate.getStyleClass().add("importantLabel");
        FXMLLoader loader2 = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader2.load());
        noteArea.getStyleClass().add("textArea");
        noteArea.setEditable(false);

        caricaEsame(Stats.getInstance().getIndiceEsameCorrente());
        setIcon("mdi2a-arrow-right-bold", nextButton);
        setIcon("mdi2a-arrow-left-bold", backButton);
    }

    @FXML
    void backButtonClicked(MouseEvent event) {
        Stats.getInstance().setIndiceEsameCorrente(Stats.getInstance().getIndiceEsameCorrente() - 1);
        caricaEsame(Stats.getInstance().getIndiceEsameCorrente());
    }

    @FXML
    void nextButtonClicked(MouseEvent event) {
        Stats.getInstance().setIndiceEsameCorrente(Stats.getInstance().getIndiceEsameCorrente() + 1);
        caricaEsame(Stats.getInstance().getIndiceEsameCorrente());
    }

    void caricaEsame(int indice){
        ObservableList<Esame> esami = StudenteCorrente.getInstance().getEsamiSenzaVoto();

        if(indice == 0)
            backButton.setVisible(false);
        else
            backButton.setVisible(true);

        if(indice == (esami.size() - 1))
            nextButton.setVisible(false);
        else
            nextButton.setVisible(true);

        Esame firstExam = esami.get(indice);
        examNameLabel.setText(firstExam.nome());
        examNameLabel.getStyleClass().add("importantLabel");
        cfuLabel.setText(firstExam.CFU() + " CFU");
        cfuLabel.setStyle("-fx-text-fill: white");
        dateLabel.setText(firstExam.data());
        dateLabel.setStyle("-fx-text-fill: white");
        noteArea.setText(firstExam.note());
        noteArea.setStyle("-fx-text-fill: white");
        profLabel.setText(firstExam.professori());
        profLabel.setStyle("-fx-text-fill: white");

    }

    private void setIcon(String iconCode, Button button) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(25);
        icon.getStyleClass().add("iconButton");
        button.setGraphic(icon);
    }
}
