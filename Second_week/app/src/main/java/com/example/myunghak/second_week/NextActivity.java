package com.example.myunghak.second_week;

/**
 * Created by myunghak on 2015-12-02.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = this.getIntent();
        Toast.makeText(this.getApplicationContext(), "name : "+ in.getStringExtra("name"), Toast.LENGTH_LONG ).show();

    }
}