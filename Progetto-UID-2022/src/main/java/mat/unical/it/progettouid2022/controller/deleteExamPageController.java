package mat.unical.it.progettouid2022.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.SceneHandler;

public class deleteExamPageController {

    @FXML
    private TableColumn<Esame, String> cfuColumn;

    @FXML
    private TableColumn<Esame, String> dateColumn;

    @FXML
    private TableColumn<Esame, String> voteColumn;

    @FXML
    private TableView<Esame> examTable;

    @FXML
    private TableColumn<Esame, String> nameColumn;

    @FXML
    void initialize() {
        examTable.setItems(StudenteCorrente.getInstance().getEsamiTotali());
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().nome()));
        dateColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().data()));
        cfuColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().CFU()));
        voteColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().voto()));
    }

    @FXML
    void cancelButtonClicked(MouseEvent event) {
        SceneHandler.getInstance().closeExamStage();
    }

    @FXML
    void confirmButtonClicked(MouseEvent event) throws Exception {
        Esame selectedExam = examTable.getSelectionModel().getSelectedItem();
        if(StudenteCorrente.getInstance().eliminaEsame(selectedExam)) {
            Stats.getInstance().aggiornaStats();
            SceneHandler.getInstance().closeExamStage();
            SceneHandler.getInstance().createExamPageScene();
        }
        else {
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Esame non selezionato", "Errore");
        }
    }
}