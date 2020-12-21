package com.example.sqliteexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb ;
    String formattedDate;
    EditText textInput;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        formattedDate = myDateObj.format(myFormatObj);


                 EditText textInput = findViewById(R.id.itemName);
                 Button button = findViewById(R.id.btnSubmit);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(textInput.getText().toString().isEmpty()){
                            textInput.setError("Enter Valid Name");
                        }else{
                            String input = textInput.getText().toString();
                            int item = (int) myDb.noItems();
                            myDb.addData(item, input, formattedDate);

                            Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }
    }
