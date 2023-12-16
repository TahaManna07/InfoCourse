package com.example.infocourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonGoToRegister;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonGoToRegister = findViewById(R.id.buttonGoToRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // L'utilisateur est connecté avec succès
                                    Toast.makeText(MainActivity2.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                                    finish();
                                } else {
                                    // Une erreur s'est produite lors de la connexion
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                    Toast.makeText(MainActivity2.this, "Échec de la connexion: " + errorCode, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, RegisterActivity.class));
                finish();
            }
        });
    }
}
