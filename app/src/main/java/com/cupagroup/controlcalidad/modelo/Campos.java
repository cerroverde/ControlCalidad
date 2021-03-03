package com.cupagroup.controlcalidad.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Campos {
    private String titulo;
    private String etiqueta;

    public Campos(String titulo, String etiqueta) {
        this.titulo = titulo;
        this.etiqueta = etiqueta;
    }

    public static final List<Campos> MATERIALES = new ArrayList<>();
    public static final List<Campos> FORMA = new ArrayList<>();
    public static final List<Campos> PIEDRA = new ArrayList<>();

    static {
        MATERIALES.add(new Campos("Largo de la Pieza", "Este valor esta representado en centimetros"));
        MATERIALES.add(new Campos("Ancho de la Pieza", "Este valor esta representado en centimetros"));
        MATERIALES.add(new Campos("Forma", "Seleccione la forma correspondiente de la pieza"));
        MATERIALES.add(new Campos("Espesor", "Especifique el espesor correspondiente"));
        MATERIALES.add(new Campos("Calidad", "Seleccione el tipo de calidad"));

        FORMA.add(new Campos("Chaflán", "Corte y rebaje en la arista de las piezas"));
        FORMA.add(new Campos("Roturas", "Cantidad de roturas identificadas"));
        FORMA.add(new Campos("Mal Cortada", "Cantidad de cortes erróneos"));
        FORMA.add(new Campos("Fina", "Preguntar posible descripción"));
        FORMA.add(new Campos("Gruesa", "Preguntar posible descripción"));
        FORMA.add(new Campos("Refollo", "Preguntar posible descripción"));
        FORMA.add(new Campos("Falsa Escuadra", "Preguntar posible descripción"));

        PIEDRA.add(new Campos("Torcida","Numero de torceduras"));
        PIEDRA.add(new Campos("Nudos","Cantidad de nudos visibles"));
        PIEDRA.add(new Campos("Piritas Oxidables","Preguntar posible descripción"));
        PIEDRA.add(new Campos("Diferencias de Color","Preguntar posible descripción"));
        PIEDRA.add(new Campos("Vetas","Preguntar posible descripción"));
        PIEDRA.add(new Campos("Flor","Preguntar posible descripción"));
        PIEDRA.add(new Campos("Mal Sonido","Preguntar posible descripción"));

    }

    public String getValor() {
        return titulo;
    }
    public String getEtiqueta() {
        return etiqueta;
    }

}