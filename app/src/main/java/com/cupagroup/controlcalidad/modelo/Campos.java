package com.cupagroup.controlcalidad.modelo;

import com.cupagroup.controlcalidad.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Campos {
    private String titulo;
    private String etiqueta;
    private AppDatabase mAppDatabase;


    public Campos(String titulo, String etiqueta) {
        this.titulo = titulo;
        this.etiqueta = etiqueta;
    }

    public static final List<Campos> FORMAS = new ArrayList<>();
    public static final List<Campos> MATERIALES = new ArrayList<>();
    public static final List<Campos> FORMA = new ArrayList<>();
    public static final List<Campos> PIEDRA = new ArrayList<>();

    static {
        MATERIALES.add(new Campos("LARGO DE LA PIEZA", "Este valor esta representado en centimetros"));
        MATERIALES.add(new Campos("ANCHO DE LA PIEZA", "Este valor esta representado en centimetros"));
        MATERIALES.add(new Campos("FORMA", "Seleccione la forma de la pieza"));
        MATERIALES.add(new Campos("ESPESOR", "Especifique el espesor correspondiente"));
        MATERIALES.add(new Campos("CALIDAD", "Seleccione el tipo de calidad"));

        FORMA.add(new Campos("CHAFLAN", "Corte y rebaje en la arista de las piezas"));
        FORMA.add(new Campos("ROTURAS", "Cantidad de roturas identificadas"));
        FORMA.add(new Campos("MAL CORTADA", "Cantidad de cortes erróneos"));
        FORMA.add(new Campos("FINA", "Preguntar posible descripción"));
        FORMA.add(new Campos("GRUESA", "Preguntar posible descripción"));
        FORMA.add(new Campos("REFOLLO", "Preguntar posible descripción"));
        FORMA.add(new Campos("FALSA ESCUADRA", "Preguntar posible descripción"));

        PIEDRA.add(new Campos("TORCIDA","Numero de torceduras"));
        PIEDRA.add(new Campos("NUDOS","Cantidad de nudos visibles"));
        PIEDRA.add(new Campos("PIRITAS OXIDABLES","Preguntar posible descripción"));
        PIEDRA.add(new Campos("DIFERENCIAS DE COLOR","Preguntar posible descripción"));
        PIEDRA.add(new Campos("VETAS","Preguntar posible descripción"));
        PIEDRA.add(new Campos("FLOR","Preguntar posible descripción"));
        PIEDRA.add(new Campos("MAL SONIDO","Preguntar posible descripción"));
        PIEDRA.add(new Campos("CORTES Y CUARZOS","Preguntar posible descripción"));

    }

    public String getValor() {
        return titulo;
    }
    public String getEtiqueta() { return etiqueta; }

}