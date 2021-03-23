package fr.cned.emdsgil.suividevosfrais;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import fr.cned.emdsgil.suividevosfrais.tools.AccessHTTP;
import fr.cned.emdsgil.suividevosfrais.tools.AsyncResponse;

public class AcessDistant implements AsyncResponse {

    //FIXME changer l'adresse du serveur ici
    public static final String SERVERADRESS="http://192.168.0.11/controleurs/android/c_android.php";
    private static Context leContexte;

    @Override
    public void processFinish(String output) {
        String[] message = output.split("%");
        if (message.length>1){
            Toast.makeText(leContexte, message[0], Toast.LENGTH_LONG).show();
            Log.d("ProcessFinish", message[0]);
        }
    }

    public void envoi(String login, String password, String operation, String lesdonneesJSON, Context leContexte){
        this.leContexte = leContexte;
        AccessHTTP accessDonnees= new AccessHTTP();
        accessDonnees.delegate= this;
        accessDonnees.addParam("login", login);
        accessDonnees.addParam("password", password);
        accessDonnees.addParam("operation", operation);
        accessDonnees.addParam("lesDonnes", lesdonneesJSON.toString());
        accessDonnees.execute(SERVERADRESS);
        Toast.makeText(leContexte, "Envoit des données", Toast.LENGTH_LONG).show();
    }
}
