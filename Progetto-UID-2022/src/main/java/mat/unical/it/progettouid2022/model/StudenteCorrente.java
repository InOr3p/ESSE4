package mat.unical.it.progettouid2022.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class StudenteCorrente {

   private static StudenteCorrente instance = new StudenteCorrente();
   private StudenteCorrente() {}

   public static StudenteCorrente getInstance(){
       return instance;
   }
   private JSONObject studente;
   private ObservableList<Esame> esamiConVoto;
   private ObservableList<Esame> esamiSenzaVoto;

   private ObservableList<Esame> esamiTotali;  // quando si vuole cancellare un esame, lo si sceglie da qui
   private String element_id;

   private String emailToReset;

    public ObservableList getEsamiConVoto() {
        return esamiConVoto;
    }
    public ObservableList getEsamiSenzaVoto() {
        return esamiSenzaVoto;
    }
    public ObservableList<Esame> getEsamiTotali() {
        return esamiTotali;
    }

    public String getEmailToReset() { return emailToReset; }

    public void setEmailToReset(String emailToReset) { this.emailToReset = emailToReset; }

    public String getElement_id() { return element_id;}
    public JSONObject getStudenteOBJ() {
        return studente;
    }
    public void setStudenteOBJ(JSONObject studente) {
        this.studente = studente;
    }
    public void setElement_id(String element_id){this.element_id = element_id; }
    public void aggiornaEsami() {
        esamiConVoto = FXCollections.observableArrayList();
        esamiSenzaVoto = FXCollections.observableArrayList();

        JSONArray js_esami = studente.getJSONArray("esami");
        for (int i = 0; i < js_esami.length(); i++) {
            JSONObject singolo_esame = js_esami.getJSONObject(i);
            String voto = singolo_esame.getString("voto");
            String nome = singolo_esame.getString("nome");
            String cfu = singolo_esame.getString("CFU");
            String data = singolo_esame.getString("data");
            String prof = singolo_esame.getString("professori");
            String note = singolo_esame.getString("note");
            Esame e = new Esame(voto, nome, prof, cfu, data, note);
            if (voto.equals("0")){
                esamiSenzaVoto.add(e);
                sort();
            }
            else esamiConVoto.add(e);
        }

        esamiTotali = FXCollections.observableArrayList(esamiConVoto);
        for(Esame e: esamiSenzaVoto)
            esamiTotali.add(e);
    }

    public void sort(){
        esamiSenzaVoto.sort((o1, o2) -> {
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
    }

    public boolean eliminaEsame(Esame e) throws InterruptedException {
        if(e == null) return false;

        if(e.voto().equals("0")) this.esamiSenzaVoto.remove(e);

        else this.esamiConVoto.remove(e);

        this.esamiTotali.remove(e);


        JSONArray arrayEsami = studente.getJSONArray("esami");
        for(int i = 0; i < arrayEsami.length(); ++i) {
            JSONObject esameObj = (JSONObject) arrayEsami.get(i);
            if(esameObj.get("nome").equals(e.nome()) && esameObj.get("data").equals(e.data()) && esameObj.get("CFU").equals(e.CFU()) && esameObj.get("voto").equals(e.voto())) {
                arrayEsami.remove(i);
                break;
            }
        }

        Client.getInstance().update("studente", StudenteCorrente.getInstance().getElement_id(), studente,
                ref -> {},
                er -> er.printStackTrace());

        synchronized (oggettoSynchronized.getInstance()) {
            oggettoSynchronized.getInstance().wait();
        }
        return true;
    }

    public void inserisciEsame(Esame e) throws Exception {
        JSONObject esame_obj = JSONUtil.toJSON(e);
        JSONArray arrayEsami = studente.getJSONArray("esami");
        arrayEsami.put(esame_obj);
        studente.put("esami", arrayEsami);

        Client.getInstance().update("studente", StudenteCorrente.getInstance().getElement_id(), studente,
                succ -> StudenteCorrente.getInstance().setStudenteOBJ(studente),
                err -> err.printStackTrace());

        synchronized (oggettoSynchronized.getInstance()) {
            oggettoSynchronized.getInstance().wait();
        }
    }
}
