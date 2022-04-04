package com.example.beebuu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beebuu.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtPhone,edtName,edtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        //Check if already user phone
                        if(datasnapshot.child(edtPhone.getText().toString()).exists()){
                            Toast.makeText(SignUp.this,"Phone Number Already Used!!!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"SignUp Success !!!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}