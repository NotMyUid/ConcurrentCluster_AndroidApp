package pcad2019.pcadapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
    }

    public void insertDataSend(View view) { // effettuta il login e se va a buon fine avvia la loginActivity
        if (!checkIp()) return;
        EditText positionText = (EditText) findViewById(R.id.positionText);
        EditText searchText = (EditText) findViewById(R.id.searchText);
        String position = positionText.getText().toString();
        String search = searchText.getText().toString();
        AndroidClient client = new AndroidClient(IPstring , 5005);
        ConnectionWorker worker = new ConnectionWorker(this,client,"search",position,search);
        worker.execute();
    }

    public void insertDataShow(View view) { // effettuta il login e se va a buon fine avvia la loginActivity
        if (!checkIp()) return;
        EditText positionText = (EditText) findViewById(R.id.positionText);
        String position = positionText.getText().toString();;
        AndroidClient client = new AndroidClient(IPstring , 5005);
        ConnectionWorker worker = new ConnectionWorker(this,client,"showFrequents",position,"");
        worker.execute();
    }

    public void insertIp(View view) { // inserisce indirizzo IP sul quale contattare il server
        EditText ipText = (EditText) findViewById(R.id.IpText);
        IPstring = ipText.getText().toString();
        Toast.makeText(this, IPstring, Toast.LENGTH_SHORT).show();
    }
}
