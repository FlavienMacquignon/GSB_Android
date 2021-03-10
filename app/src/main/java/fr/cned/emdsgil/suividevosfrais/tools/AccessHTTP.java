package fr.cned.emdsgil.suividevosfrais.tools;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AccessHTTP extends AsyncTask<String, Integer, Long> {

    public String retour="";
    public AsyncResponse delegate=null;
    private String parametres= "";

    public AccessHTTP(){
        super();
    }
    /**
     * Construction de la chaîne de paramètres à envoyer en POST au serveur
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur) {
        try {
            if (parametres.equals("")) {
                parametres = URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8");
            }else{
                parametres += "&" + URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Long doInBackground(String... urls) {

        System.setProperty("http.keepAlive", "false");
        PrintWriter writer = null;
        BufferedReader reader = null;
        HttpURLConnection connexion = null;
        try {
            URL url = new URL(urls[0]);
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setDoOutput(true);
            connexion.setRequestMethod("POST");
            connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connexion.setFixedLengthStreamingMode(parametres.getBytes().length);
            writer = new PrintWriter(connexion.getOutputStream());
            writer.print(parametres);
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            retour = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                writer.close();
            }catch(Exception e){}
            try{
                reader.close();
            }catch(Exception e){}
        }
        return null;
    }
}
