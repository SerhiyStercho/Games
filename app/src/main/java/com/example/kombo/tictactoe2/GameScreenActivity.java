package com.example.kombo.tictactoe2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GameScreenActivity extends AppCompatActivity {
    Button A1,A2,A3,B1,B2,B3,C1,C2,C3;
    boolean sign=true;
    int stepCounter=0;
    Drawable color;
    public String winner;
    public boolean nobody=false;
    ArrayList<Button> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        startService(new Intent(this, Menu.class));
        stepCounter=0;
        A1=(Button) findViewById(R.id.A1);
        A2=(Button) findViewById(R.id.A2);
        A3=(Button) findViewById(R.id.A3);
        B1=(Button) findViewById(R.id.B1);
        B2=(Button) findViewById(R.id.B2);
        B3=(Button) findViewById(R.id.B3);
        C1=(Button) findViewById(R.id.C1);
        C2=(Button) findViewById(R.id.C2);
        C3=(Button) findViewById(R.id.C3);
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
        mainMethod();
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
            fillButtons(A1,A2,A3);
        }
        if((B1.getText()==B2.getText()) && (B2.getText()==B3.getText()) && (B2.getText()=="X" || B2.getText()=="0")){
            fillButtons(B1,B2,B3);
        }
        if((C1.getText()==C2.getText()) && (C2.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            fillButtons(C1,C2,C3);
        }

        ///vertical
        if((A1.getText()==B1.getText()) && (B1.getText()==C1.getText()) && (A1.getText()=="X" || A1.getText()=="0")){
            fillButtons(A1,B1,C1);
        }
        if((A2.getText()==B2.getText()) && (B2.getText()==C2.getText())&& (B2.getText()=="X" || B2.getText()=="0")){
            fillButtons(A2,B2,C2);
        }
        if((A3.getText()==B3.getText()) && (B3.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            fillButtons(A3,B3,C3);
        }

        //diagonal
        if((A1.getText()==B2.getText()) && (B2.getText()==C3.getText()) && (C3.getText()=="X" || C3.getText()=="0")){
            fillButtons(A1,B2,C3);
        }
        if((A3.getText()==B2.getText()) && (B2.getText()==C1.getText()) && (A3.getText()=="X" || A3.getText()=="0")){
            fillButtons(A3,B2,C1);
        }

        if(stepCounter==9 && nobody==false){
            System.out.println(nobody);
            playerDialog();
        }

    }

    public void enable(){
        for (int i=0;i<9;i++){
            list.get(i).setEnabled(false);
        }
    }

    public void playerDialog(){
        AlertDialog.Builder finishDialog=new AlertDialog.Builder(this);
        if(nobody!=true){
            finishDialog.setMessage(GameScreenActivity.this.getString(R.string.nothing));
        }else {
            finishDialog.setMessage(GameScreenActivity.this.getString(R.string.end) + " " + retStr() + "!");
        }
        finishDialog.setPositiveButton(GameScreenActivity.this.getString(R.string.again),gameOutOrLeave);
        finishDialog.setNegativeButton(GameScreenActivity.this.getString(R.string.menu),gameOutOrLeave);
        finishDialog.setCancelable(false);
        finishDialog.create().show();


    }


    DialogInterface.OnClickListener gameOutOrLeave=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int index) {
            switch (index){
                case DialogInterface.BUTTON_POSITIVE:
                    System.out.println(""+winner);
                    stepCounter=0;
                    nobody=false;
                    for (int i=0;i<9;i++) {
                        sign = true;
                        list.get(i).setEnabled(true);
                        list.get(i).setText("");
                        list.get(i).setBackgroundDrawable(color);

                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    setContentView(R.layout.menu);
                    finish();
                    break;
            }
        }
    };

    private void fillButtons(Button but1,Button but2,Button but3){
        winner=but1.getText().toString();
        nobody=true;
        but1.setBackgroundColor(Color.parseColor("#7B68EE"));
        but2.setBackgroundColor(Color.parseColor("#7B68EE"));
        but3.setBackgroundColor(Color.parseColor("#7B68EE"));
        enable();
        playerDialog();
    }

    private String retStr(){
        return winner;
    }


}
