package mat.unical.it.progettouid2022.model;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Calendario {

    private Calendar calendar;
    private CalendarSource source;
    public Calendario() {
        calendar = new Calendar();
        source = new CalendarSource();
    }

    public CalendarSource caricaEsami() {
        ObservableList<Esame> esami = StudenteCorrente.getInstance().getEsamiTotali();
        for (Esame e : esami) {
            LocalDate data = LocalDate.parse(e.data());
            Entry<Esame> esame = new Entry<>(e.nome() + "\n" + e.professori());
            esame.setInterval(data);
            calendar.addEntry(esame);
        }

        source.getCalendars().clear();
        source.getCalendars().addAll(calendar);
        return source;
    }
}
