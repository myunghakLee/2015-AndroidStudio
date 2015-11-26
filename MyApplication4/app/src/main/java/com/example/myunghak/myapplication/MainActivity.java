package com.example.myunghak.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
    Extra -> Bundle(�����͹���) Ÿ���� �������� ������.
    Intent putExtra(String name, int value)
    Intent putExtra(String name, String value)
    Intent putExtra(String name, boolean value)

    // ���ϰ��� �����ִ� ��Ƽ��Ƽ�� ȣ���ϴ� �Լ�
    // requestCode : � ��Ƽ��Ƽ�� ���� �������� �����ϱ� ���� ������ ��ȣ, ������ ��� ���� �ȹ���
    public void startActivityForResult(Intent intent, int requsetCode)
    // ������ ���� ȣ���� ��� ó���Ǵ��� �˱����� �Լ�, ��Ƽ��Ƽ�� ������ ȣ���
    // requestCode : ȣ���� �� ������ ��û�ڵ�
    // resultCode : ��Ƽ��Ƽ�� ������
    proteced void onActivityResult(int requestCode, int resultCode, Intent data)

 */
public class MainActivity extends Activity {
    TextView mText;
    final static int ACT_EDIT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mText = (TextView)findViewById(R.id.text);

        Button btnEdit = (Button)findViewById(R.id.edit);
        btnEdit.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActEdit.class);
                intent.putExtra("TextIn", mText.getText().toString()); //����Ʈ�� ������ �ɾ� ������
                startActivityForResult(intent, ACT_EDIT);
            }
        });
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACT_EDIT:
                if(resultCode == RESULT_OK) {
                    mText.setText(data.getStringExtra("TextOut"));
                }
                else if (resultCode == RESULT_CANCELED)
                {
                    mText.setText(data.getStringExtra("�̰� �����"));
                }
                break;
        }
    }
}