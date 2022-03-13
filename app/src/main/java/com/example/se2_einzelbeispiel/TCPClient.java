package com.example.se2_einzelbeispiel;

import java.io.*;
import java.net.*;

public class TCPClient extends Thread {
    private String matrikelnr;
    private String answer;

    public TCPClient(String matrikelnr) {
        this.matrikelnr = matrikelnr;
    }

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        try {
            // erzeugen client socket und Verbindung zu Server
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            // erzeugen output stream zum socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            // erzeugen input stream
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            // erzeugen input stream vom socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //matrikelnr = inFromUser.readLine();
            // Zeile auslesen an Server
            outToServer.writeBytes(matrikelnr + '\n');
            // Zeile einlesen vom Server
            answer = inFromServer.readLine();
            //System.out.println("Answer from server: " + answer);

            clientSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            answer = "failure in connection: " + e;
        }
    }

    public String getAnswer() {
        return this.answer;
    }
}
