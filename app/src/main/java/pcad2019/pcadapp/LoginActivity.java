package pcad2019.pcadapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_IP = "msg";
    private String IPstring ;

    private boolean checkIp(){ // controlla se è stato inserito un indirizzo ip
        if ( IPstring == null ){
            Toast.makeText(this, "MISSING IP ADRESS", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*public void doSignIn() {
        if (!checkIp()) return;
        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra("msg", IPstring);
        startActivity(intent);
    }*/

    public void insertData(View view) { // effettuta il login e se va a buon fine avvia la loginActivity
        if (!checkIp()) return;
        EditText positionText = (EditText) findViewById(R.id.positionText);
        EditText searchText = (EditText) findViewById(R.id.searchText);
        String position = positionText.getText().toString();
        String search = searchText.getText().toString();
        AndroidClient client = new AndroidClient(IPstring , 5005);
        ConnectionWorker worker = new ConnectionWorker(this,client,"search",position,search);
        worker.execute();
    }

    public void insertIp(View view) { // inserisce indirizzo IP sul quale contattare il server
        EditText ipText = (EditText) findViewById(R.id.IpText);
        IPstring = ipText.getText().toString();
        Toast.makeText(this, IPstring, Toast.LENGTH_SHORT).show();
    }
}
