package com.harold.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 1 => red, 2=> yellow, 0 => empty
    int activePlayer = 1;
    int[] gameState = {0,0,0,0,0,0,0,0,0,0};
    int[][] winningPositions = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9}, {1,5,9}, {3,5,7}};
    boolean gameOver = false, emptyField = true;
    int winner = 0;

    public void diveIn(View view){

        if(!gameOver){

            ImageView piece = (ImageView)view;

            // set game state
            if(gameState[Integer.parseInt((String)piece.getTag())]==0){
                gameState[Integer.parseInt((String)piece.getTag())] = activePlayer;

                // set active player
                if(activePlayer==1){
                    piece.setImageResource(R.drawable.red);
                    activePlayer = 2;
                } else{
                    piece.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                }

                // piece movement
                piece.setTranslationY(-1500);
                piece.animate().rotation(3600).translationYBy(1500);


                Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
                TextView txtWinner = (TextView) findViewById(R.id.txtWinner);

                // check winning position
                for(int[] wp : winningPositions){
                    if(gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]]==gameState[wp[2]] && gameState[wp[0]]!=0){
                        //Toast.makeText(this,gameState[wp[0]]==1 ? " RED WINS " : "YELLOW WINS", Toast.LENGTH_LONG).show();
                        winner = gameState[wp[0]];

                        // ask for play again
                        txtWinner.setText(winner==1 ? "RED WINS" : "YELLOW WINS");
                        btnPlayAgain.animate().alpha(1);
                        txtWinner.animate().alpha(1);
                        gameOver = true;
                        break;
                    }
                }

                // check draw
                if(!gameOver){
                    for(int i=1; i<gameState.length; i++){
                        if(gameState[i]==0){
                            emptyField = false;
                            break;
                        }
                    }
                    if(emptyField){
                        // draw
                        txtWinner.setText("It's a draw!");
                        btnPlayAgain.animate().alpha(1);
                        txtWinner.animate().alpha(1);
                        gameOver = true;
                    } else
                        emptyField = true;
                }
            }
        }

    }

    public void playAgain(View view){
        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        TextView txtWinner = (TextView) findViewById(R.id.txtWinner);
        for(int i=0; i<gameState.length;i++){
            gameState[i] = 0;
        }
        gameOver = false;
        winner = 0;

        btnPlayAgain.animate().alpha(0);
        txtWinner.animate().alpha(0);
        resetBoard();
    }

    private void resetBoard() {

        GridLayout grid = findViewById(R.id.gridLayout);
        for(int i=0; i<grid.getChildCount();i++){
            if(grid.getChildAt(i) instanceof ImageView){
                ((ImageView) grid.getChildAt(i)).setImageResource(0);
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
