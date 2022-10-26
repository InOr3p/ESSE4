package mat.unical.it.progettouid2022.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.model.*;
import mat.unical.it.progettouid2022.view.SceneHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class addExamPageController {
    private String [] array = {"18","19","20","21","22","23","24","25","26","27","28","29","30","30L"};
    private List voti = new ArrayList<>(List.of(array)); //arraylist contenente array di voti
    @FXML
    private ComboBox<?> votoComboBox;

    @FXML
    private Label votoLabel;

    @FXML
    private TextField cfuField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea notesArea;

    @FXML
    private TextField profField;

    @FXML
    private TableView<Prof> profTable;

    @FXML
    public TableColumn<Prof, String> profColumn;

    private ObservableList<Prof> professori = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        profTable.setItems(professori);
        profColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        votoComboBox.setVisible(false);
        votoLabel.setVisible(false);
        votoComboBox.setItems(FXCollections.observableList(voti));
    }

    @FXML
    void actionPerformed(ActionEvent event) {  // controlla che la data inserita sia passata o futura e in base a questo, rende visible il votoComboBox
        LocalDate data_corrente = LocalDate.now();
        LocalDate data_scelta = datePicker.getValue();
        if(data_scelta.isBefore(data_corrente)){
            votoLabel.setVisible(true);
            votoComboBox.setVisible(true);
        }
        else if(data_scelta.isAfter(data_corrente)){
            votoLabel.setVisible(false);
            votoComboBox.setVisible(false);
        }
    }

    @FXML
    void addButtonClicked(MouseEvent event) throws Exception {
        if(datePicker.getValue() == null)
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Data dell'esame mancante", "Errore");
        else {
            String voto = "0";
            if (votoComboBox.isVisible()) {
                voto = (String) votoComboBox.getValue();
            }
            String nomeEsame = nameField.getText();
            String prof = "";
            for (int i = 0; i < professori.size(); i++){
                prof += professori.get(i).toString() + "; ";
            }
            String cfu = cfuField.getText();
            String data = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String note = notesArea.getText();

            Pattern pattern = Pattern.compile("\\d+");

            if(votoComboBox.isVisible() && votoComboBox.getValue() == null)
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Voto mancante", "Errore");

            else if((!pattern.matcher(cfu).matches() || Integer.parseInt(cfu) > 12 || Integer.parseInt(cfu) <= 0)) {
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Inserire un numero di CFU compreso tra 1 e 12", "Errore");
            }
            else if((Integer.parseInt(cfu) + Stats.getInstance().getCfu()) > 180)
                SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING, "Puoi ottenere al più 180 CFU", "Attenzione");

            else if(!nomeEsame.equals("") && !professori.isEmpty() && !cfu.equals("")) {
                Esame newExam = new Esame(voto, nomeEsame, prof, cfu, data, note);

                StudenteCorrente.getInstance().inserisciEsame(newExam);
                StudenteCorrente.getInstance().aggiornaEsami();
                Stats.getInstance().aggiornaStats();
                SceneHandler.getInstance().closeExamStage();
                SceneHandler.getInstance().createExamPageScene();
            }

            else SceneHandler.getInstance().createAlertScene(Alert.AlertType.ERROR, "Compilare tutti i campi", "Errore");
        }
    }

    @FXML
    void addProfButtonClicked(MouseEvent event) {  // + button
        String nome = profField.getText();
        if(!nome.equals("")) {
            Prof prof = new Prof(nome);
            professori.add(prof);
            profField.setText("");
        }
    }

    @FXML
    void deleteProfButtonClicked(MouseEvent event) {  // - button
        if(!professori.isEmpty()) {
            professori.remove(professori.size() - 1);
        }
    }

    @FXML
    void cancelButtonClicked(MouseEvent event) {
        SceneHandler.getInstance().closeExamStage();
    }
}