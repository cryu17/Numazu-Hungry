package com.example.numazu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StampLocationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_stamp);

        Button stamp_button_1 = findViewById(R.id.stamp_button_1);
        Button stamp_button_2 = findViewById(R.id.stamp_button_2);
        Button stamp_button_3 = findViewById(R.id.stamp_button_3);
        Button stamp_button_4 = findViewById(R.id.stamp_button_4);
        Button stamp_button_5 = findViewById(R.id.stamp_button_5);
        Button stamp_button_6 = findViewById(R.id.stamp_button_6);
        Button stamp_button_7 = findViewById(R.id.stamp_button_7);
        Button stamp_button_8 = findViewById(R.id.stamp_button_8);
        Button stamp_button_9 = findViewById(R.id.stamp_button_9);
        Button stamp_button_10 = findViewById(R.id.stamp_button_10);
        stamp_button_1.setOnClickListener(this);
        stamp_button_2.setOnClickListener(this);
        stamp_button_3.setOnClickListener(this);
        stamp_button_4.setOnClickListener(this);
        stamp_button_5.setOnClickListener(this);
        stamp_button_6.setOnClickListener(this);
        stamp_button_7.setOnClickListener(this);
        stamp_button_8.setOnClickListener(this);
        stamp_button_9.setOnClickListener(this);
        stamp_button_10.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ListActivity.class);
        Button b = (Button) v;
        DataHolder.setLocation(b.getContentDescription().toString());
        DataHolder.setLocationLocale(b.getText().toString());
        startActivity(intent);
    }
}
