package com.example.sqliteapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insertBtn,updateBtn,deleteBtn,viewBtn;
    dbhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        viewBtn = findViewById(R.id.viewBtn);

        db = new dbhelper(this);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkinsertdata = db.insertuserdata(nameTXT,contactTXT,dobTXT);
                if (checkinsertdata) Toast.makeText(MainActivity.this,"New entry Inserted" , Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this,"New entry Insert Failed" , Toast.LENGTH_SHORT).show();

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkupdatedata = db.updateuserdata(nameTXT,contactTXT,dobTXT);
                if (checkupdatedata) Toast.makeText(MainActivity.this,"Entry Updated" , Toast.LENGTH_SHORT).show();
               else Toast.makeText(MainActivity.this,"Update Failed" , Toast.LENGTH_SHORT).show();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkdeletedata = db.deletedata(nameTXT);
                if (checkdeletedata) Toast.makeText(MainActivity.this,"Entry Deleted" , Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this,"Delete Failed" , Toast.LENGTH_SHORT).show();
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if (res.getCount() == 0 ){
                    Toast.makeText(MainActivity.this, "No entry found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()){
                    buffer.append("Name :").append(res.getString(0)).append("\n");
                    buffer.append("Contact :").append(res.getString(1)).append("\n");
                    buffer.append("DOB  :").append(res.getString(2)).append("\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}