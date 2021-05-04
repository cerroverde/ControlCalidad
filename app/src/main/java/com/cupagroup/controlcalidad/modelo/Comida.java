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

    public Comida(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Comida(
                "NUEVO ENSAYO",
                "Crear un nuevo ensayo de calidad")
        );
        COMIDAS_POPULARES.add(new Comida(
                "HISTORIAL",
                "Lista de ensayos anteriores")
        );
        COMIDAS_POPULARES.add(new Comida(
                "ESTADISTICAS",
                "Estadisticas sobre los diferentes resultados")
        );
    }

    public String getTitulo() {
        return titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }

}
