package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btn_simpan;
    EditText ruangan, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        ruangan=findViewById(R.id.ruangan);
        kapasitas=findViewById(R.id.kapasitas);
        btn_simpan=findViewById(R.id.btn_simpan);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE ruangan = '" +
                getIntent().getStringExtra("ruangan")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            ruangan.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=database.getWritableDatabase();
                db.execSQL("update inventory set ruangan='" +
                        ruangan.getText().toString() + "', kapasitas= '" +
                        kapasitas.getText().toString() + "' where ruangan = '" +
                                getIntent().getStringExtra("ruangan")+"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}