package mat.unical.it.progettouid2022.view;

import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import mat.unical.it.progettouid2022.model.Esame;
import mat.unical.it.progettouid2022.model.StudenteCorrente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ChartsHandler {
    private static ChartsHandler instance = new ChartsHandler();

    public static ChartsHandler getInstance() {
        return instance;
    }
    private ChartsHandler() {}

    public void setHomeChart(LineChart<?,?> chart) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Andamento voti");
        ObservableList<Esame> esamiConVoti = StudenteCorrente.getInstance().getEsamiConVoto();
        esamiConVoti.sort((o1, o2) -> {
            int returnValue = 0;
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(o1.data());
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(o2.data());
                if (date1.toInstant().isBefore(date2.toInstant())){
                    returnValue = -1;
                }
                else returnValue = 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return returnValue;
        });
        for (int i = 0; i < esamiConVoti.size(); i++){
            if(esamiConVoti.get(i).voto().equals("30L")){
                XYChart.Data dato = new XYChart.Data(esamiConVoti.get(i).data(), 30);
                series.getData().add(dato);

            } else {
                series.getData().add(new XYChart.Data(esamiConVoti.get(i).data(), Integer.valueOf(esamiConVoti.get(i).voto())));
            }
        }
        chart.getData().add(series);
    }

    public void setAverageChart(LineChart<?,?> chart) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Andamento media");

        ObservableList<Esame> esamiConVoti = StudenteCorrente.getInstance().getEsamiConVoto();
        esamiConVoti.sort((o1, o2) -> {
            int returnValue = 0;
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(o1.data());
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(o2.data());
                if (date1.toInstant().isBefore(date2.toInstant())){
                    returnValue = -1;
                }
                else returnValue = 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return returnValue;
        });

        double somma = 0, mediaAttuale = 0;
        int numEsami = 0;
        for (int i = 0; i < esamiConVoti.size(); i++){
            if(esamiConVoti.get(i).voto().equals("30L")){
                somma += 30;
            }
            else somma += Integer.parseInt(esamiConVoti.get(i).voto());

            numEsami++;
            mediaAttuale = somma / numEsami;
            XYChart.Data dato = new XYChart.Data(esamiConVoti.get(i).data(), mediaAttuale);
            series.getData().add(dato);

        }
        chart.getData().add(series);
    }

    public void setVotesDistributionChart(BarChart<?,?> chart, NumberAxis yAxis) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Distribuzione voti");

        ObservableList<Esame> esamiConVoti = StudenteCorrente.getInstance().getEsamiConVoto();
        HashMap<String, Integer> numVoti = new HashMap<>();

        for(int i = 18; i <= 30; ++i) {
            numVoti.put(String.valueOf(i), 0);
        }
        numVoti.put("30L", 0);

        for(int i = 0; i < esamiConVoti.size(); ++i) {
            int num = numVoti.get(esamiConVoti.get(i).voto()) + 1;
            numVoti.replace(esamiConVoti.get(i).voto(), num);
        }

        int maxValue = 0;
        for(int key = 18; key <= 30; ++key){
            if(numVoti.get(String.valueOf(key)) > maxValue) maxValue = numVoti.get(String.valueOf(key));
            XYChart.Data dato = new XYChart.Data(String.valueOf(key), numVoti.get(String.valueOf(key)));
            series.getData().add(dato);
        }
        if(numVoti.get("30L") > maxValue) maxValue = numVoti.get("30L");
        XYChart.Data dato = new XYChart.Data("30L", numVoti.get("30L"));
        series.getData().add(dato);

        yAxis.setUpperBound(maxValue);
        chart.setBarGap(0);
        chart.getData().add(series);
    }

    public void setCFUDistributionChart(BarChart<?,?> chart, NumberAxis yAxis) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Distribuzione CFU");

        ObservableList<Esame> esamiConVoti = StudenteCorrente.getInstance().getEsamiConVoto();
        HashMap<String, Integer> numCfu = new HashMap<>();

        for(int i = 1; i <= 12; ++i) {
            numCfu.put(String.valueOf(i), 0);
        }

        for(int i = 0; i < esamiConVoti.size(); ++i) {
            int num = numCfu.get(esamiConVoti.get(i).CFU()) + 1;
            numCfu.replace(esamiConVoti.get(i).CFU(), num);
        }
        int maxValue = 0;
        for(int key = 1; key <= 12; ++key){
            if(numCfu.get(String.valueOf(key)) > maxValue) maxValue = numCfu.get(String.valueOf(key));
            XYChart.Data dato = new XYChart.Data(String.valueOf(key), numCfu.get(String.valueOf(key)));
            series.getData().add(dato);
        }

        yAxis.setUpperBound(maxValue);
        chart.setBarGap(0);
        chart.getData().add(series);
    }
}