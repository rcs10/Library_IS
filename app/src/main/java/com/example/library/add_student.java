package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_student extends AppCompatActivity {

    String fname;
    String mname;
    String lname;
    String department;
    String phone;
    String email;
    String id;
    boolean iserror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button submitbtn = (Button) findViewById(R.id.submitbtn);
        Button cancelbtn = (Button) findViewById(R.id.cancelbtn);

        final EditText Fname = findViewById(R.id.Addstudentfname);
        final EditText Mname = findViewById(R.id.Addstudentmname);
        final EditText Lname = findViewById(R.id.Addstudentlname);
        final EditText Department = findViewById(R.id.Addstudentdepartment);
        final EditText Phone = findViewById(R.id.Addstudentphone);
        final EditText Email = findViewById(R.id.Addstudentemail);
        final EditText ID = findViewById(R.id.Addidnumber);



        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String MobilePattern = "[0-9]{10}";

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(add_student.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = Fname.getText().toString().trim();
                mname = Mname.getText().toString().trim();
                lname = Lname.getText().toString().trim();
                department = Department.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                id = ID.getText().toString().trim();
                iserror = false;
                if(TextUtils.isEmpty(email)){
                    iserror = true;
                    Email.setError("Enter e-mail ID");
                }
                if(!email.matches(emailPattern)){
                    iserror = true;
                    Email.setError("Enter correct e-mail ID");
                }
                if(fname.isEmpty())
                {
                    Fname.setError("Enter First name");
                    iserror = true;
                }
                if(lname.isEmpty())
                {
                    Lname.setError("Enter Last name");
                    iserror = true;
                }
                if(phone.isEmpty())
                {
                    Phone.setError("Enter Phone Number");
                    iserror = true;
                }
                if(!phone.matches(MobilePattern))
                {
                    Phone.setError("Enter Valid Phone Number");
                    iserror = true;
                }
                if(department.isEmpty())
                {
                    Department.setError("Enter department");
                    iserror = true;
                }
                if(id.isEmpty())
                {
                    ID.setError("Enter ID");
                    iserror = true;
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(add_student.this);
                    int isInserted;
                    if(mname.isEmpty()) {
                        isInserted = db.insertIntoStudent(fname + " " + lname, email, phone, department, id);
                    }
                    else
                    {
                        isInserted = db.insertIntoStudent(fname + " " + mname + " " + lname, email, phone, department, id);
                    }

                    if(isInserted == 0)
                    {
                        Toast.makeText(add_student.this, "Student added", Toast.LENGTH_SHORT).show();
                        Intent submitIntent = new Intent(add_student.this,MainActivity.class);
                        startActivity(submitIntent);
                    }
                    else if(isInserted == 1)
                    {
                        Toast.makeText(add_student.this, "Student already exists", Toast.LENGTH_SHORT).show();
                    }
                    else if(isInserted == 2)
                    {
                        Toast.makeText(add_student.this,"No student with these details has registered", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });



    }
}
