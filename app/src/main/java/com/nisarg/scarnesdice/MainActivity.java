package com.nisarg.scarnesdice;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView yourScore,computerScore,turnScore,winner;
    int yourTurn=0,compScore=0,finalScore=0,guess,computer,flag=0;
    ImageView diceImage;
    boolean turn=true;
    int computerNumber;
    String winnerText;
    int a[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yourScore=(TextView)findViewById(R.id.tv1);
        computerScore=(TextView)findViewById(R.id.tv2);
        diceImage=(ImageView)findViewById(R.id.i1);
        turnScore=(TextView)findViewById(R.id.tv3);
        winner=findViewById(R.id.WinnerTv);
    }

    public void onRoll(View view)
    {
        guess=(int)(Math.random()*6);
        diceImage.setImageResource(a[guess]);
        if(guess==0)
        {
            yourTurn=0;
            turnScore.setText("Turn Score : "+ yourTurn);
            if(turn==true)
            {
                onHold(view);
            }
        }

        else {
            yourTurn += guess + 1;
            turnScore.setText("Turn Score : " + yourTurn);
            System.out.println(yourTurn);
        }
    }
    public void onHold(View view)
    {
            if (turn == true) {
                finalScore += yourTurn;
                yourScore.setText("Your Score : " + finalScore);
                turn = false;
                yourTurn = 0;
                if (finalScore >= 100) {
                    winnerText = "Congratulations! ,You Win";
                    winner.setText(winnerText);
                    winner.setVisibility(View.VISIBLE);
                    flag = 1;
                }
                if (turn == false && flag != 1) {
                    computerNumber = (int) (Math.random() * 9);
                    computer = (int) (Math.random() * computerNumber);
                    while (computer != 0) {
                        onRoll(view);
                        if (yourTurn == 0) {
                            break;
                        }
                        compScore += yourTurn;
                        computer--;
                        yourTurn = 0;
                    }
                    turnScore.setText("Turn Score : 0");
                    computerScore.setText("Computer Score : " + compScore);
                    turn = true;
                    yourTurn = 0;
                }
                if (compScore >= 100 && flag != 1) {
                    winnerText = "HardLuck! ,You Lose";
                    winner.setText(winnerText);
                    winner.setVisibility(View.VISIBLE);
                    flag = 1;
                }
                if (flag == 1) {
                    onReset(view);
                }
            }

    }
    public void onReset(View view)
    {
        yourTurn=0;
        compScore=0;
        finalScore=0;
        turnScore.setText("Turn Score : "+yourTurn);
        yourScore.setText("Your Score : "+finalScore);
        computerScore.setText("Your Score : "+compScore);
        diceImage.setImageResource(a[0]);
        turn=true;
        flag=0;

    }
}
