package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import mat.unical.it.progettouid2022.model.Stats;
import mat.unical.it.progettouid2022.view.SceneHandler;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class menuController {

    @FXML
    private HBox root;

    @FXML
    private Button accountButton;

    @FXML
    private Button calendarButton;

    @FXML
    private Button examButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button statsButton;

    @FXML
    void initialize(){
        root.getStyleClass().add("Hbox");
        accountButton.setTooltip(new Tooltip("Account"));
        calendarButton.setTooltip(new Tooltip("Calendario"));
        examButton.setTooltip(new Tooltip("Esami"));
        homeButton.setTooltip(new Tooltip("Home"));
        statsButton.setTooltip(new Tooltip("Statistiche"));


        int num = Stats.getInstance().getNextNumScene();
        if(num == 0){
            setIcon("mdi2h-home", homeButton);
            setIcon("mdi2s-school-outline", examButton);
            setIcon("mdi2c-calendar-outline", calendarButton);
            setIcon("mdi2c-chart-box-outline", statsButton);
            setIcon("mdi2a-account-circle-outline", accountButton);
        }
        if (num == 1){
            setIcon("mdi2h-home-outline", homeButton);
            setIcon("mdi2s-school", examButton);
            setIcon("mdi2c-calendar-outline", calendarButton);
            setIcon("mdi2c-chart-box-outline", statsButton);
            setIcon("mdi2a-account-circle-outline", accountButton);
        }
        if (num == 2){
            setIcon("mdi2h-home-outline", homeButton);
            setIcon("mdi2s-school-outline", examButton);
            setIcon("mdi2c-calendar", calendarButton);
            setIcon("mdi2c-chart-box-outline", statsButton);
            setIcon("mdi2a-account-circle-outline", accountButton);
        }
        if (num == 3){
            setIcon("mdi2h-home-outline", homeButton);
            setIcon("mdi2s-school-outline", examButton);
            setIcon("mdi2c-calendar-outline", calendarButton);
            setIcon("mdi2c-chart-box", statsButton);
            setIcon("mdi2a-account-circle-outline", accountButton);
        }
        if (num == 4){
            setIcon("mdi2h-home-outline", homeButton);
            setIcon("mdi2s-school-outline", examButton);
            setIcon("mdi2c-calendar-outline", calendarButton);
            setIcon("mdi2c-chart-box-outline", statsButton);
            setIcon("mdi2a-account-circle", accountButton);
        }
    }

    @FXML
    void accountButtonClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextNumScene(4);
        SceneHandler.getInstance().createAccountScene();
    }

    @FXML
    void calendarButtonClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextNumScene(2);
        SceneHandler.getInstance().createCalendarPage();
    }

    @FXML
    void examButtonClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextNumScene(1);
        SceneHandler.getInstance().createExamPageScene();
    }

    @FXML
    void homeButtonClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextNumScene(0);
        SceneHandler.getInstance().createHomePageStudenteScene();
    }

    @FXML
    void statsButtonClicked(MouseEvent event) throws IOException {
        Stats.getInstance().setNextNumScene(3);
        SceneHandler.getInstance().createStatsScene();
    }

    private void setIcon(String iconCode, Button button) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(30);
        icon.getStyleClass().add("iconButton");
        button.setGraphic(icon);
    }
}
