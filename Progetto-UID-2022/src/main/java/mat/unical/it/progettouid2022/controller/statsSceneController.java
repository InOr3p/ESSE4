package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.model.Stats;

import java.io.IOException;

public class statsSceneController {

    @FXML
    private ScrollPane chartsRoot;

    @FXML
    private Label maxBaseLaurea;

    @FXML
    private Label maxMediaLabel;

    @FXML
    private Label minBaseLaurea;

    @FXML
    private Label minMediaLabel;

    @FXML
    private Label maxAverage;

    @FXML
    private Label maxGraduation;

    @FXML
    private AnchorPane menuRoot;

    @FXML
    private Label minAverage;

    @FXML
    private Label minGraduation;


    @FXML
    void initialize() throws IOException {
        maxBaseLaurea.getStyleClass().add("importantLabel");
        minBaseLaurea.getStyleClass().add("importantLabel");
        maxMediaLabel.getStyleClass().add("importantLabel");
        minMediaLabel.getStyleClass().add("importantLabel");
        minAverage.setStyle("-fx-text-fill: white");
        maxAverage.setStyle("-fx-text-fill: white");
        minGraduation.setStyle("-fx-text-fill: white");
        maxGraduation.setStyle("-fx-text-fill: white");

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader.load());

        FXMLLoader loader2 = new FXMLLoader(Application.class.getResource("scrollChartsScene.fxml"));
        chartsRoot.setContent(loader2.load());

        if(Stats.getInstance().setMediaPondMin() < 18)
            minAverage.setText("18.0");
        else minAverage.setText(String.valueOf(Stats.getInstance().setMediaPondMin()));

        if(Stats.getInstance().setMediaPondMax() > 30)
            maxAverage.setText("30.0");
        else maxAverage.setText(String.valueOf(Stats.getInstance().setMediaPondMax()));

        if(Stats.getInstance().setGraduationVoteMin() < 66)
            minGraduation.setText("66");
        else minGraduation.setText(String.valueOf(Stats.getInstance().setGraduationVoteMin()));

        if(Stats.getInstance().setGraduationVoteMax() > 110)
            maxGraduation.setText("110");
        else maxGraduation.setText(String.valueOf(Stats.getInstance().setGraduationVoteMax()));
    }
}
