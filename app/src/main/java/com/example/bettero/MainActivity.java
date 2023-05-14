package com.example.bettero;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bettero.R;

import android.content.Intent;
import android.net.Uri;
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
               //gotoUrl("https://previewer.adalo.com/af472125-70dd-4aaf-973f-ce2a009eef23");
               Intent loginadmin=new Intent(getApplicationContext(),demosign.class);
                startActivity(loginadmin);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://previewer.adalo.com/af472125-70dd-4aaf-973f-ce2a009eef23");
                //Intent loginstudent=new Intent(getApplicationContext(),demologin.class);
                //startActivity(loginstudent);
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}