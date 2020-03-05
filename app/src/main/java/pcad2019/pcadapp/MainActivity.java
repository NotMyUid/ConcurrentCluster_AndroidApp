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

import static pcad2019.pcadapp.LoginActivity.*;
import static pcad2019.pcadapp.LoginActivity.EXTRA_MESSAGE_IP;

public class MainActivity extends AppCompatActivity {

    AndroidClient client;
    protected volatile boolean logoutDone ; // true se è stato già effettuato logout, utilizzata nel metodo onStop
    protected volatile String ID ;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Intent intent = getIntent();
            String id = intent.getStringExtra("msg1");
            ID=id;
            String ip = intent.getStringExtra("msg2");
            Toast.makeText(this, id+" "+ip, Toast.LENGTH_SHORT).show();
            client = new AndroidClient(ip, 5005 , id);

        }


        @Override
        public void onBackPressed(){
            AlertDialog.Builder builder = new AlertDialog.Builder(pcad2019.pcadapp.MainActivity.this);
            builder.setMessage("Sei sicuro di voler uscire? \nVerrà effettuato LogOut")
                    .setPositiveButton("CONFERMA",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {
                            pcad2019.pcadapp.MainActivity.super.onBackPressed(); // Chiudiamo veramente
                            //Il logout viene effettuato dal metodo onStop
                        }
                    }).setNegativeButton("ANNULLA",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {
                    // Non succede nulla
                }
            });
            builder.create().show();

        }

        @Override
        protected void onStop(){
            super.onStop();
            if (!logoutDone) { // effetuo logout solo se non è già stato fatto dal RandomWalkWorker
                ConnectionWorker worker = new ConnectionWorker(this, client, "logOut", null, null, null);
                worker.execute();

            }

        }

        public void doLeft(View view) {
            ConnectionWorker asyncTask = new ConnectionWorker(this,client, "left", ID, null, null);
            asyncTask.execute();
        }

        public void doRight(View view) {
            ConnectionWorker asyncTask = new ConnectionWorker(this,client, "right", ID, null, null);
            asyncTask.execute();
        }

        public void doUp(View view) {
            ConnectionWorker asyncTask = new ConnectionWorker(this, client, "up", ID, null, null);
            asyncTask.execute();
        }

        public void doDown(View view) {
            ConnectionWorker asyncTask = new ConnectionWorker(this, client, "down", ID, null, null);
            asyncTask.execute();
        }
}
