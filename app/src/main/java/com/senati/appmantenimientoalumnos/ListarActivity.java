package com.senati.appmantenimientoalumnos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senati.appmantenimientoalumnos.adaptadores.ListaAlumnosAdapter;
import com.senati.appmantenimientoalumnos.db.DbAlumnos;
import com.senati.appmantenimientoalumnos.entidades.Alumnos;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    RecyclerView listaAlumnos;
    TextView txtSinRegistros;
    FloatingActionButton fabRegresar;
    ArrayList<Alumnos> listaArrayAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);

        listaAlumnos = findViewById(R.id.listaAlumnos);
        txtSinRegistros = findViewById(R.id.txtSinRegistros);
        fabRegresar = findViewById(R.id.fabRegresar);
        listaAlumnos.setLayoutManager(new LinearLayoutManager(this));

        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cargarDatos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void cargarDatos() {
        DbAlumnos dbAlumnos = new DbAlumnos(this);
        listaArrayAlumnos = dbAlumnos.mostrarAlumnos();

        if (listaArrayAlumnos.size() > 0) {
            txtSinRegistros.setVisibility(View.GONE);
        } else {
            txtSinRegistros.setVisibility(View.VISIBLE);
        }

        ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(listaArrayAlumnos);
        listaAlumnos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos();
    }
}
