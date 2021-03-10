package fr.cned.emdsgil.suividevosfrais;

import org.json.JSONArray;

import fr.cned.emdsgil.suividevosfrais.tools.AccessHTTP;
import fr.cned.emdsgil.suividevosfrais.tools.AsyncResponse;

public class AcessDistant implements AsyncResponse {

    public static final String SERVERADRESS="https://flavien.educationhost.cloud:3306";

    @Override
    public void processFinish(String output) {
        String[] message = output.split("%");
        if (message.length>1){
            if(message[0].equals("done")){
                // TODO afficher un message toast ici
            }
            else{
                 // TODO afficher un toast "une erreur est survenue, veuilleur contacter votre administrateur système (avec mes infos de contact)
            }
            // TODO Attention, il faut également prévoir un retour pour l'identification

        }
    }

    public void envoi(String operation, JSONArray lesdonneesJSON){
        AccessHTTP accessDonnees= new AccessHTTP();
        accessDonnees.delegate= this;
        accessDonnees.addParam("operation", operation);
        accessDonnees.addParam("lesdonnes", lesdonneesJSON.toString());
        accessDonnees.execute(SERVERADRESS);
    }
}
