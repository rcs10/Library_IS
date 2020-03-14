package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class your_history extends AppCompatActivity {
    DatabaseHelperClass mydb;
    ArrayList<History> students = new ArrayList<>();
    Cursor tempDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_history);


        SharedPreferences prefs = getSharedPreferences("MyFiles", MODE_PRIVATE);
        String UserEmail = prefs.getString("UserEmail", "mailme.vikasb@gmail.com");//"No name defined" is the default value.
        String UserNumber = prefs.getString("PhoneNumber", "9731807100"); //0 is the default value.
        String UserName = prefs.getString("Name", "Vikas BN");
        String UserRole = prefs.getString("Role", "Admin");

//        Toast.makeText(this, UserEmail, Toast.LENGTH_SHORT).show();

        mydb = new DatabaseHelperClass(your_history.this);
        tempDB = mydb.getParticularHistory(UserRole,UserEmail);
        if(tempDB!=null){
            while (tempDB.moveToNext())
            {
                students.add(new History(tempDB.getString(1),tempDB.getString(0),tempDB.getString(2),
                        tempDB.getString(3),tempDB.getString(4)));
            }

            Collections.reverse(students);

            ListView studenthistory = (ListView) findViewById(R.id.yourhistory);
            StudentAdapter adapt = new StudentAdapter(your_history.this,0,students);
            studenthistory.setAdapter(adapt);
        }
        else
        {
            Toast.makeText(this,  UserName + ", You have not issued any books", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        students.clear();
    }
}
