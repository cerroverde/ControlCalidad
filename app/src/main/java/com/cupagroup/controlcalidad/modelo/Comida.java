package com.cupagroup.controlcalidad.modelo;

import com.cupagroup.controlcalidad.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Comida {
    private String titulo;
    private String descripcion;
    private int idDrawable;

    public Comida(String titulo, String descripcion, int idDrawable) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idDrawable = idDrawable;
    }

    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Comida(
                "Nuevo Ensayo",
                "Crear un nuevo ensayo de calidad",
                R.drawable.fondo_sesiones_sony_a)
        );
        COMIDAS_POPULARES.add(new Comida(
                "Historial",
                "Lista de ensayos anteriores",
                R.drawable.fondo_sesiones_d)
        );
        COMIDAS_POPULARES.add(new Comida(
                "Estadisticas",
                "Estadisticas sobre los diferentes resultados",
                R.drawable.fondo_sesiones_e)
        );

        /*
        PLATILLOS.add(new Comida(5, "Camarones Tismados", R.drawable.camarones));
        PLATILLOS.add(new Comida(3.2f, "Rosca Herbárea", R.drawable.rosca));
        PLATILLOS.add(new Comida(12f, "Sushi Extremo", R.drawable.sushi));
        PLATILLOS.add(new Comida(9, "Sandwich Deli", R.drawable.sandwich));
        PLATILLOS.add(new Comida(34f, "Lomo De Cerdo Austral", R.drawable.lomo_cerdo));

        BEBIDAS.add(new Comida(3, "Taza de Café", R.drawable.cafe));
        BEBIDAS.add(new Comida(12, "Coctel Tronchatoro", R.drawable.coctel));
        BEBIDAS.add(new Comida(5, "Jugo Natural", R.drawable.jugo_natural));
        BEBIDAS.add(new Comida(24, "Coctel Jordano", R.drawable.coctel_jordano));
        BEBIDAS.add(new Comida(30, "Botella Vino Tinto Darius", R.drawable.vino_tinto));

        POSTRES.add(new Comida(2, "Postre De Vainilla", R.drawable.postre_vainilla));
        POSTRES.add(new Comida(3, "Flan Celestial", R.drawable.flan_celestial));
        POSTRES.add(new Comida(2.5f, "Cupcake Festival", R.drawable.cupcakes_festival));
        POSTRES.add(new Comida(4, "Pastel De Fresa", R.drawable.pastel_fresa));
        POSTRES.add(new Comida(5, "Muffin Amoroso", R.drawable.muffin_amoroso));

        */
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
