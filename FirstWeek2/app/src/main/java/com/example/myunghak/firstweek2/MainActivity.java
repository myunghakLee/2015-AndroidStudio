package com.example.myunghak.firstweek2;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myunghak.firstweek2.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST", "oncreate1");
        setContentView(R.layout.activity_main);
        Button bt = (Button)this.findViewById(R.id.btnNew);



        bt.setOnClickListener(new View.OnClickListener(){
                                  @Override
                                  public void onClick(View v) {
                                      Toast.makeText(MainActivity.this.getApplicationContext(), "Click Button", Toast.LENGTH_LONG).show();
                                  }
                              }
        );



        // bt.setOnClickListener(btnlistener2);


        /*
            //3 리스너 구현
            ButtonClickListener btnlistener = new ButtonClickListener();
                bt.setOnClickListener(btnlistener);
         */

    }
    //2 익명 클래스
    View.OnClickListener btnlistener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Click Button", Toast.LENGTH_LONG).show();
        }
    };

    /*
    //3 리스너 구현
    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Click Button", Toast.LENGTH_LONG).show();
        }
    }
    */

    public void onNewBtnClicked(View v) {
        Toast.makeText(getApplicationContext(), "click button2.", Toast.LENGTH_LONG).show();
    }


    public void onTestBtnClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
