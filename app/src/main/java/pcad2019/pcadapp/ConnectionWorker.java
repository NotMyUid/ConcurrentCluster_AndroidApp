package pcad2019.pcadapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by giorgiodelzanno on 09/03/19.
 */

public class ConnectionWorker extends AsyncTask<String,String,Boolean> {

    private Activity activity;
    private AndroidClient client;
    private String command;
    private String id;
    private String name;
    private String surname;

    public ConnectionWorker(Activity activity, AndroidClient client, String command, String id, String name, String surname) {
        this.activity = activity;
        this.client = client;
        this.command = command;
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    @Override
    protected Boolean doInBackground(String... strings) { // in base al comando specificato  nel costruttore il thread esegue una delle operazioni
        switch (command) {
            case "signIn":
                return client.signIn(id, name, surname);
            case "remove":
                return client.removeUser();
            case "logIn": {
                return client.logIn(id);
            }
            case "left": {
                return client.sndmsg(id,"left");
            }
            case "right": {
                return client.sndmsg(id,"right");
            }
            case "up": {
                return client.sndmsg(id,"up");
            }
            case "down": {
                return client.sndmsg(id,"down");
            }
            case "logOut":
                return client.logOut();
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void onPostExecute(Boolean result){
        switch(command) {
            case "signIn": {
                if (result) {
                    Toast.makeText(activity, "Registrazione effettuata", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
                else {
                    Toast.makeText(activity, "Impossibile eseguire registrazione", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case "remove": {
                if (result) {
                    Toast.makeText(activity, "Utente cancellato", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
                else { Toast.makeText(activity, "Impossibile cancellare utente", Toast.LENGTH_SHORT).show();}
                break;
            }
            case "logIn": {
                if (!result) {
                    Toast.makeText(activity, "Impossibile eseguire LogIn", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra("msg1", id);
                    intent.putExtra("msg2" ,client.ip);
                    activity.startActivity(intent);
                }
                break;
            }
            case "logOut": {
                if (result) {
                    Toast.makeText(activity, "LogOut effettuato", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
                else { Toast.makeText(activity, "Impossibile eseguire LogOut", Toast.LENGTH_SHORT).show();}
                break;
            }
            case "left": {
                if (result) {
                    Toast.makeText(activity, "Left effettuato", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(activity, "Impossibile eseguire Left", Toast.LENGTH_SHORT).show();}
                break;
            }
            case "right": {
                if (result) {
                    Toast.makeText(activity, "Right effettuato", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(activity, "Impossibile eseguire Right", Toast.LENGTH_SHORT).show();}
                break;
            }
            case "up": {
                if (result) {
                    Toast.makeText(activity, "Up effettuato", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(activity, "Impossibile eseguire Up", Toast.LENGTH_SHORT).show();}
                break;
            }
            case "down": {
                if (result) {
                    Toast.makeText(activity, "Down effettuato", Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(activity, "Impossibile eseguire Down", Toast.LENGTH_SHORT).show();}
                break;
            }
        }
    }
}