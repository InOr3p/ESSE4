package mat.unical.it.progettouid2022.model;

import javafx.collections.ObservableList;

public class Stats {

    private static Stats instance = new Stats();

    private Stats() {
        cfu = 0;
    }

    public static Stats getInstance() {
        return instance;
    }

    private int cfu;
    private double media;
    private double mediaPonderata;
    private String nextSelectedExam;  // tiene traccia dell'esame cliccato tra gli esami non ancora sostenuti
    private int indiceEsameCorrente;  // serve per caricare gli esami non ancora sostenuti nella tabella "prossimi esami"

    private int nextNumScene = 0;

    public int getNextNumScene() { return nextNumScene; }

    public void setNextNumScene(int nextNumScene) {
        this.nextNumScene = nextNumScene;
    }

    public int getIndiceEsameCorrente() { return indiceEsameCorrente; }

    public void setIndiceEsameCorrente(int indiceEsameCorrente) { this.indiceEsameCorrente = indiceEsameCorrente; }

    public double getMediaPonderata() {
        return mediaPonderata;
    }

    public int getCfu() {
        return cfu;
    }

    public double getMedia() {
        return media;
    }

    public void setNextSelectedExam(String nextSelectedExam) { this.nextSelectedExam = nextSelectedExam; }

    public void aggiornaStats() {
        ObservableList<Esame> esami = StudenteCorrente.getInstance().getEsamiConVoto();
        float somma = 0, sommaPonderata = 0;
        int num = 0, numCfu = 0;

        for(Esame e: esami) {
            if(e.voto().equals("30L")){
                somma += 30;
                sommaPonderata += (30 * Integer.parseInt(e.CFU()));
            }
            else {
                somma += Integer.parseInt(e.voto());
                sommaPonderata += (Integer.parseInt(e.voto()) * Integer.parseInt(e.CFU()));
            }
            num++;
            numCfu += Integer.parseInt(e.CFU());
        }

        if(num != 0) {
            this.media = Math.round((somma / num) * 100) / 100.0;
            this.mediaPonderata = Math.round((sommaPonderata / numCfu) * 100) / 100.0;
            this.cfu = numCfu;
        }

        else {
            this.media = 0;
            this.mediaPonderata = 0;
            this.cfu = 0;
        }
    }

    public double setMediaPondMin() {
        double cfuMancanti = 180 - Stats.getInstance().getCfu();
        double minMedia = (18.0 * cfuMancanti + Stats.getInstance().calcolaSommaPonderata()) / 180.0;
        return Math.round(minMedia * 100) / 100.0;
    }

    public double setMediaPondMax() {
        double cfuMancanti = 180 - Stats.getInstance().getCfu();
        double maxMedia = (30.0 * cfuMancanti + Stats.getInstance().calcolaSommaPonderata()) / 180.0;
        return Math.round(maxMedia * 100) / 100.0;
    }

    public int setGraduationVoteMin() {
        int minGrad = (int) ((setMediaPondMin() * 110) / 30);
        return minGrad;
    }

    public int setGraduationVoteMax() {
        int maxGrad = (int) ((setMediaPondMax() * 110) / 30);
        return maxGrad;
    }

    private double calcolaSommaPonderata() {
        ObservableList<Esame> esami = StudenteCorrente.getInstance().getEsamiConVoto();
        double sommaPonderata = 0;

        for(Esame e: esami) {
            if(e.voto().equals("30L")){
                sommaPonderata += (30 * Integer.parseInt(e.CFU()));
            }
            else {
                sommaPonderata += (Integer.parseInt(e.voto()) * Integer.parseInt(e.CFU()));
            }
        }
        return sommaPonderata;
    }
}