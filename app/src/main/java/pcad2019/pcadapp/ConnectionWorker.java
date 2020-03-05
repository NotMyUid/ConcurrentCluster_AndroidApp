package pcad2019.pcadapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by NotMyUid on 05/03/20.
 */

public class ConnectionWorker extends AsyncTask<String,String,Boolean> {

    private Activity activity;
    private AndroidClient client;
    private String command;
    private String position;
    private String search;

    public ConnectionWorker(Activity activity, AndroidClient client, String command, String position, String search) {
        this.activity = activity;
        this.client = client;
        this.command = command;
        this.position = position;
        this.search = search;
    }


    @Override
    protected Boolean doInBackground(String... strings) { // in base al comando specificato  nel costruttore il thread esegue una delle operazioni
        switch (command) {
            case "search":
                return client.send(position,search);
            //case "mostFrequents":
                //return client.mostFrequents(position);
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void onPostExecute(Boolean result){
        switch(command) {
            case "search": {
                if (result) {
                    Toast.makeText(activity, "HERE WE ARE ;-)", Toast.LENGTH_SHORT).show();
                    //activity.finish();
                }
                else {
                    Toast.makeText(activity, "MM.. Something went wrong :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            /*case "mostFrequents": {
                if (result) {
                    Toast.makeText(activity, "Utente cancellato", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
                else { Toast.makeText(activity, "Impossibile cancellare utente", Toast.LENGTH_SHORT).show();}
                break;
            }*/
        }
    }
}