package com.example.sqllitenativedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_uid, edt_firstname, edt_lastname;
    TextView tv_show;
    SQLiteDatabase db;
    MySQLiteOpenHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.btn_add)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_read)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_remove)).setOnClickListener(this);
        edt_uid = findViewById(R.id.edt_uid);
        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        myHelper = new MySQLiteOpenHelper(this, "user_db",null,1);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_add){
            addUser(edt_uid.getText().toString(),
                    edt_firstname.getText().toString(),
                    edt_lastname.getText().toString());
        }
    }
    public void addUser(String uid, String f, String l){
        db = myHelper.getWritableDatabase();
        String sql = "INSERT INTO users VALUES (?,?,?)";
        db.execSQL(sql, new String[]{uid,f,l});
        db.close();
    }
}