package com.example.loser.tugasvoice.Login_Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loser.tugasvoice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaftarActivity extends Activity {

    private EditText inputNama,
            inputEmail,
            inputPassword;

    private String key,
            nama,
            email,
            password,
            telepon;

    private FirebaseAuth auth;
    private Button btnSignUp;
    private ProgressDialog mProgress;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();

        mProgress = new ProgressDialog(this);

        inputNama = (EditText) findViewById(R.id.etNama);
        inputEmail = (EditText) findViewById(R.id.etEmail);
        inputPassword = (EditText) findViewById(R.id.etPassword);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.setMessage("Mendaftar ...");
                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    inputEmail.setError("Wajib diisi");
                }
                if (password.isEmpty()) {
                    inputPassword.setError("Wajib diisi");
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password terlalu pendek, masukkan minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mProgress.show();

                //create user
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            showDialog();
                            mProgress.dismiss();
                        } else {
                            startActivity(new Intent(DaftarActivity.this, Login.class));
                            simpanData();
                            Toast.makeText(DaftarActivity.this, "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                            mProgress.dismiss();
                            finish();
                        }
                    }
                });
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Pemberitahuan")
                .setCancelable(true)
                .setMessage("Daftar Gagal")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alert.show();
    }

    private void simpanData() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        key = user.getUid();

        nama = inputNama.getText().toString().trim();
        email = inputEmail.getText().toString().trim();
        password = inputPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mProgress.show();
            dbRef.child("Anggota").child(key).child("key").setValue(key);
            dbRef.child("Anggota").child(key).child("nama").setValue(nama);
            dbRef.child("Anggota").child(key).child("email").setValue(email);
            dbRef.child("Anggota").child(key).child("pass").setValue(password);

        }
    }
}