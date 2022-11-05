package com.codes.bilikjelantah;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codes.bilikjelantah.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvGoToLogin;
    private EditText etEmail;
    private EditText etNama;
    private EditText etPassword;
    private EditText etKonfirmPassword;
    private TextView btnLogin;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!etPassword.getText().toString().equals(etKonfirmPassword.getText().toString())) {
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Password Tidak Sama")
                            .show();
                } else if(etEmail.getText().toString().matches("")||etNama.getText().toString().matches("")||etPassword.getText().toString().matches("")||etKonfirmPassword.getText().toString().matches("")){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Semua Wajib Di isi !")
                            .show();
                }else if(!etEmail.getText().toString().trim().matches(emailPattern)){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Format Email Salah")
                            .show();
                }else if (etPassword.getText().toString().length() <= 8 ){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Password Harus Lebih dari 8 Karakter")
                            .show();
                }
                else {
                    storeData();
                }



            }

        });
    }

    private void initView() {
        tvGoToLogin = (TextView) findViewById(R.id.tv_go_to_login);
        etEmail = (EditText) findViewById(R.id.et_email);
        etNama = (EditText) findViewById(R.id.et_nama);
        etPassword = (EditText) findViewById(R.id.et_password);
        etKonfirmPassword = (EditText) findViewById(R.id.et_konfirm_password);
        btnLogin = (TextView) findViewById(R.id.btn_login);
    }
    private void storeData(){
        SweetAlertDialog pDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.orange);
        pDialog.setTitleText("Loading ...");
        pDialog.setCancelable(true);
        pDialog.show();
        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            User user1 = new User(currentUser.getUid(), etEmail.getText().toString(), etNama.getText().toString(), etPassword.getText().toString(), 0, 0,25);
                            databaseReference.child("user").child(currentUser.getUid()).setValue(user1);
                            Intent intent = new Intent(RegisterActivity.this, MainMenu.class);
                            startActivity(intent);
                            pDialog.dismissWithAnimation();
                            finish();

                        } else {
                            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                            pDialog.dismissWithAnimation();
                        }
                    }
                });
    }
}