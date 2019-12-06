package com.example.subastasonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {
    //Variable de autenticacion de firebase
    private FirebaseAuth mAuth = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Generando instancia con firebase
         mAuth = FirebaseAuth.getInstance();

         Button btnRegistrar = (Button) findViewById(R.id.btn_registrar);
         Button btnVolver = (Button) findViewById(R.id.btn_cancelar_registro);

         progressDialog = new ProgressDialog(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registrar(){
        EditText correo = (EditText) findViewById(R.id.ed_correo_reg);
        EditText contra1 = (EditText) findViewById(R.id.ed_password_reg);
        EditText contra2 = (EditText) findViewById(R.id.ed_password2_reg);
        String correoS = correo.getText().toString().trim();
        String contraS = contra1.getText().toString().trim();
        String contra2S = contra2.getText().toString().trim();

        //Validaciones
        if (!correoS.isEmpty() && !contraS.isEmpty() && !contra2S.isEmpty()){
            if (contraS.equalsIgnoreCase(contra2S)){
                    progressDialog.setMessage("Procesando solicitud...");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(correoS, contraS)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(RegistroActivity.this, user.getEmail() + "ha sido registrado con exito", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegistroActivity.this, "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
        }
    }

//    private void registrarFirebase(String correo, String contra){
//
//    }




}
