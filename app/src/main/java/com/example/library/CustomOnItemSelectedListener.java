package com.example.library;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

    String selected;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
