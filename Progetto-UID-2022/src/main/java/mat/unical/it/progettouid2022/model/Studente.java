package mat.unical.it.progettouid2022.model;



public record Studente(@client.util.Ignore String element_id, String matricola, String nome, String cognome, String uni, String cds, String anno, String fileId, String email) {

    @Override
    public String toString() {
        return element_id + ";" + matricola + ";" + nome + ";" + cognome + ";" + uni + ";" + cds + ";" + anno + ";" + fileId + ";" + email;
    }
}
