package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.io.IOException;

public class examLabelController {  // singola label di tabella "Prossimi esami" in home page

    private int idEsame;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private void initialize(){
        idEsame = Stats.getInstance().getIndiceEsameCorrente();  // indice di label
        Esame exam = (Esame) StudenteCorrente.getInstance().getEsamiSenzaVoto().get(idEsame);
        dateLabel.setText(exam.data());
        dateLabel.setStyle("-fx-text-fill: white");
        nameLabel.setText(exam.nome());
        anchorPane.getStyleClass().add("examLabel");
    }
    @FXML
    void paneClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextSelectedExam(nameLabel.getText());
        Stats.getInstance().setIndiceEsameCorrente(idEsame);
        SceneHandler.getInstance().createExamScene();
    }
}
