package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class profile_settings extends AppCompatActivity {


    @Override
    protected void onRestart() {
        super.onRestart();


        SharedPreferences prefs = getSharedPreferences("MyFiles", MODE_PRIVATE);
        String UserEmail = prefs.getString("UserEmail", "mailme.vikasb@gmail.com");//"No name defined" is the default value.
        String UserNumber = prefs.getString("PhoneNumber", "9731807100"); //0 is the default value.
        String UserName = prefs.getString("Name", "Vikas BN");

        TextView email = (TextView) findViewById(R.id.profileSemail);
        TextView name = (TextView) findViewById(R.id.profileSname);
        TextView phone = (TextView) findViewById(R.id.profileSphno);


        email.setText(UserEmail);
        name.setText(UserName);
        phone.setText(UserNumber);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);


        ArrayList<DataClass> items = new ArrayList<>();


        SharedPreferences prefs = getSharedPreferences("MyFiles", MODE_PRIVATE);
        String UserEmail = prefs.getString("UserEmail", "mailme.vikasb@gmail.com");//"No name defined" is the default value.
        String UserNumber = prefs.getString("PhoneNumber", "9731807100"); //0 is the default value.
        String UserName = prefs.getString("Name", "Vikas BN");

        TextView email = (TextView) findViewById(R.id.profileSemail);
        TextView name = (TextView) findViewById(R.id.profileSname);
        TextView phone = (TextView) findViewById(R.id.profileSphno);


        email.setText(UserEmail);
        name.setText(UserName);
        phone.setText(UserNumber);

        Intent intentTOShareApp = new Intent();
        intentTOShareApp.setAction(Intent.ACTION_SEND);
        intentTOShareApp.putExtra(Intent.EXTRA_TEXT,
                "Hey there!\nDownload this new app,'BookEazy' to ease out the management of libraries\nhttps://drive.google.com/open?id=1poXP1K4cvjXda4LWva9lePTc_IzA7Bjo");
        intentTOShareApp.setType("text/plain");
        if (items.size() == 0) {
            items.add(new DataClass(R.drawable.editaccount,"EditAccount",new Intent(profile_settings.this,conformation.class) ));
            items.add(new DataClass(R.drawable.share,"ShareApp",intentTOShareApp ));
            items.add(new DataClass(R.drawable.directions,"Directions",new Intent(profile_settings.this,terms.class) ));
            items.add(new DataClass(R.drawable.delete,"DeleteAccount",new Intent(profile_settings.this,delete_account.class) ));
            items.add(new DataClass(R.drawable.developers,"Developers",new Intent(profile_settings.this,Developers.class) ));
        }
        ViewAdapter adapter = new ViewAdapter(profile_settings.this, items);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }
}
