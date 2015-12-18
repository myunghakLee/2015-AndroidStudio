package com.example.myunghak.exam;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    Question Q1 = new Question("보기중 가장 큰 수를 고르시오 \n" +
            " 1)0   2)4   3)50",3);

    Question Q2 = new Question("보기중 가장 작은 수를 고르시오 \n" +
            " 1)0   2)4   3)50",1);
    Question Q3 = new Question("보기중 음수를 고르시오 \n" +
            " 1)-5   2)4   3)50",1);
    Question Q4 = new Question("보기중 알파벳 고르시오 \n" +
            " 1)0   2)A   3)50",2);

    // Database 관련 객체들
    SQLiteDatabase db;
    String dbName = "idList.db"; // name of Database;
    String tableName = "idListTable"; // name of Table;
    int dbMode = Context.MODE_PRIVATE;


    Button mbt_next;
    Button mbt_prev;
    Button mbt_input;
    Button mbt_result;
    EditText met_answer;
    TextView problem;
    int problem_num = 1;



    public void show_problem()
    {
        if(problem_num==0){
            problem_num++;
            Toast.makeText(this, "현재 첫번째 문제입니다.", Toast.LENGTH_LONG).show();
        }
        else if(problem_num==1) {
            problem.setText(Q1.getQuestion());
        }
        else if(problem_num==2) {
            problem.setText(Q2.getQuestion());
        }
        else if(problem_num==3) {
            problem.setText(Q3.getQuestion());
        }
        else if(problem_num==4) {
            problem.setText(Q4.getQuestion());
        }
        else if(problem_num==5){
            Toast.makeText(this, "현재 마지막 문제입니다.", Toast.LENGTH_LONG).show();
            problem_num--;
        }

    }

    public void input_answer(int ans, ArrayList<String> is_it_right){
        if(ans==0){
            Toast.makeText(this,  "올바른 수를 입력하시오", Toast.LENGTH_LONG).show();

        }
        else {
          Toast.makeText(this, problem_num + "번문제 " + ans + "보기 선택", Toast.LENGTH_LONG).show();
        }

        if(problem_num==1){
            Q1.setUserAnswer(ans);
            if(Q1.isCorrect()){
                is_it_right.set(0, "1");
            }
            else{
                is_it_right.set(0, "0");
            }
        }
        else if(problem_num==2){
            Q2.setUserAnswer(ans);
            if(Q2.isCorrect()){
                is_it_right.set(1, "1");
            }
            else{
                is_it_right.set(1, "0");
            }
        }
        else if(problem_num==3) {
            Q3.setUserAnswer(ans);
            if(Q3.isCorrect()){
                is_it_right.set(2, "1");
            }
            else{
                is_it_right.set(2, "0");
            }
        }
        else if(problem_num==4){
            Q4.setUserAnswer(ans);
            if(Q4.isCorrect()){
                is_it_right.set(3, "1");
            }
            else{
                is_it_right.set(3, "0");
            }
        }


    }


    public void result(View v,ArrayList<String> is_it_right)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        //intent.putExtra("result", is_it_right);


        intent.putExtra("result1", is_it_right);

        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> is_it_right;
        is_it_right = new ArrayList<String>();
        is_it_right.add(0, "0");
        is_it_right.add(1,"0");
        is_it_right.add(2,"0");
        is_it_right.add(3,"0");

        mbt_next = (Button) findViewById(R.id.bt_right);
        mbt_prev = (Button) findViewById(R.id.bt_left);
        mbt_input = (Button) findViewById(R.id.bt_insert);
        mbt_result = (Button) findViewById(R.id.bt_result);
        met_answer = (EditText) findViewById(R.id.et_answer);
        problem = (TextView) findViewById(R.id.tv_questions);

        problem.setText(Q1.getQuestion());

///////////////////////////////////////////////////////////////////////////////////button control///////////////////////////////////////////////////////////////
        mbt_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
                result(v,is_it_right);
            }
        });
        mbt_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
                problem_num--;
                show_problem();
            }
        });
        mbt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
                problem_num++;
                show_problem();
            }
        });
        mbt_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
             int ans;
               try {
                    ans = Integer.parseInt(met_answer.getText().toString());
               }
               catch(NumberFormatException e)
               {
                    ans = 0;
               }
                   input_answer(ans, is_it_right);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

}

