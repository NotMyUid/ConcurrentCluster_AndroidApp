package pcad2019.pcadapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

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
            case "showFrequents":
                return client.showFrequents(position);
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
                    String url = "http://www.google.com/#q="+search;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    activity.startActivity(browserIntent);
                }
                else {
                    Toast.makeText(activity, "MM.. Something went wrong :|", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case "showFrequents": {
                if (result) {
                    Toast.makeText(activity, "Echoing..", Toast.LENGTH_SHORT).show();
                    TextView toEdit=(TextView) activity.findViewById(R.id.echoes);
                    toEdit.setText(client.result);
                    TextView toEdit1=(TextView) activity.findViewById(R.id.echoes1);
                    toEdit1.setText(client.result1);
                    TextView toEdit2=(TextView) activity.findViewById(R.id.echoes2);
                    toEdit2.setText(client.result2);
                }
                else { Toast.makeText(activity, "MM.. Something went wrong :|", Toast.LENGTH_SHORT).show();}
                break;
            }
        }
    }
}