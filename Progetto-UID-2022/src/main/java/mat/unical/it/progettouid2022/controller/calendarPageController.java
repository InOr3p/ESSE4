package mat.unical.it.progettouid2022.controller;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import mat.unical.it.progettouid2022.Application;
import mat.unical.it.progettouid2022.model.Calendario;

import java.io.IOException;

public class calendarPageController {

    private Calendario calendarioEsami;  // classe del model
    @FXML
    private CalendarView calendar;
    @FXML
    private AnchorPane menuRoot;


    @FXML
    void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("menuComponent.fxml"));
        menuRoot.getChildren().add(loader.load());

        if(calendarioEsami == null)
            calendarioEsami = new Calendario();
        
        CalendarSource cs = calendarioEsami.caricaEsami();
        calendar.getCalendarSources().setAll(cs);
    }
}

