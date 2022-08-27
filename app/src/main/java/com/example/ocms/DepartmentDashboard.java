package com.example.ocms;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocms.adapter.ShowAllComplaintAdapter;
import com.example.ocms.model.DepartmentUser;
import com.example.ocms.model.complaint;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDashboard extends AppCompatActivity {


    DepartmentUser model = new DepartmentUser();

    String department = "";

    List<complaint> mList = new ArrayList<>();
    RecyclerView rv;
    ShowAllComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_dashboard);

        rv = findViewById(R.id.rv_showAllFood);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(DepartmentDashboard.this));

        getUser();
        getAllFood();

    }

    public void getUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.DEPARTMENT).child(firebaseUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    model = snapshot.getValue(DepartmentUser.class);
//                    Toast.makeText(DepartmentDashboard.this,model.getUserName(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void getAllFood() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser.getUid() != null) {
//            if(model == null) {
//                Toast.makeText(DepartmentDashboard.this,"NULL",Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(DepartmentDashboard.this,model.getUserName(),Toast.LENGTH_SHORT).show();
//            }
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterActivity.COMPLAINT);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
//                    String res=""+snapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        complaint model = dataSnapshot.getValue(complaint.class);
//                        res+=model.getId();
                        mList.add(model);
                    }
//                    Toast.makeText(DepartmentDashboard.this,res,Toast.LENGTH_SHORT).show();
                    adapter = new ShowAllComplaintAdapter(DepartmentDashboard.this, mList);
                    rv.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}