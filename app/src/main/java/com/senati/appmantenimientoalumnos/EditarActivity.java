package com.senati.appmantenimientoalumnos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senati.appmantenimientoalumnos.db.DbAlumnos;
import com.senati.appmantenimientoalumnos.entidades.Alumnos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtDni, txtCodigoId, txtTelefono, txtCorreoElectronico, 
             txtCarrera, txtSemestre, txtZonalSede, txtFechaIngreso, txtPromedio;
    Button btnActualizar;
    FloatingActionButton fabRegresar;
    int id = 0;
    Alumnos alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);

        txtNombre = findViewById(R.id.txtNombre);
        txtDni = findViewById(R.id.txtDni);
        txtCodigoId = findViewById(R.id.txtCodigoId);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtSemestre = findViewById(R.id.txtSemestre);
        txtZonalSede = findViewById(R.id.txtZonalSede);
        txtFechaIngreso = findViewById(R.id.txtFechaIngreso);
        txtPromedio = findViewById(R.id.txtPromedio);
        btnActualizar = findViewById(R.id.btnActualizar);
        fabRegresar = findViewById(R.id.fabRegresar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbAlumnos dbAlumnos = new DbAlumnos(EditarActivity.this);
        alumno = dbAlumnos.verAlumno(id);

        if (alumno != null) {
            txtNombre.setText(alumno.getNombre());
            txtDni.setText(alumno.getDni());
            txtCodigoId.setText(alumno.getCodigo_id());
            txtTelefono.setText(alumno.getTelefono());
            txtCorreoElectronico.setText(alumno.getCorreo());
            txtCarrera.setText(alumno.getCarrera());
            txtSemestre.setText(alumno.getSemestre());
            txtZonalSede.setText(alumno.getZonal());
            txtFechaIngreso.setText(alumno.getFecha());
            txtPromedio.setText(alumno.getPromedio());
        }

        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtDni.getText().toString().equals("")) {
                    boolean correcto = dbAlumnos.editarAlumno(id, 
                            txtNombre.getText().toString(),
                            txtDni.getText().toString(),
                            txtCodigoId.getText().toString(),
                            txtTelefono.getText().toString(),
                            txtCorreoElectronico.getText().toString(),
                            txtCarrera.getText().toString(),
                            txtSemestre.getText().toString(),
                            txtZonalSede.getText().toString(),
                            txtFechaIngreso.getText().toString(),
                            txtPromedio.getText().toString()
                    );

                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL ACTUALIZAR", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
