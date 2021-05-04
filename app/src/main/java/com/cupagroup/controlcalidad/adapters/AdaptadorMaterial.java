package com.cupagroup.controlcalidad.adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cupagroup.controlcalidad.R;
import com.cupagroup.controlcalidad.db.AppDatabase;
import com.cupagroup.controlcalidad.modelo.Campos;

import java.util.Collections;
import java.util.List;

/**
 * Adaptador para caracteristicas del material usadas en la sección "Categorías"
 */
public class AdaptadorMaterial
        extends RecyclerView.Adapter<AdaptadorMaterial.ViewHolder> {

    private final List<Campos> items;
    private AppDatabase mAppDatabase;
    private SharedPreferences mPreferences;
    private SharedPreferences mDataShared;
    private String mShareFile = "pref_data";
    private String mShareCampos = "campos_data";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView etiqueta;
        public TextView titulo;
        public EditText input;
        public Spinner spinner;
        public LinearLayout container;

        public ViewHolder(View v) {
            super(v);

            container = (LinearLayout) v.findViewById(R.id.container_campos);
            etiqueta = (TextView) v.findViewById(R.id.sugerencia_campo);
            titulo = (TextView) v.findViewById(R.id.titulo_campo);
            input = (EditText) v.findViewById(R.id.edit_campo);
            spinner = (Spinner) v.findViewById(R.id.spinner_campo);
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
        mPreferences = viewGroup.getContext()
                .getSharedPreferences(mShareFile, Context.MODE_PRIVATE);
        mDataShared = viewGroup.getContext()
                .getSharedPreferences(mShareCampos, Context.MODE_PRIVATE);

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_campos, viewGroup, false);


        return new ViewHolder(v);
    }

    /*
     * La etiqueta es el hint
     */
    @Override
    public void onBindViewHolder(AdaptadorMaterial.ViewHolder viewHolder, int i) {
        String inputVal = null;
        String inputPosition = null;

        Campos item = items.get(i);
        viewHolder.etiqueta.setText(item.getEtiqueta());
        viewHolder.titulo.setText(item.getValor());
        float elevation = 4;
        int round = 3;

        if ( item.getValor().equals("FORMA")
                || item.getValor().equals("CALIDAD")
                || item.getValor().equals("ESPESOR")
                || item.getValor().equals("LARGO DE LA PIEZA")
                || item.getValor().equals("ANCHO DE LA PIEZA"))
        {
            inputVal = mPreferences.getString(item.getValor(), "");
            inputPosition = mDataShared.getString(item.getValor(), "");
        }else {
            inputVal = mDataShared.getString(item.getValor(), "");
        }
        viewHolder.input.setText(inputVal);
        switch (item.getValor()){
            case "FORMA":
                viewHolder.input.setVisibility(View.GONE);
                viewHolder.spinner.setVisibility(View.VISIBLE);
                mAppDatabase = AppDatabase.getInstance(viewHolder.itemView.getContext());
                List<String> formas = mAppDatabase.getFormas().getAll();

                ArrayAdapter<String> FormasAdapter =
                        new ArrayAdapter<String>(
                                viewHolder.itemView.getContext(),
                                R.layout.spinner_layout,
                                formas
                        );
                viewHolder.spinner.setAdapter(FormasAdapter);
                if (inputVal.isEmpty()){
                    viewHolder.spinner.setSelection(0);
                }else{
                    viewHolder.spinner.setSelection(Integer.parseInt(inputPosition));
                }

                break;
            case "CALIDAD":
                viewHolder.input.setVisibility(View.GONE);
                viewHolder.spinner.setVisibility(View.VISIBLE);
                mAppDatabase = AppDatabase.getInstance(viewHolder.itemView.getContext());
                List<String> quality = mAppDatabase.getCalidad().getAll();
                Collections.sort(quality);

                ArrayAdapter<String> QualityAdapter =
                        new ArrayAdapter<String>(
                                viewHolder.itemView.getContext(),
                                R.layout.spinner_layout,
                                quality
                        );
                viewHolder.spinner.setAdapter(QualityAdapter);
                if (inputVal.isEmpty()){
                    viewHolder.spinner.setSelection(0);
                }else{
                    viewHolder.spinner.setSelection(Integer.parseInt(inputPosition));
                }
                break;
            case "ESPESOR":
                viewHolder.input.setVisibility(View.GONE);
                viewHolder.spinner.setVisibility(View.VISIBLE);
                mAppDatabase = AppDatabase.getInstance(viewHolder.itemView.getContext());
                List<String> espesor = mAppDatabase.getEspesor().getAll();
                Collections.sort(espesor);

                ArrayAdapter<String> EspesorAdapter =
                        new ArrayAdapter<String>(
                                viewHolder.itemView.getContext(),
                                R.layout.spinner_layout,
                                espesor
                        );
                viewHolder.spinner.setAdapter(EspesorAdapter);
                if (inputVal.isEmpty()){
                    viewHolder.spinner.setSelection(0);
                }else{
                    viewHolder.spinner.setSelection(Integer.parseInt(inputPosition));
                }
                break;
        }


        // Escucha al cambiar el valor del spinner
        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = mDataShared.edit();
                SharedPreferences.Editor prefEditor = mPreferences.edit();
                Object item = parent.getItemAtPosition(position);

                TextView textView = (TextView)parent.getSelectedView();
                String spinnerText = textView.getText().toString();

                String tagName = String.valueOf(viewHolder.titulo.getText());

                if ( tagName.equals("FORMA")
                        || tagName.equals("CALIDAD")
                        || tagName.equals("ESPESOR"))
                {

                    editor.putString(tagName,String.valueOf(position));
                    prefEditor.putString(tagName,spinnerText);
                    prefEditor.apply();
                    editor.apply();
                }else {
                    editor.putString(tagName, String.valueOf(position));
                    editor.apply();

                    Log.e("SPINNER ",
                            "onItemSelected: "+ tagName + " -- "+ position+" -- "+ item  );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Escucha al cambiar el valor del input
        viewHolder.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences.Editor editor = mDataShared.edit();
                SharedPreferences.Editor prefEditor = mPreferences.edit();
                String tagName = String.valueOf(viewHolder.titulo.getText());
                String textVal = String.valueOf(viewHolder.input.getText());
                if ( tagName.equals("LARGO DE LA PIEZA") || tagName.equals("ANCHO DE LA PIEZA")
                ){
                    if(textVal.isEmpty() || textVal == "0"){
                        prefEditor.remove(tagName);
                    }else {
                        prefEditor.putString(tagName, textVal);
                        prefEditor.apply();
                    }
                }else{
                    if(textVal.isEmpty() || textVal == "0"){
                        editor.remove(tagName);
                        editor.apply();
                    }else {
                        editor.putString(tagName, textVal);
                        editor.apply();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
