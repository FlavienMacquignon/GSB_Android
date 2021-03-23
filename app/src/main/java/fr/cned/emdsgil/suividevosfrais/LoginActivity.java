package fr.cned.emdsgil.suividevosfrais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.retour_accueil))) {
            retourActivityPrincipale() ;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retourActivityPrincipale() {
        Intent intent = new Intent(this, MainActivity.class) ;
        startActivity(intent) ;
    }

    private String hashToJson (){

        Hashtable<?, ?> monHash = (Hashtable<?, ?>) Serializer.deSerialize(this);
        Gson gson = new Gson();
        String json= gson.toJson(monHash);
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