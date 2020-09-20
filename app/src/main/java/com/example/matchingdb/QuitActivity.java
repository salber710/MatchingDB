package com.example.matchingdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuitActivity extends AppCompatActivity {

    private Button buttonContinue;
    private Button buttonCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit);

        buttonContinue = (Button) findViewById(R.id.continue_quit);
        buttonCancel = (Button) findViewById(R.id.cancel_quit);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuitActivity.this, TriviaActivity.class));
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuitActivity.this, MainActivity.class));
            }
        });
    }
}