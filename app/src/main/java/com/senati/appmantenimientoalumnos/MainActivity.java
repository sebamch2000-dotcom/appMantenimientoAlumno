package com.senati.appmantenimientoalumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnCrear=findViewById(R.id.btnCrear);

        //Para que detecte en el momento que hagamos clic en el boton
        // OnClickListener inplementando el metodo
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamamos a nuestra clase dbHelper
                DbHelper dbHelper= new DbHelper(MainActivity.this);
                SQLiteDatabase db=dbHelper.getWritableDatabase();

                if ( db!=null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA" ,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERRROR AL CREAR BASE DE DATOS" ,Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //creamos un menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // "menu_principal" es el nombre del XML
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //creamos otro metodo
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nuevoRegistro(){
        Intent intent =new Intent(this,NuevoActivity.class);
        startActivity(intent);
    }
}