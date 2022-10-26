package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import mat.unical.it.progettouid2022.model.ImmagineCorrente;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import org.json.JSONObject;

public class InformazioniStudenteController {

    @FXML
    private Label studentName;

    @FXML
    private Label universityInformation;

    @FXML
    private ImageView userImage;


    @FXML
    void initialize() {
        universityInformation.setStyle("-fx-text-fill: white");
        JSONObject studente = StudenteCorrente.getInstance().getStudenteOBJ();
        String nome = studente.getString("nome");
        String cognome = studente.getString("cognome");
        studentName.setText(nome+" "+cognome);
        String universita = studente.getString("uni");
        String cds = studente.getString("cds");
        String anno = studente.getString("anno");
        universityInformation.setText(universita + " - " + cds + " - " + anno + "° anno");
        userImage.setImage(ImmagineCorrente.getInstance().getImg());
    }
}
