package com.cupagroup.controlcalidad.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cupagroup.controlcalidad.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para poblar la lista de direcciones de la sección "Mi Cuenta"
 */
public class AdaptadorDirecciones
        extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView cantera;
        public TextView nave;
        public TextView direccion;
        public TextView telefono;

        public ViewHolder(View v) {
            super(v);
            cantera = (TextView) v.findViewById(R.id.tv_cantera);
            nave = (TextView) v.findViewById(R.id.tv_nave);
            direccion = (TextView) v.findViewById(R.id.tv_direccion);
            telefono = (TextView) v.findViewById(R.id.tv_telefono);
        }
    }


    public AdaptadorDirecciones() {
    }

    @Override
    public int getItemCount() {
        return Direccion.DIRECCIONES.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_direccion, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Direccion item = Direccion.DIRECCIONES.get(i);
        viewHolder.cantera.setText(item.cantera);
        viewHolder.nave.setText(item.nave);
        viewHolder.direccion.setText(item.direccion);
        viewHolder.telefono.setText(item.telefono);
    }

    /**
     * Modelo de datos para probar el adaptador
     */
    public static class Direccion {
        public String cantera;
        public String nave;
        public String direccion;
        public String telefono;

        public Direccion(String cantera, String nave,
                         String direccion, String telefono) {
            this.cantera = cantera;
            this.nave = nave;
            this.direccion = direccion;
            this.telefono = telefono;
        }

        public final static List<Direccion> DIRECCIONES = new ArrayList<Direccion>();

        static {
            DIRECCIONES.add(new Direccion("Cra 24 #2C-50", "Valle", "Cali", "3459821"));
            DIRECCIONES.add(new Direccion("Calle 100 Trans. 23", "Valle", "Cali", "4992600"));
            DIRECCIONES.add(new Direccion("Ave. 3ra N. #20-10", "Valle", "Cali", "4400725"));
        }
    }



}