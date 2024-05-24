package com.drusp.myconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn_switching =findViewById(R.id.button_switching);
        btn_switching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GsmActivity.class));
            }

        });
       // startActivity(new Intent(getBaseContext(), MainActivity_v2.class));

       // startActivity(new Intent(getBaseContext(), GsmActivity.class));

    }

}
