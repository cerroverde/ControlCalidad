package com.cupagroup.controlcalidad.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.db.AppDatabase;
import com.cupagroup.controlcalidad.modelo.Comida;
import com.cupagroup.controlcalidad.modelo.Credentials;

import static com.cupagroup.controlcalidad.sync.SyncManager.syncAll;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

    AppDatabase mAppDatabase;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView descripcion;
        public TextView titulo;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            descripcion = (TextView) v.findViewById(R.id.nombre_comida);
            titulo = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return Comida.COMIDAS_POPULARES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = Comida.COMIDAS_POPULARES.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.descripcion.setText(item.getDescripcion());
        viewHolder.titulo.setText(item.getTitulo());

        viewHolder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncAll(viewHolder.itemView.getContext(), Long.valueOf(99));

                Credentials userCert = new Credentials(
                        "malopez@cupagroup",
                        "1234567"
                );


                /*
                Credentials userCert = mAppDatabase
                        .getUser()
                        .getCredentials("malopez@cupagroup.com");
                */
                Toast.makeText(
                        viewHolder.itemView.getContext(),
                        userCert.getEmail(),
                        Toast.LENGTH_LONG).show();
            }

        });

    }



}