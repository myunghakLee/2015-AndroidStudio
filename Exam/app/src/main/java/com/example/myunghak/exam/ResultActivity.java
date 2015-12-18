package com.example.myunghak.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    TextView result_text1;
    TextView result_text2;
    TextView result_text3;
    TextView result_text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_text1 = (TextView) findViewById(R.id.textView1);
        result_text2 = (TextView) findViewById(R.id.textView2);
        result_text3 = (TextView) findViewById(R.id.textView3);
        result_text4 = (TextView) findViewById(R.id.textView4);

        Button back = (Button) findViewById(R.id.bt_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
                finish();
            }
        });
        ArrayList<String> is_it_right;
        is_it_right = new ArrayList<String>();
        is_it_right = this.getIntent().getStringArrayListExtra("result1");

        int correct_num=0;

        if(Integer.parseInt(is_it_right.get(0)) == 1){
            result_text1.setText("1번문제 맞음");
            correct_num++;
        }
        else
            result_text1.setText("1번문제 틀림");

        if(Integer.parseInt(is_it_right.get(1)) == 1){
            result_text2.setText("2번문제 맞음");
            correct_num++;
        }
        else
            result_text2.setText("2번문제 틀림");
        if(Integer.parseInt(is_it_right.get(2)) == 1){
            result_text3.setText("3번문제 맞음");
            correct_num++;
        }
        else
            result_text3.setText("3번문제 틀림");
        if(Integer.parseInt(is_it_right.get(3)) == 1){
            result_text4.setText("4번문제 맞음");
            correct_num++;
        }
        else
            result_text4.setText("4번문제 틀림");


        Toast.makeText(this, "현재 맞힌 갯수는 " + correct_num+"개입니다.", Toast.LENGTH_LONG).show();

    }
}
