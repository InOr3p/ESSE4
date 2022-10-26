package mat.unical.it.progettouid2022.model;

public record Esame(String voto, String nome, String professori, String CFU, String data, String note) {
    @Override
    public String toString() {
        return  voto + ";" + nome + ";" + professori + ";" + CFU + ";" + data + ";" + note;
    }
}
