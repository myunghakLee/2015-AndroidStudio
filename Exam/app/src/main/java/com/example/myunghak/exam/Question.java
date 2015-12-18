package com.example.myunghak.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Question {

    String question;
    int correctAnswer;
    int userAnswer;


    public Question(String q, int answer){
        question = q;
        correctAnswer = answer;
        restAnswer();
    }
    public boolean isCorrect(){
        if(correctAnswer == userAnswer){    return true; }
        else{   return false;   }
    }
    public String getQuestion(){
        return question;
    }
    public void setUserAnswer(int answer){
        userAnswer = answer;
    }
    public void restAnswer(){
        userAnswer = -1;
    }

}
