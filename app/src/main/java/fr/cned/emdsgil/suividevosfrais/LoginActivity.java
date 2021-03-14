package fr.cned.emdsgil.suividevosfrais;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("GSB: Transfert des Frais");
        loginListener(findViewById(R.id.btnLogin));
    }

    private fraisEntry [] retrieveData (){
        fraisEntry[] data= new fraisEntry[4];
        //TODO etendre l'array fraisEntry en fonction de la longueur des FraisHF

        return data;
    }

    private String hashPassword (String password){
        String hashedPassword = "";
        //TODO hash le mot de passe ici
        return hashedPassword;
    }
    private JSONArray dataToJson (fraisEntry [] dataFrais){
        JSONArray dataAsJSON = new JSONArray();
        // TODO transformer les data en JSONArray
        return dataAsJSON;
    }
    private void loginListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO ajouter un test pour déterminer si login et password sont vides
                final EditText loginfield = (EditText) findViewById(R.id.login);
                String login = String.valueOf(loginfield.getText());
                final EditText passwordField = (EditText) findViewById(R.id.passWord);
                String password = String.valueOf(passwordField.getText());
                fraisEntry[] lesFrais= retrieveData();
                JSONArray lesFraisJSON = dataToJson(lesFrais);
                String hashedPassword= hashPassword(password);
                AcessDistant acessDistant= new AcessDistant();
                acessDistant.envoi(login, hashedPassword, "ADD",  lesFraisJSON);
            }
        });
    }
}