package mat.unical.it.progettouid2022.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.model.StudenteCorrente;
import mat.unical.it.progettouid2022.view.ChartsHandler;

import java.io.IOException;

public class homePageStudenteController {

    @FXML
    public AnchorPane InformazioniStudente;

    @FXML
    private AnchorPane menuRoot;

    @FXML
    private Label cfuLabel;

    @FXML
    private ProgressBar cfuProgressBar;

    @FXML
    private Label weightedAverage;

    @FXML
    private VBox box;

    @FXML
    private LineChart<?, ?> weightedAverageChart;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label labelCfu;

    @FXML
    private Label labelMedia;

    @FXML
    private Label nextExamsLabel;

    @FXML
    void initialize() throws InterruptedException, IOException {
        nextExamsLabel.getStyleClass().add("importantLabel");
        StudenteCorrente.getInstance().aggiornaEsami();
        Stats.getInstance().aggiornaStats();
        caricaInformazioni();

        cfuLabel.setText(Stats.getInstance().getCfu() + "/180");
        cfuLabel.setStyle("-fx-text-fill: white");
        weightedAverage.setText(String.valueOf(Stats.getInstance().getMediaPonderata()));
        weightedAverage.setStyle("-fx-text-fill: white");
        float cfu = (float) Stats.getInstance().getCfu();
        cfuProgressBar.setProgress(cfu/180.0);
        ChartsHandler.getInstance().setHomeChart(weightedAverageChart);
        box.getStyleClass().add("vbox");
        labelCfu.getStyleClass().add("importantLabel");
        labelMedia.getStyleClass().add("importantLabel");
    }

    @FXML
    private void caricaInformazioni() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("InformazioniStudente.fxml"));
        InformazioniStudente.getChildren().add(loader.load());

        FXMLLoader loader2 = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader2.load());

        ObservableList esami = StudenteCorrente.getInstance().getEsamiSenzaVoto();
        for (int i = 0; i < esami.size(); i++){       // carico la tabella "prossimi esami"
            Stats.getInstance().setIndiceEsameCorrente(i);
            FXMLLoader loader3 = new FXMLLoader(Application.class.getResource("ExamLabelComponent.fxml"));
            AnchorPane pane = loader3.load();
            box.getChildren().add(pane);
        }
    }
}
