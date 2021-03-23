package fr.cned.emdsgil.suividevosfrais;

import android.content.Context;
import android.widget.Toast;

import fr.cned.emdsgil.suividevosfrais.tools.AccessHTTP;
import fr.cned.emdsgil.suividevosfrais.tools.AsyncResponse;

public class AcessDistant implements AsyncResponse {

    //FIXME changer l'adresse du serveur ici
    public static final String SERVERADRESS="http://192.168.0.11/controleurs/android/c_android.php";
    private static Context leContexte;

    @Override
    public void processFinish(String output) {
        Toast.makeText(leContexte, "This is my Toast message!", Toast.LENGTH_LONG).show();
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

    public void envoi(String login, String password, String operation, String lesdonneesJSON, Context leContexte){
        this.leContexte = leContexte;
        Toast.makeText(leContexte, "This is my Toast message!", Toast.LENGTH_LONG).show();
        AccessHTTP accessDonnees= new AccessHTTP();
        accessDonnees.delegate= this;
        accessDonnees.addParam("login", login);
        accessDonnees.addParam("password", password);
        accessDonnees.addParam("operation", operation);
        accessDonnees.addParam("lesDonnes", lesdonneesJSON.toString());
        accessDonnees.execute(SERVERADRESS);
    }
}
