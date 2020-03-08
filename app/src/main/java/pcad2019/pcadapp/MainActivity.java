package pcad2019.pcadapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(pcad2019.pcadapp.MainActivity.this);
        builder.setMessage("Are you sure? \nYou gonna be disconnected..")
                .setPositiveButton("YES",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        pcad2019.pcadapp.MainActivity.super.onBackPressed();
                    }
                }).setNegativeButton("NO",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) { }
        });
        builder.create().show();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    public void locateMe (View view) {
        EditText positionText = (EditText) findViewById(R.id.positionText);
        if (!positionText.getText().toString().isEmpty()) {
            String url = "https://www.google.it/maps/place/"+positionText.getText().toString();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            this.startActivity(browserIntent);
        }
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
        String position = positionText.getText().toString();
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
