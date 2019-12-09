package com.example.subastasonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearSubasta extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText nombreSubasta;
    private EditText nombreProducto;
    private EditText precioSubasta;
    private EditText descripcionSubasta;
    private Button btn_crearSubasta;
    private Button btn_cancelarSubasta;
    //private RecyclerView rv_lista_subastas;
    private ProgressDialog progressDialog;
    private AdapterSubasta adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_subasta);

        //Creando instancia de firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Botones y edit text
        nombreSubasta = (EditText) findViewById(R.id.ed_nombre_subasta);
        nombreProducto = (EditText) findViewById(R.id.ed_producto_subasta);
        precioSubasta = (EditText) findViewById(R.id.ed_precio_inicial_subasta);
        descripcionSubasta = (EditText) findViewById(R.id.ed_descripcion_subasta);
        //rv_lista_subastas = (RecyclerView) findViewById(R.id.rv_subastas);
        btn_crearSubasta = (Button) findViewById(R.id.btn_crear_subasta);
        btn_cancelarSubasta = (Button) findViewById(R.id.btn_cancelar_subasta);

//        adapter = new AdapterSubasta(this);
//        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
//        rv_lista_subastas.setLayoutManager(l);
//        rv_lista_subastas.setAdapter(adapter);

        //Instanciando el progress dialog
        progressDialog = new ProgressDialog(this);

        //Listener de los botones
        btn_cancelarSubasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CrearSubasta.this, MainActivity.class));
                finish();
            }
        });

        btn_crearSubasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addSubasta(new Subasta(nombreSubasta.getText().toString(), nombreProducto.getText().toString(),
                        precioSubasta.getText().toString(), descripcionSubasta.getText().toString()));
                startActivity(new Intent(CrearSubasta.this, MainActivity.class));
                finish();
            }
        });

    }


}
