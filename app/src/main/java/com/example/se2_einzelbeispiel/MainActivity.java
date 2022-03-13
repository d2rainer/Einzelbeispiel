// 09855612, Dagmar Rainer

package com.example.se2_einzelbeispiel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText matrikelnr;
    Button send;
    TextView answer;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verknüpfung mit Layoutfeldern
        matrikelnr = findViewById(R.id.editTextNumber);
        send = findViewById(R.id.buttonSenden);
        answer = findViewById(R.id.editTextAntwort);
        calculate = findViewById(R.id.buttonBerechnen);

        /**
         * Button send:
         * Eingegebene Matrikelnummer wird über Client an Server geschickt
         * Server schickt Antwort über Ausgabefeld
         */

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Client starten und Matrikelnummer an Server schicken
                TCPClient client = new TCPClient(matrikelnr.getText().toString());
                client.start();

                try {
                    client.join();
                } catch (Exception e) {
                    e.getStackTrace();
                }

                // Rückantwort von Server erhalten und im Textfeld ausgeben
                answer.setText(client.getAnswer());
            }
        });

        /**
         * Mod 7 meiner Matrikelnummer 09855612 = 4 => Aufgabe 4:
         * Quersumme der Matrikelnummer bilden und anschließend als Binärzahl darstellen
         */

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int number = Integer.parseInt(String.valueOf(matrikelnr));
                int sum = 0;

                try {
                    while (0 != number) {
                        sum = sum + (number % 10);
                        number = number / 10;
                    }

                    System.out.println(Integer.toBinaryString(sum));

                } catch (Exception e) {
                    System.out.println("Fehler bei Berechnung der Matrikelnummer: " + e);
                }
            }
        });
    }
}