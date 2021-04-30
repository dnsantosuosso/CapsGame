// Student Name: Diego Santosuosso
// This lab was done individually

package com.example.capsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DEBUG:CapsActivity";
    private Game game; // To hold the game instance
    private String question; // To hold the last-posed question
    private String answer; // To hold the answer of the last-posed question
    private int score; // To hold the current score
    private int qNum = 1; // To hold the number of the last-posed question
    private String logEntry;
    private ToneGenerator toneGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");
        game = new Game();
        toneGenerator= new ToneGenerator(AudioManager.STREAM_ALARM,100);
        score = 0;
        qNum = 1;
        ask();
        Log.d(TAG, "ask method called");
    }



    private void ask()
    {
        Log.d(TAG, "ask running");
//        String[] a = game.qa().split("\n",2);
        String[] a = game.qa().split("[\\n]+");
        this.question = a[0];
        this.answer = a[1];

        ((TextView) findViewById(R.id.question)).setText(this.question);

        //implementing the ask so that it retrieves the question from the model; sets the the attributes of the
        //activity accordingly; and displays the questions to the user.

    }

    public void onDone(View v)
    {
        Log.d(TAG, "onDone clicked");
        if(qNum == 10)
        {
            finish();
        }
        else
        {
            String result = ((EditText) findViewById(R.id.answer)).getText().toString().trim();
            if (result.equalsIgnoreCase(this.answer))
            {
                //correct answer
                toneGenerator.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,200);
                score ++;
            }

            toneGenerator.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT,100);
            logEntry = "Q# " + qNum + ": " + question + "\n";
            logEntry += "Your answer: " + result.toUpperCase() + "\n";
            logEntry += "Correct Answer: " + answer + "\n";


            qNum++;

            String scoreResult = "Score " + this.score;
            ((TextView) findViewById(R.id.score)).setText(scoreResult);
            TextView logBox = (TextView) findViewById(R.id.log);

            String history = logBox.getText().toString();
            logBox.setText(logEntry + "\n");
            logBox.append(history);

            if(qNum == 10)
            {
                String gameFinished = "GAME OVER!";
                ((TextView) findViewById(R.id.qNum)).setText(gameFinished);
                ((Button) findViewById(R.id.done)).setEnabled(false);
            }
            else
            {
                String qNum = "Q# " + this.qNum;
                ((TextView) findViewById(R.id.qNum)).setText(qNum);
                String scored = "Score " + this.score;
                ((TextView) findViewById(R.id.score)).setText(scored);
                ask();

            }
        }
    }
}