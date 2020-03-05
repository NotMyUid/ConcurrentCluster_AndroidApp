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
    /*@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Intent intent = getIntent();
            String ip = intent.getStringExtra("msg");
            client = new AndroidClient(ip, 5005);
        }*/

        @Override
        protected void onStop(){
            super.onStop();
        }

        /*public void doSearch(View view) {
            ConnectionWorker asyncTask = new ConnectionWorker(this,client,);
            asyncTask.execute();
        }*/

}
