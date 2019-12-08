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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = null;
    private ProgressDialog progressDialog;
    private EditText correo;
    private EditText contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creando instancia con firebase
        mAuth = FirebaseAuth.getInstance();

        //Obteniendo los valores de los EditTex y los botones
        Button btnEntrarLog = (Button) findViewById(R.id.btn_entrar);
        Button btnRegistro = (Button) findViewById(R.id.btn_registro);
        correo = findViewById(R.id.ed_correo);
        contra = findViewById(R.id.ed_password);

        progressDialog = new ProgressDialog(this);

        //Metodo listener para los botones

        btnEntrarLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

        //Metodo listener para los botones
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Metodo para iniciar sesion con tus cuentas ya registradas, valida el usuario y el correo para asi
     * entrar en el metodo signInWithEmailAndPassword que recibe dos parametros, correo y contra.
     * Y que son escuchados por el metodo OnComplete, como en registro si se cumple la tarea se haria la accion
     */

    private void ingresar() {
        final String correo_s = correo.getText().toString();
        String contra_s = contra.getText().toString();

        if (!correo_s.isEmpty() && !contra_s.isEmpty()) {
            progressDialog.setMessage("Ingresando...");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(correo_s, contra_s)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("usuario", correo_s);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Bienvenido de nuevo " + user.getEmail(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Error al iniciar sesion", Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(this, "Por favor ingrese bien los datos!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Metodo OnStart que permite mantener la sesion del usuario si este no ha cerrado sesion.
     */
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            String correo = user.getEmail();
            i.putExtra("usuario", correo);
            startActivity(i);
        }
    }

    /**
     * Metodo OnRestart que se activa automaticamente cuando el usuario presiona el boton cerrar sesion.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        mAuth.signOut();
    }
}
