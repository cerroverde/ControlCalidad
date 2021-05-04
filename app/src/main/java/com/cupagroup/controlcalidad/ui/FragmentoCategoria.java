package com.cupagroup.controlcalidad.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.adapters.AdaptadorCategorias;
import com.cupagroup.controlcalidad.adapters.AdaptadorMaterial;
import com.cupagroup.controlcalidad.adapters.AdaptadorMiscelaneo;
import com.cupagroup.controlcalidad.modelo.Campos;

import java.util.Objects;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Categorías"
 */
public class FragmentoCategoria extends Fragment {

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;
    private AdaptadorMaterial adaptadorMaterial;
    private AdaptadorMiscelaneo adaptadorMiscelaneo;

    public static FragmentoCategoria nuevaInstancia(int indiceSeccion) {
        FragmentoCategoria fragment = new FragmentoCategoria();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        int indiceSeccion = getArguments().getInt(INDICE_SECCION);
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);

        reciclador.setLayoutManager(layoutManager);

        switch (indiceSeccion) {
            case 0:
                adaptadorMaterial = new AdaptadorMaterial(Campos.MATERIALES);
                break;
            case 1:
                adaptadorMaterial = new AdaptadorMaterial(Campos.FORMA);
                break;
            case 2:
                adaptadorMaterial = new AdaptadorMaterial(Campos.PIEDRA);
                break;
        }

        reciclador.setAdapter(adaptadorMaterial);
        return view;
    }
}