package com.example.bettero;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bettero.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button a;
    Button student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a=findViewById(R.id.admin);
        student=findViewById(R.id.student);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginadmin=new Intent(getApplicationContext(),demosign.class);
                startActivity(loginadmin);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginstudent=new Intent(getApplicationContext(),demologin.class);
                startActivity(loginstudent);
            }
        });

    }
}