package com.example.bettero;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class upload extends AppCompatActivity {
    private CardView addjobs;
    private ImageView job_image_view;
    private EditText upload_jobs;
    private Button btn;
    private Bitmap bitmap;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private ProgressDialog pd;
    String downloadUrl="";
    private final int REQ=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        reference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        pd=new ProgressDialog(this);

        addjobs=findViewById(R.id.addjobs);
        job_image_view=findViewById(R.id.job_image_view);
        upload_jobs=findViewById(R.id.upload_job);
        btn=findViewById(R.id.upload_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upload_jobs.getText().toString().isEmpty()){
                    upload_jobs.setError("empty");
                    upload_jobs.requestFocus();
                } else if (bitmap==null) {
                    uploadData();

                }else{
                    uploadImage();
                }
            }

            private void uploadImage() {
                pd.setMessage("uploading.....");
                pd.show();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
                byte[] finalimg=baos.toByteArray();
                final StorageReference  filepath;
                filepath=storageReference.child(" uploaded jobs").child(finalimg+"jpg");
                final UploadTask uploadTask=filepath.putBytes(finalimg);
                uploadTask.addOnCompleteListener(upload.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            downloadUrl =String.valueOf(uri);
                                            uploadData();
                                        }
                                    });
                                }
                            });
                        }else{
                            pd.dismiss();
                            Toast.makeText(upload.this, "something went hussy", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            private void uploadData() {
                reference=reference.child("Jobs_database");
                final String uniquekey=reference.push().getKey();
                String title=upload_jobs.getText().toString();
                Calendar calender = Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yy");
                String date=currentDate.format(calender.getTime());
                Calendar calentime = Calendar.getInstance();
                SimpleDateFormat currenttime=new SimpleDateFormat("hh-mm a");
                String time=currenttime.format(calentime.getTime());

                database_jobs database_jobs=new database_jobs(title,downloadUrl,date,time,uniquekey);
                reference.child(uniquekey).setValue(database_jobs).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        pd.dismiss();
                        Toast.makeText(upload.this, "ALL RIGHT ITS DONE,uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(upload.this, "something hussy", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        addjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opengalery();
            }
        });
    }




    private void opengalery() {
        Intent pick=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pick,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==REQ && resultCode==RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            job_image_view.setImageBitmap(bitmap);

        }

    }






}