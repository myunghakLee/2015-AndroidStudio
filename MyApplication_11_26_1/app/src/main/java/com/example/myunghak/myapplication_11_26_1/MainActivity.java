package com.example.myunghak.myapplication_11_26_1;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Database 관련 객체들
    SQLiteDatabase db;
    String dbName = "idList.db"; // name of Database;
    String tableName = "idListTable"; // name of Table;
    int dbMode = Context.MODE_PRIVATE;

    // layout object
    EditText mEtName;       //text바를 가리키는 변수를 하나 만듬
    Button mBtInsert;       //insert 버튼을 가리키는 변수를 하나 만듬
    Button mBtRead;
    Button mBtDelete;
    Button mBtUpdate;
    Button mBtSort;
    EditText mEtUpdate;
    EditText mEtDelete;
    ListView mList;         //ListView 를 가리키는 변수를 하나 만듬


    ArrayAdapter<String> baseAdapter;   //배열로 부터 데이터를 가져올때 사용하는 것이 arrayadapter
    ArrayList<String> nameList; //객체들을 삽입, 삭제 검색할 수 있는 컨테이너 class, linkedlist 비슷

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // // Database 생성 및 열기
        db = openOrCreateDatabase(dbName,dbMode,null);
        // 테이블 생성
        createTable();

        mEtName = (EditText) findViewById(R.id.et_text);        //변수 mEtname이 et_text를 나타내도록 한다
        mBtInsert = (Button) findViewById(R.id.bt_insert);
        mBtRead = (Button) findViewById(R.id.bt_read);
        mBtDelete = (Button) findViewById(R.id.bt_delete);
        mEtDelete = (EditText) findViewById(R.id.et_delete);
        mEtUpdate = (EditText) findViewById(R.id.et_Update);
        mBtUpdate = (Button) findViewById(R.id.bt_update);
        mBtSort = (Button) findViewById(R.id.bt_sort);
        ListView mList = (ListView) findViewById(R.id.list_view);

        mBtInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //button mBtinsert 를 클릭한경우 아래 코드를 실행하라
                String name = mEtName.getText().toString();
                insertData(name);
            }
        });
        mBtSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.clear();
                sort();
                baseAdapter.notifyDataSetChanged();
            }
        });

        mBtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = Integer.parseInt(mEtUpdate.getText().toString());
                String str = mEtName.getText().toString();
                updateData(temp,str);






                nameList.clear();
                selectAll();
                baseAdapter.notifyDataSetChanged();
            }
        });


        mBtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.clear();
                selectAll();
                baseAdapter.notifyDataSetChanged();
            }
        });

        mBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = Integer.parseInt(mEtDelete.getText().toString());
                removeData(temp);
                nameList.clear();
                selectAll();
                baseAdapter.notifyDataSetChanged();

            }
        });
        // Create listview
        nameList = new ArrayList<String>();
        baseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameList);
        mList.setAdapter(baseAdapter);

    }

    // Table 생성
    public void createTable() {//크기 설정은 안해도 되나???
        try {
            String sql = "create table " + tableName + "(id integer primary key autoincrement, name text not null)";
            db.execSQL(sql);//sql문을 실행시켜주는 역할
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite","error: "+ e);//디버그 If you want to print out a bunch of messages so you can log the exact flow of your program, use this. If you want to keep a log of variable values, use this.
        }
    }

    // Table 삭제
    public void removeTable() {
        String sql = "drop table " + tableName;
        db.execSQL(sql);
    }

    // Data 추가
    public void insertData(String name) {
        String sql = "insert into " + tableName + " values(NULL, '" + name + "');";//NuLL 값을 외 넣는지....
        db.execSQL(sql);
    }

    // Data 업데이트
    public void updateData(int index, String name) {
        String sql = "update " + tableName + " set name = '" + name + "' where id = " + index + ";";
        db.execSQL(sql);
    }



    // Data 삭제
    public void removeData(int index) {
        String sql = "delete from " + tableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    // Data 읽기(꺼내오기)
    public void selectData(int index) {
        String sql = "select * from " + tableName + index + ";";
        Cursor result = db.rawQuery(sql, null);

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if (result.moveToFirst()) {
            int id = result.getInt(0);
            String name = result.getString(1);
//            Toast.makeText(this, "index= " + id + " name=" + name, Toast.LENGTH_LONG).show();
            Log.d("lab_sqlite", "\"index= \" + id + \" name=\" + name ");
        }
        result.close();
    }

    // 모든 Data 읽기
    public void selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String num = results.getString(0);
//            Toast.makeText(this, "index= " + id + " name=" + name, Toast.LENGTH_LONG).show();
            Log.d("lab_sqlite", "index= " + id + " name=" + name);
            String add = "index = " + num+" value = "+name;
            nameList.add(add);
            results.moveToNext();
        }
        results.close();
    }
    public void sort(){
        String sql = "select * from " + tableName + " order by id desc;";
        Cursor results = db.rawQuery(sql, null);    //. Cursor는 DB에서 값을 가져와서 마치 실제 Table의 한 행(Row), 한 행(Row) 을 참조하는 것 처럼 사용 할 수 있게 해주는 것이다.
        results.moveToFirst();  //results cursor 의 제일 첫번째 행으로 이동

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String num = results.getString(0);
//            Toast.makeText(this, "index= " + id + " name=" + name, Toast.LENGTH_LONG).show();
            Log.d("lab_sqlite", "index= " + id + " name=" + name);//log.d("생명주기", "onCreate")
            String add = "index = " + num+" value = "+name;
            nameList.add(add);     //arryalist형 변수
            results.moveToNext();   //다음 행으로 이동
        }
        results.close();

    }

}
