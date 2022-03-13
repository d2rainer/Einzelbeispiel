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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnr = findViewById(R.id.editTextNumber);
        send = findViewById(R.id.buttonSenden);
        answer = findViewById(R.id.editTextAntwort);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TCPClient client = new TCPClient(matrikelnr.getText().toString());
                client.start();

                try {
                    client.join();
                } catch (Exception e) {
                    e.getStackTrace();
                }
                answer.setText(client.getAnswer());
            }
        });
    }
}