package com.drusp.myconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PlantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(PlantsActivity.this, R.color.black));
        }
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout lin1=findViewById(R.id.line1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) LinearLayout lin2=findViewById(R.id.line2);

        CircularSeekBar circularSeekBar;


        lin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), StatusActivity.class));
            }
        });

        lin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), StatusActivity.class));
            }
        });
    }
}