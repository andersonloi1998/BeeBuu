package com.example.beebuu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.beebuu.Common.Common;
import com.example.beebuu.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone,edtName,edtPassword,edtSecureCode;
    FButton btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtSecureCode = (MaterialEditText)findViewById(R.id.edtSecureCode);

        btnSignUp = (FButton) findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Common.isConnectedToInternet(getBaseContext())){

                    final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                    mDialog.setMessage("Please wait");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            //Check if already user phone
                            if(datasnapshot.child(edtPhone.getText().toString()).exists()){
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this,"Phone Number Already Used!!!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(),
                                        edtPassword.getText().toString(),
                                        edtSecureCode.getText().toString());
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
                else{
                    Toast.makeText(SignUp.this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}