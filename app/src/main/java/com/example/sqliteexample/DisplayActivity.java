package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ArrayList<String> item_id, item_name, item_date;

    public static final String TABLE_NAME = "user_data";
    DatabaseHelper helper;
    RecyclerView cartRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        cartRec = findViewById(R.id.recDisplay);

        helper = new DatabaseHelper(getApplicationContext());
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_date = new ArrayList<>();

        readAllData();

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), item_id, item_name, item_date);
        cartRec.setLayoutManager(new LinearLayoutManager(this));
        cartRec.setAdapter(adapter);
    }

    private void readAllData() {

        Cursor cursor = helper.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Empty List", Toast.LENGTH_SHORT).show();
        }else{

            while (cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_date.add(cursor.getString(2));

            }
        }
    }
}