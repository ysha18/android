package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout goLayout, gameLayout;
    Button btnPlayAgain,btnTimer, btnScore,ans1,ans2,ans3,ans4;
    TextView txtResult,txtQuestion;
    CountDownTimer cdt;
    final int time = 11000;
    Random rand = new Random();
    String correctAnswer="0";
    int nbAttemps=0,nbCorrect=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goLayout = findViewById(R.id.goLayout);
        gameLayout = findViewById(R.id.gameLayout);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        txtResult = findViewById(R.id.txtResult);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnTimer = findViewById(R.id.btnTimer);
        btnScore = findViewById(R.id.btnScore);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);


    }

    public void start(View view){
        // visibility of the game
        goLayout.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        txtResult.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(View.VISIBLE);


        // generate question and answers
        generateQuestion();


        // start timer
        cdt = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // display countdown
                btnTimer.setText(getTime(String.valueOf(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                // Display score
            }
        }.start();
    }

    private void generateQuestion() {
        int n1 = rand.nextInt(11);
        int n2 = rand.nextInt(11);
        txtQuestion.setText(n1 + " + " + n2);

        correctAnswer = String.valueOf(n1 + n2);

        assignRandomlyAnswers(getFakeAnswers(Integer.parseInt(correctAnswer)));

    }

    public void checkCorrectAnswer(View view){
        Button btnClicked = (Button) view;
        txtResult.setVisibility(View.VISIBLE);
        nbAttemps++;
        if(btnClicked.getText().toString().equals(correctAnswer)){
            // correct
            txtResult.setText("CORRECT :)");
            nbCorrect++;
        } else{
            // false
            txtResult.setText("WRONG :(");
        }
        updateScore();
        // generate new question
        generateQuestion();
    }

    private void updateScore() {
        btnScore.setText(nbCorrect+"/"+nbAttemps);
    }

    private void assignRandomlyAnswers(List<String> allAnsw) {

        Button[] allButtons = {ans1,ans2,ans3,ans4};
        Set answerAlreadySet = new HashSet();
        Set buttonAlreadySet = new HashSet();

        boolean ok = false;

        while(!ok){
            int answ = rand.nextInt(4); // pick 1 answer
            int butt = rand.nextInt(4); // pick 1 button

           if(!answerAlreadySet.contains(answ) && !buttonAlreadySet.contains(butt)){

               answerAlreadySet.add(answ);
               buttonAlreadySet.add(butt);

               allButtons[butt].setText(allAnsw.get(answ));

//               if(ans1.getText().toString().isEmpty())
//                   ans1.setText(allAns[genOrder]);
//               else if(ans2.getText().toString().isEmpty() )
//                   ans2.setText(allAns[genOrder]);
//               else if(ans3.getText().toString().isEmpty())
//                   ans3.setText(allAns[genOrder]);
//               else if(ans4.getText().toString().isEmpty())
//                   ans4.setText(allAns[genOrder]);
           }
           if(answerAlreadySet.size() == 4 && buttonAlreadySet.size() == 4)
               ok = true;
       }

    }

    private List<String> getFakeAnswers(int result) {
        boolean ok = false;
        int gen;
        List<String> list = new ArrayList<>();
        while(!ok){
            gen = rand.nextInt(21);
            if(gen!=result && !list.contains(String.valueOf(gen))){
                list.add(String.valueOf(gen));
                if(list.size()==3){
                    list.add(String.valueOf(result));
                    ok = true;
                }
            }
        }
        return list;
    }

    private String getTime(String timeLeft) {
        String ret = String.valueOf(Integer.parseInt(timeLeft)/1000);
        return "0:" + (ret.length()==1 ? "0"+ret : ret);

    }

}
