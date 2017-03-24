package com.example.kombo.tictactoe2;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tournament extends AppCompatActivity {
    Button A1, A2, A3, B1, B2, B3, C1, C2, C3;
    boolean sign=true;
    int stepCounter=0;
    Drawable color;
    public String winner;
    private String player1,player2;
    private TextView name1,name2;
    private  TextView xText,oText;
    private  EditText firstPlayer,secondPlayer;
    ArrayList<Button> list=new ArrayList<>();
    int gamesPlayed=0;
    public boolean nobody=false;
    int firstPlayerScore,secondPlayerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        firstPlayerScore=secondPlayerScore=0;
        stepCounter=0;
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        A1 = (Button) findViewById(R.id.A1);
        A2 = (Button) findViewById(R.id.A2);
        A3 = (Button) findViewById(R.id.A3);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        B3 = (Button) findViewById(R.id.B3);
        C1 = (Button) findViewById(R.id.C1);
        C2 = (Button) findViewById(R.id.C2);
        C3 = (Button) findViewById(R.id.C3);
        list.add(A1);
        list.add(A2);
        list.add(A3);
        list.add(B1);
        list.add(B2);
        list.add(B3);
        list.add(C1);
        list.add(C2);
        list.add(C3);
        color=A1.getBackground();
        dialogInterface();
        mainMethod();

    }

    private void dialogInterface() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Players");
        alertDialog.setPositiveButton("OK",seterName);
        alertDialog.setNegativeButton(Tournament.this.getString(R.string.menu),seterName);
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        xText = new TextView(this);
        oText = new TextView(this);
        firstPlayer = new EditText(this);
        secondPlayer = new EditText(this);
        layout.addView(xText);
        layout.addView(firstPlayer);
        layout.addView(oText);
        layout.addView(secondPlayer);
        alertDialog.setView(layout);
        xText.setText("     X");
        oText.setText("     0");
        alertDialog.create().show();
    }

    public void mainMethod() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.A1:
                        analyse(0);
                        break;
                    case R.id.A2:
                        analyse(1);
                        break;
                    case R.id.A3:
                        analyse(2);
                        break;
                    case R.id.B1:
                        analyse(3);
                        break;
                    case R.id.B2:
                        analyse(4);
                        break;
                    case R.id.B3:
                        analyse(5);
                        break;
                    case R.id.C1:
                        analyse(6);
                        break;
                    case R.id.C2:
                        analyse(7);
                        break;
                    case R.id.C3:
                        analyse(8);
                        break;

                }

            }
        };
        A1.setOnClickListener(listener);
        A2.setOnClickListener(listener);
        A3.setOnClickListener(listener);
        B1.setOnClickListener(listener);
        B2.setOnClickListener(listener);
        B3.setOnClickListener(listener);
        C1.setOnClickListener(listener);
        C2.setOnClickListener(listener);
        C3.setOnClickListener(listener);
    }

    public void analyse(int id){
        if(sign==true){
            list.get(id).setText("X");
            sign=false;
        }else if(sign==false){
            list.get(id).setText("0");
            sign=true;
        }
        stepCounter++;
        list.get(id).setEnabled(false);
        if(stepCounter>=5) {
            winner();
        }
    }
    public void winner(){
        ///horizontal
        if((A1.getText()==A2.getText()) && (A2.getText()==A3.getText()) && (A1.getText()=="X" || A1.getText()=="0")){
            winner=A1.getText().toString();
            nobody=true;
            A1.setBackgroundColor(Color.parseColor("#7B68EE"));
            A2.setBackgroundColor(Color.parseColor("#7B68EE"));
            A3.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //waiter();
            //playerDialog();
        }
        if((B1.getText()==B2.getText()) && (B2.getText()==B3.getText()) && (B2.getText()=="X" || B2.getText()=="0")){
            winner=B1.getText().toString();
            nobody=true;
            B1.setBackgroundColor(Color.parseColor("#7B68EE"));
            B2.setBackgroundColor(Color.parseColor("#7B68EE"));
            B3.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //waiter();
            //playerDialog();
        }
        if((C1.getText()==C2.getText()) && (C2.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            winner=C1.getText().toString();
            nobody=true;
            C1.setBackgroundColor(Color.parseColor("#7B68EE"));
            C2.setBackgroundColor(Color.parseColor("#7B68EE"));
            C3.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            waiter();
            //playerDialog();
        }

        ///vertical
        if((A1.getText()==B1.getText()) && (B1.getText()==C1.getText()) && (A1.getText()=="X" || A1.getText()=="0")){
            winner=A1.getText().toString();
            nobody=true;
            A1.setBackgroundColor(Color.parseColor("#7B68EE"));
            B1.setBackgroundColor(Color.parseColor("#7B68EE"));
            C1.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //waiter();
            //playerDialog();
        }
        if((A2.getText()==B2.getText()) && (B2.getText()==C2.getText())&& (B2.getText()=="X" || B2.getText()=="0")){
            winner=A2.getText().toString();
            nobody=true;
            A2.setBackgroundColor(Color.parseColor("#7B68EE"));
            B2.setBackgroundColor(Color.parseColor("#7B68EE"));
            C2.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //waiter();
            //playerDialog();
        }
        if((A3.getText()==B3.getText()) && (B3.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            nobody=true;
            A3.setBackgroundColor(Color.parseColor("#7B68EE"));
            B3.setBackgroundColor(Color.parseColor("#7B68EE"));
            C3.setBackgroundColor(Color.parseColor("#7B68EE"));
            winner=A3.getText().toString();
            enable();
            //waiter();
            //playerDialog();
        }

        //diagonal
        if((A1.getText()==B2.getText()) && (B2.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            winner=A1.getText().toString();
            nobody=true;
            A1.setBackgroundColor(Color.parseColor("#7B68EE"));
            B2.setBackgroundColor(Color.parseColor("#7B68EE"));
            C3.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //waiter();

            //playerDialog();
        }
        if((A3.getText()==B2.getText()) && (B2.getText()==C1.getText()) && (A3.getText()=="X" || A3.getText()=="0")){
            winner=A3.getText().toString();
            nobody=true;
            A3.setBackgroundColor(Color.parseColor("#7B68EE"));
            B2.setBackgroundColor(Color.parseColor("#7B68EE"));
            C1.setBackgroundColor(Color.parseColor("#7B68EE"));
            enable();
            //playerDialog();
        }

        if(stepCounter==9 && nobody==false){
            System.out.println(nobody);
            enable();
            //playerDialog();
        }

    }

    private  void  waiter(){
        if(winner=="X"){
            makeMessage(player1);
        }else{
            makeMessage(player2);

        }

        //restart();


    }


    public void enable(){
        gamesPlayed++;
        changeResult();
        for (int i=0;i<9;i++){
            list.get(i).setEnabled(false);
        }
        if(gamesPlayed!=5){
            beetwenDialog();
        }else
        playerDialog();
    }

    private void beetwenDialog() {
        AlertDialog.Builder beetwenDialog=new AlertDialog.Builder(this);
        if(nobody==true) {
            beetwenDialog.setMessage("Переможець " + winner);
        }else{
            beetwenDialog.setMessage(Tournament.this.getString(R.string.nothing));
        }
        beetwenDialog.setPositiveButton("OK",onePlus);
        beetwenDialog.setCancelable(false);
        beetwenDialog.create().show();

    }

    public void playerDialog(){

        String end;
        if(firstPlayerScore>secondPlayerScore)
        {
            end=player1;
        }else{
            end=player2;
        }
        AlertDialog.Builder finishDialog=new AlertDialog.Builder(this);
        if(firstPlayerScore==secondPlayerScore){
            finishDialog.setMessage(Tournament.this.getString(R.string.nothing));
        }else {
            finishDialog.setMessage(Tournament.this.getString(R.string.end) + " " + end + "!");
        }
        finishDialog.setPositiveButton(Tournament.this.getString(R.string.again),gameOutOrLeave);
        finishDialog.setNegativeButton(Tournament.this.getString(R.string.menu),gameOutOrLeave);
        finishDialog.setCancelable(false);
        finishDialog.create().show();


    }

    DialogInterface.OnClickListener seterName=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int index) {
            switch (index){
                case DialogInterface.BUTTON_POSITIVE:
                    player1=firstPlayer.getText().toString();
                    player2=secondPlayer.getText().toString();
                     name1.setText(player1);
                     name2.setText(player2);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    setContentView(R.layout.menu);
                    finish();
                    break;

            }

        }
    };

    DialogInterface.OnClickListener onePlus=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int index) {
            switch (index) {
                case DialogInterface.BUTTON_POSITIVE:
                    restart();
                    break;
            }
        }
    };


    DialogInterface.OnClickListener gameOutOrLeave=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int index) {
            switch (index){
                case DialogInterface.BUTTON_POSITIVE:
                    restart();
                    dialogInterface();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    setContentView(R.layout.menu);
                    finish();
                    break;
            }
        }
    };

    private void restart(){
        winner="";
        stepCounter=0;
        nobody=false;
        sign = true;
        for (int i=0;i<9;i++) {
            list.get(i).setEnabled(true);
            list.get(i).setText("");
            list.get(i).setBackgroundDrawable(color);

        }
        if(gamesPlayed==5){
            gamesPlayed=0;
            firstPlayerScore=secondPlayerScore=0;
            name1.setText(null);
            name2.setText(null);
        }

    }

    private  void  changeResult(){
        if(winner=="X"){
            firstPlayerScore++;
            name1.setText(player1+"  "+firstPlayerScore);

        }else if(winner=="0"){
            secondPlayerScore++;
            name2.setText(player2+"  "+secondPlayerScore);
        }
        if(nobody==false){
            firstPlayerScore++;
            secondPlayerScore++;
            name1.setText(player1+"  "+firstPlayerScore);
            name2.setText(player2+"  "+secondPlayerScore);
        }
    }

    private void makeMessage(String message){
        Toast.makeText(this,"Переміг "+message,Toast.LENGTH_SHORT).show();

    }


    private String retStr(){
        return winner;
    }

}
