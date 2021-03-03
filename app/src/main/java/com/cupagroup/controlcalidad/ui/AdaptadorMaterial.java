package com.cupagroup.controlcalidad.ui;


import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.modelo.Campos;
import java.util.List;

/**
 * Adaptador para caracteristicas del material usadas en la sección "Categorías"
 */
public class AdaptadorMaterial
        extends RecyclerView.Adapter<AdaptadorMaterial.ViewHolder> {

    private final List<Campos> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView etiqueta;
        public TextView titulo;
        public EditText input;

        public ViewHolder(View v) {
            super(v);

            etiqueta = (TextView) v.findViewById(R.id.sugerencia_campo);
            titulo = (TextView) v.findViewById(R.id.titulo_campo);
            input = (EditText) v.findViewById(R.id.edit_campo);
        }
    }

    public AdaptadorMaterial(List<Campos> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_campos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorMaterial.ViewHolder viewHolder, int i) {
        Campos item = items.get(i);
        viewHolder.etiqueta.setText(item.getEtiqueta());
        viewHolder.titulo.setText(item.getValor());
        switch (item.getValor()){
            case "Chaflán":
                viewHolder.input.setInputType(InputType.TYPE_CLASS_TEXT);
                viewHolder.input.setHint("Valor del Chaflán");
                break;
            case "Forma":
                //viewHolder.input.setInputType(InputType.);
        }

    }
}
