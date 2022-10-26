package mat.unical.it.progettouid2022.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import mat.unical.it.progettouid2022.client.Client;
import mat.unical.it.progettouid2022.client.ConnectionException;
import mat.unical.it.progettouid2022.client.util.JSONUtil;
import mat.unical.it.progettouid2022.model.*;

import mat.unical.it.progettouid2022.view.SceneHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class emailVerificationPageController {

    @FXML
    void confirmButtonClicked(MouseEvent event) throws IOException, ConnectionException, InterruptedException {
        if(Client.getInstance().isEmailVerified()){  //se l'email è stata verificata, allora accedo all'home page
            if(emailVerificationHandler.getInstance().getVerificationNumber() == emailVerificationHandler.firstVerification)
                confirmFirstEmail();
            else SceneHandler.getInstance().createAccountScene();
        }
        else{  //altrimenti, verifica l'email
            SceneHandler.getInstance().createAlertScene(Alert.AlertType.WARNING,"Non hai ancora verificato l'email","Attenzione");
        }
    }
    private void confirmFirstEmail() throws IOException, InterruptedException {
        File file = ImmagineCorrente.getInstance().getFile();

        Client.getInstance().uploadFile(file, file.getName().replaceAll(".*\\.", ""), ref -> {
            JSONObject StudenteOBJ = StudenteCorrente.getInstance().getStudenteOBJ();
            JSONArray esami_stud = new JSONArray();
            Studente stud = new Studente(null, StudenteOBJ.getString("matricola"), StudenteOBJ.getString("nome"), StudenteOBJ.getString("cognome"), StudenteOBJ.getString("uni"), StudenteOBJ.getString("cds"), StudenteOBJ.getString("anno"), ref.fileId(), StudenteOBJ.getString("email"));
            JSONObject obj = JSONUtil.toJSON(stud);
            obj.put("esami", esami_stud);
            StudenteCorrente.getInstance().setStudenteOBJ(obj);
            Client.getInstance().insert("studente", obj, reference -> {
                String element_id = reference.result().getJSONObject("response").getString("element_id");
                Studente newStud = new Studente(element_id, StudenteOBJ.getString("matricola"), StudenteOBJ.getString("nome"), StudenteOBJ.getString("cognome"), StudenteOBJ.getString("uni"), StudenteOBJ.getString("cds"), StudenteOBJ.getString("anno"), ref.fileId(), StudenteOBJ.getString("email"));
                JSONObject newObj = JSONUtil.toJSON(newStud);
                newObj.put("esami", esami_stud);
                StudenteCorrente.getInstance().setStudenteOBJ(newObj);
                StudenteCorrente.getInstance().setElement_id(element_id);
            }, exc -> {
                exc.printStackTrace();
            });
        }, er -> {
            er.printStackTrace();
        });

        synchronized (oggettoSynchronized.getInstance()) {
            oggettoSynchronized.getInstance().wait();
        }
        SceneHandler.getInstance().createHomePageStudenteScene();
    }
}