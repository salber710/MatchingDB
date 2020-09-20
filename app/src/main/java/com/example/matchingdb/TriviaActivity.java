package com.example.matchingdb;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class TriviaActivity extends AppCompatActivity {

    private TextView scoreView;
    private ImageView imageView;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    private Button buttonChoice4;
    private Button buttonQuit;

    private int score = 0;
    private File member_photos = new File("src/main/res/drawable\n" +
            "src/main/res/drawable-v24");
    private String correct_name;
    private Boolean isCounterRunning;
    private Boolean firstTime = true;

    private int timeLeftInMilliseconds = 5000;
    private CountDownTimer countDownTimer;

    //Pause timer when the user exits the app
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

       scoreView = (TextView) findViewById(R.id.score);
       imageView = (ImageView) findViewById(R.id.member_photo);
       buttonChoice1 = (Button) findViewById(R.id.choice1);
       buttonChoice2 = (Button) findViewById(R.id.choice2);
       buttonChoice3 = (Button) findViewById(R.id.choice3);
       buttonChoice4 = (Button) findViewById(R.id.choice4);
       buttonQuit = (Button) findViewById(R.id.quit) ;
       correct_name = correctAnswer();
       if (firstTime){
           updateQuestion();
           firstTime = false;
       }

       startTimer(5000,"trivia");

        //Button Listener for Option 1
        buttonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                //If the user gets the question correct, answer flashes green for 1 second
                //and score is increased by one
                if (buttonChoice1.getText() == correct_name){
                    score += 1;
                    updateScore(score);
                    buttonChoice1.setBackgroundColor(Color.parseColor("#008000"));
                    startTimer(1000,"answer");
                }
                //If the user gets the question wrong, answer flashes red for 1 second
                else{
                    buttonChoice1.setBackgroundColor(Color.parseColor("#800000"));
                    startTimer(1000,"answer");
                }
            }
        });

        //Button Listener for Option 2
        buttonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                if (buttonChoice2.getText() == correct_name){
                    score += 1;
                    updateScore(score);
                    buttonChoice2.setBackgroundColor(Color.parseColor("#008000"));
                    startTimer(1000,"answer");
                }
                else{
                    buttonChoice2.setBackgroundColor(Color.parseColor("#800000"));
                    startTimer(1000,"answer");
                }
            }
        });

        //Button Listener for Option 3
        buttonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                if (buttonChoice3.getText() == correct_name){
                    score += 1;
                    updateScore(score);
                    buttonChoice3.setBackgroundColor(Color.parseColor("#008000"));
                    startTimer(1000,"answer");
                }
                else{
                    buttonChoice3.setBackgroundColor(Color.parseColor("#800000"));
                    startTimer(1000,"answer");
                }
            }
        });

        //Button Listener for Option 4
        buttonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                if (buttonChoice4.getText() == correct_name){
                    score += 1;
                    updateScore(score);
                    buttonChoice4.setBackgroundColor(Color.parseColor("#008000"));
                    startTimer(1000,"answer");
                }
                else{
                    buttonChoice4.setBackgroundColor(Color.parseColor("#800000"));
                    startTimer(1000,"answer");
                }
            }
        });

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TriviaActivity.this, QuitActivity.class));
            }
        });
    }


    //Stars the timer
    public void startTimer(int time, final String type){
        countDownTimer = new CountDownTimer(time, 1000){
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                if (type.equals("trivia")){
                    updateQuestion();
                }
                else{
                    buttonChoice1.setBackgroundColor(Color.parseColor("#0091EA"));
                    buttonChoice2.setBackgroundColor(Color.parseColor("#0091EA"));
                    buttonChoice3.setBackgroundColor(Color.parseColor("#0091EA"));
                    buttonChoice4.setBackgroundColor(Color.parseColor("#0091EA"));
                    updateQuestion();
                }
            }
        }.start();
        isCounterRunning = true;
    }

    //Stops the timer
    //For when the user exits the app
    public void stopTimer() {
        countDownTimer.cancel();
    }

    //Updates the score
    //For when the user gets a question correct
    private void updateScore(int point){
        scoreView.setText("" + score);
    }

    //Returns the correct answer for a question
    private String correctAnswer(){
        Resources res = getResources();
        String[] member_list = res.getStringArray(R.array.array_photos);
        Random rand = new Random();
        int correct_int = rand.nextInt(member_list.length);
        String correct_name = member_list[correct_int];
        return correct_name;
    }



    //Updates the question and answers
    private void updateQuestion() {
        Resources res = getResources();
        String[] member_list = res.getStringArray(R.array.array_photos);
        String[] photos_list = member_photos.list();

        correct_name = correctAnswer();

        Random rand = new Random();
        int correct_place = 1+ rand.nextInt(5);

        ArrayList<String> randy_membs = random_members(member_list,3);

        if (correct_place == 1){
            buttonChoice1.setText(correct_name);
            buttonChoice2.setText(randy_membs.get(0));
            buttonChoice3.setText(randy_membs.get(1));
            buttonChoice4.setText(randy_membs.get(2));
        }
        else if (correct_place == 2){
            buttonChoice2.setText(correct_name);
            buttonChoice1.setText(randy_membs.get(0));
            buttonChoice3.setText(randy_membs.get(1));
            buttonChoice4.setText(randy_membs.get(2));
        }

        else if (correct_place == 3){
            buttonChoice3.setText(correct_name);
            buttonChoice2.setText(randy_membs.get(0));
            buttonChoice1.setText(randy_membs.get(1));
            buttonChoice4.setText(randy_membs.get(2));
        }

        else{
            buttonChoice4.setText(correct_name);
            buttonChoice2.setText(randy_membs.get(0));
            buttonChoice3.setText(randy_membs.get(1));
            buttonChoice1.setText(randy_membs.get(2));
        }

        String drawableName = (correct_name.replaceAll("\\s+", "")).toLowerCase();
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        imageView.setImageResource(resID);
        if (!firstTime){
            startTimer(5000,"trivia");
        }
    }


    //Returns an ArrayList of four different members
    private ArrayList<String> random_members(String[] member_list, int num){
        ArrayList<String> rand_members = new ArrayList<String>();
        Random rand = new Random();
        rand_members.add(member_list[rand.nextInt(member_list.length)]);
        int index = 0;
        while(index<num-1){
            String member = member_list[rand.nextInt(member_list.length)];
            if (!rand_members.contains(member)){
                rand_members.add(member);
                index += 1;
            }
        }
        return rand_members;
    }


}