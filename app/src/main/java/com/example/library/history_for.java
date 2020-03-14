package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class history_for extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_for);

        SharedPreferences prefs = getSharedPreferences("MyFiles", MODE_PRIVATE);
        String UserEmail = prefs.getString("UserEmail", "mailme.vikasb@gmail.com");//"No name defined" is the default value.
        String UserNumber = prefs.getString("PhoneNumber", "9731807100"); //0 is the default value.
        String UserName = prefs.getString("Name", "Vikas BN");
        String UserRole = prefs.getString("Role","Admin");

        if(UserRole.equals("Admin")) {

            Button studentHistory = (Button) findViewById(R.id.historyForStudent);
            studentHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToStudentHistory = new Intent(history_for.this, student_history.class);
                    startActivity(intentToStudentHistory);
                }
            });

            Button facultyHistory = (Button) findViewById(R.id.historyForFaculty);
            facultyHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToFacultyHistory = new Intent(history_for.this, faculty_history.class);
                    startActivity(intentToFacultyHistory);
                }
            });
        }
        else {
        Intent intentToYourHistory = new Intent(history_for.this,your_history.class);
        startActivity(intentToYourHistory);
        }
    }
}
