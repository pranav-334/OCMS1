package com.example.ocms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpenComplaintActivity extends AppCompatActivity {

    Button btn_markAsInProgress, btn_save;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_complaint);

        progressDialog = new ProgressDialog(this);

        btn_markAsInProgress = findViewById(R.id.btn_markasInProgress);

        btn_markAsInProgress.setOnClickListener(v -> {
            progressDialog.setTitle("Marking as In Progress");
            progressDialog.setMessage("Marking....");
            progressDialog.show();
            progressDialog.dismiss();
        });

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(v -> {
            progressDialog.setTitle("Saving ");
            progressDialog.setMessage("Saving....");
            progressDialog.show();
            progressDialog.dismiss();
        });
    }
}