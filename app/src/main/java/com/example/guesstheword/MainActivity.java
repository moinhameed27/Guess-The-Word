package com.example.guesstheword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Declaring Array of String
    String[] places = new String[]
            {
                    "Paris", "London", "NewYork", "Tokyo", "Rome", "Sydney", "Istanbul", "Dubai", "Moscow", "Beijing", "Quetta",
                    "Cairo", "Rio", "Berlin", "Amsterdam", "LosAngeles", "Mumbai", "BuenosAires", "Barcelona", "Seoul", "Bangkok",
                    "Vienna", "Toronto", "Madrid", "Singapore", "Venice", "Lisbon", "SanFrancisco", "Sargodha", "Sukkur", "Larkana",
                    "Melbourne", "Montreal", "Shanghai", "CapeTown", "Stockholm", "Dublin", "Helsinki", "KualaLumpur", "Faisalabad",
                    "Oslo", "Copenhagen", "Budapest", "Santiago", "Lima", "Riyadh", "Jakarta", "AbuDhabi", "Jhang", "Gujrat", "Kasur",
                    "Delhi", "Nairobi", "Manila", "Auckland", "Doha", "Bogota", "Riyadh", "Oslo", "Nairobi", "Agra", "Pune", "Surat",
                    "SanDiego", "Houston", "Miami", "Dallas", "Phoenix", "Denver", "Boston", "Atlanta", "Indore", "Delhi", "Lucknow",
                    "WashingtonDC", "LasVegas", "Honolulu", "Vancouver", "Kolkata", "Okara", "Islamabad", "Multan", "Bangalore",
                    "Mumbai", "Karachi", "Istanbul", "Tehran", "Baghdad", "Riyadh", "Kabul", "Dhaka", "Lahore", "Bangkok", "Jakarta"
            };
    String day;
    Random random;

    TextView txtCorrectAnswer, txtRightAnswer, txtQuestion;
    EditText etUserInput;
    Button btCheck, btShow, btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        txtQuestion = findViewById(R.id.txtQuestion);

        etUserInput = findViewById(R.id.etUserInput);

        btCheck = findViewById(R.id.btCheck);
        btShow = findViewById(R.id.btShow);
        btNext = findViewById(R.id.btNext);

        random = new Random();

        day = places[random.nextInt(places.length)];
        txtQuestion.setText(mixWords(day));

        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUserInput.getText().toString().equalsIgnoreCase(day))
                {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.correct_dialog);
                    Button btHide = dialog.findViewById(R.id.btContinue);
                    dialog.show();

                    btHide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            etUserInput.setText("");
                            day = places[random.nextInt(places.length)];
                            txtQuestion.setText(mixWords(day));
                            txtRightAnswer.setVisibility(View.INVISIBLE);
                            txtCorrectAnswer.setVisibility(View.INVISIBLE);
                            dialog.dismiss();
                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this, "You are Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = places[random.nextInt(places.length)];
                txtQuestion.setText(mixWords(day));

                etUserInput.setText("");
                txtRightAnswer.setVisibility(View.INVISIBLE);
                txtCorrectAnswer.setVisibility(View.INVISIBLE);
            }
        });

        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCorrectAnswer.setVisibility(View.VISIBLE);
                txtRightAnswer.setVisibility(View.VISIBLE);

                txtRightAnswer.setText(day);
            }
        });

    }


    private String mixWords(String word)
    {
        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed = "";

        for(String i : words)
        {
            mixed += i;
        }
        return mixed;
    }
}