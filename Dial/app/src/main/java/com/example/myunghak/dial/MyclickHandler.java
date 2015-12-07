package com.example.myunghak.dial;

import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by myunghak on 2015-12-01.
 */


class MyClickHandler implements OnClickListener {
    @Override
    public void onClick(View v) {
        try {
            String myData = txtUriString.getText().toString();
            Intent myActivity2 = new Intent(Intent.ACTION_DIAL,
                    Uri.parse(myData));
            startActivity(myActivity2);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }// onClick
}// ClickHandler
