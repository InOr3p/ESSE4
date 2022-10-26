package mat.unical.it.progettouid2022.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class nextExamInformationPageController {
    @FXML
    private Label iconLabel;

    @FXML
    private Label cfuLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        if(!StudenteCorrente.getInstance().getEsamiSenzaVoto().isEmpty()){
            Esame nextExam = (Esame) StudenteCorrente.getInstance().getEsamiSenzaVoto().get(0);
            nameLabel.setText(nextExam.nome());
            dateLabel.setText(nextExam.data());
            cfuLabel.setText(nextExam.CFU());
        }
        else {
            nameLabel.setText("Nessun esame prenotato");
            dateLabel.setText(" ");
            cfuLabel.setText(" ");
        }
        FontIcon icon = new FontIcon("mdi2b-book-education-outline");
        icon.setIconSize(60);
        icon.getStyleClass().add("iconButton");
        iconLabel.setGraphic(icon);

        anchorPane.getStyleClass().add("nextExamLabel");
    }

    @FXML
    void paneClicked(MouseEvent event) throws IOException {
        if(!StudenteCorrente.getInstance().getEsamiSenzaVoto().isEmpty()) {
            Stats.getInstance().setIndiceEsameCorrente(0);
            SceneHandler.getInstance().createExamScene();
        }
        else SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING, "Non è presente alcun esame da sostenere", "Attenzione");
    }
}
