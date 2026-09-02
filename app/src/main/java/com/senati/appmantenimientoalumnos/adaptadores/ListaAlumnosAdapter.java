package com.senati.appmantenimientoalumnos.adaptadores;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senati.appmantenimientoalumnos.EditarActivity;
import com.senati.appmantenimientoalumnos.R;
import com.senati.appmantenimientoalumnos.db.DbAlumnos;
import com.senati.appmantenimientoalumnos.entidades.Alumnos;

import java.util.ArrayList;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnoViewHolder> {

    ArrayList<Alumnos> listaAlumnos;

    public ListaAlumnosAdapter(ArrayList<Alumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        final Alumnos alumno = listaAlumnos.get(position);
        holder.viewNombre.setText(alumno.getNombre());
        holder.viewDni.setText("DNI: " + alumno.getDni());
        holder.viewTelefono.setText("Tel: " + alumno.getTelefono());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditarActivity.class);
                intent.putExtra("ID", alumno.getId());
                v.getContext().startActivity(intent);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("¿Desea eliminar a este alumno?")
                        .setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DbAlumnos dbAlumnos = new DbAlumnos(v.getContext());
                                if (dbAlumnos.eliminarAlumno(alumno.getId())) {
                                    listaAlumnos.remove(holder.getAdapterPosition());
                                    notifyItemRemoved(holder.getAdapterPosition());
                                    Toast.makeText(v.getContext(), "REGISTRO ELIMINADO", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDni, viewTelefono;
        ImageButton btnEditar, btnEliminar;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
