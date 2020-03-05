package pcad2019.pcadapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by NotMyUid on 05/03/20.
 */

public class AndroidClient {
    protected String ip;
    private int port;

    public AndroidClient(String ip, int port ) {
        this.ip = ip;
        this.port = port;
    }

    //Send!
    public boolean send(String position,String search) {
        Socket serverSocket = null;
        PrintWriter output = null;
        if (position.isEmpty() || search.isEmpty()) return false;
        try {
            serverSocket = new Socket(ip, port);
            output =  new PrintWriter(serverSocket.getOutputStream(), true);
            output.println(position);
            output.println(search);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  finally {
            try {
                if(serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch(Exception ee) {
                ee.printStackTrace();
            }
        }
        return true;
    }

}