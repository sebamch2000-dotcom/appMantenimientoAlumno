package com.senati.appmantenimientoalumnos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.senati.appmantenimientoalumnos.db.DbAlumnos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtDni, txtCodigoId, txtTelefono, txtCorreoElectronico, 
             txtCarrera, txtSemestre, txtZonalSede, txtFechaIngreso, txtPromedio;
    Button btnGuarda;
    FloatingActionButton fabRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo);

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
        btnGuarda = findViewById(R.id.BtnGuarda);
        fabRegresar = findViewById(R.id.fabRegresar);

        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRegistro();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void guardarRegistro() {
        if (!txtNombre.getText().toString().equals("") && !txtDni.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
            DbAlumnos dbAlumnos = new DbAlumnos(NuevoActivity.this);
            long id = dbAlumnos.insertarAlumno(
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

            if (id > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                limpiar();
            } else {
                Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS (Nombre, DNI, Teléfono)", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtDni.setText("");
        txtCodigoId.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtCarrera.setText("");
        txtSemestre.setText("");
        txtZonalSede.setText("");
        txtFechaIngreso.setText("");
        txtPromedio.setText("");
    }
}
