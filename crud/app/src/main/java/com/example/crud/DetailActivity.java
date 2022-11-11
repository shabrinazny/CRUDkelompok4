package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    //Button btn_simpan;
    TextView ruangan, kapasitas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        database = new Database(this);
        ruangan=findViewById(R.id.ruangan);
        kapasitas=findViewById(R.id.kapasitas);
        //btn_simpan=findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE ruangan = '" +
                getIntent().getStringExtra("ruangan")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            ruangan.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }
    }
}