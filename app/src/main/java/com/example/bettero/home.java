package com.example.bettero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
Button homepage,profile,uploadjobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homepage=findViewById(R.id.homepage);
        profile=findViewById(R.id.profile);
        uploadjobs=findViewById(R.id.uploadjobs);


        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hpage=new Intent(getApplicationContext(),demomain.class);
                startActivity(hpage);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hpage=new Intent(getApplicationContext(),demosign.class);
                startActivity(hpage);
            }
        });
        uploadjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hpage=new Intent(getApplicationContext(),mainforlogin.class);
                startActivity(hpage);
            }
        });
    }
}