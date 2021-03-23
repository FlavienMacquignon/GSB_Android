package fr.cned.emdsgil.suividevosfrais;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import java.util.Hashtable;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("GSB: Transfert des Frais");
        loginListener(findViewById(R.id.btnLogin));
    }

    private String hashToJson (){

        Hashtable<?, ?> monHash = (Hashtable<?, ?>) Serializer.deSerialize(this);
        Gson gson = new Gson();
        String json= gson.toJson(monHash);
        Log.d("MonHash ************************", json);
        return json;
    }


    private void loginListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO ajouter un test pour déterminer si login et password sont vides
                final EditText loginfield = (EditText) findViewById(R.id.login);
                String login = String.valueOf(loginfield.getText());
                final EditText passwordField = (EditText) findViewById(R.id.passWord);
                String password = String.valueOf(passwordField.getText());
                String lesFraisJSON = hashToJson();
                AcessDistant acessDistant= new AcessDistant();
                acessDistant.envoi(login, password, "ADD",  lesFraisJSON, getApplicationContext());

            }
        });
    }
}