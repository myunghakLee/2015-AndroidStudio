package com.example.myunghak.dial;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IntentDemo1B extends Activity {
    EditText txtUriString;
    Button btnCallActivity2;
    Context context = getApplication();

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            txtUriString = (EditText) findViewById(R.id.txtUriString);
            btnCallActivity2 = (Button) findViewById(R.id.btnCallActivity2);
            btnCallActivity2.setOnClickListener(new MyClickHandler());
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }// onCreate

}