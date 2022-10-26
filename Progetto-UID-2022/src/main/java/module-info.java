module mat.unical.it.progettouid2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    requires java.desktop;
    requires org.kordamp.ikonli.javafx;
    requires javafx.swing;
    requires kernel;
    requires layout;
    requires io;
    requires com.calendarfx.view;

    opens mat.unical.it.progettouid2022 to javafx.fxml;
    exports mat.unical.it.progettouid2022;
    opens mat.unical.it.progettouid2022.controller to javafx.fxml;
    exports mat.unical.it.progettouid2022.controller;
}