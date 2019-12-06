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
    private EditText correo;
    private EditText contra1;
    private EditText contra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Generando instancia con firebase
        mAuth = FirebaseAuth.getInstance();

        //Botenes y mandando a llamar los campos Edit Text que se van a utilziar
        Button btnRegistrar = (Button) findViewById(R.id.btn_registrar);
        Button btnVolver = (Button) findViewById(R.id.btn_cancelar_registro);
        correo = (EditText) findViewById(R.id.ed_correo_reg);
        contra1 = (EditText) findViewById(R.id.ed_password_reg);
        contra2 = (EditText) findViewById(R.id.ed_password2_reg);

        //Un process dialog para mostrar una pantalla de carga al hacer la peticion
        progressDialog = new ProgressDialog(this);

        //Metodo listener de los botones
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        //Metodo listener de los botones
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Metodo registrar, que valida los campos del correo y las dos contraseñas
     * Se incluye el metodo registrarFirebase, que obtiene dos parametros el cual son correo y contraseña
     */
    private void registrar() {

        //Convirtiendo los EditText a String para poder compararlos y utilizarlos para las validaciones
        String correoS = correo.getText().toString().trim();
        String contraS = contra1.getText().toString().trim();
        String contra2S = contra2.getText().toString().trim();

        //Validaciones de los campos
        if (!correoS.isEmpty() && !contraS.isEmpty() && !contra2S.isEmpty()) {
            if (contraS.equalsIgnoreCase(contra2S)) {
                registrarFirebase(correoS, contraS);
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * El metodo registrarFirebase que recibe dos parametros correo y contraseña para validar el registro del nuevo usuario.
     * Se utiliza la instancia de firebase para llamar al metodo createUserWithEmailAndPassword que recibe los parametros del metodo
     * y se le agrega un metodo para escuchar si se complemento el proceso, en resumen en el metodo onComplete si la task se
     * completa correctamente se genera un nuevo objeto usuario de tipo FirebaseUser desde el usuario actual introducido y
     * se completa la accion, en caso contrario se lazaran los Toast.makeText por posibles errores.
     * @param correo
     * @param contra
     */
    private void registrarFirebase(String correo, String contra) {
        progressDialog.setMessage("Procesando solicitud...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegistroActivity.this, user.getEmail() + " ha sido registrado con exito", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(RegistroActivity.this, "Ha ocurrido un error, posiblemente la cuenta ya existe", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


}
