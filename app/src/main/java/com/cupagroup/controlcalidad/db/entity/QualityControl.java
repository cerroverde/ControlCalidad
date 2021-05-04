package com.cupagroup.controlcalidad.db.entity;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_QUALITY)
public class QualityControl implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long quality_id;

    @ColumnInfo(name = "ensayo_id")
    private long ensayo_id;

    @ColumnInfo(name = "session_id")
    private long session_id;

    @ColumnInfo(name = "size_width")
    private String size_width;

    @ColumnInfo(name = "size_height")
    private String size_height;

    @ColumnInfo(name = "forma")
    private String forma;

    @ColumnInfo(name = "espesor")
    private Double espesor;

    @ColumnInfo(name = "calidad")
    private String calidad;

    @ColumnInfo(name = "chaflan")
    private int chaflan;

    @ColumnInfo(name = "roturas")
    private int roturas;

    @ColumnInfo(name = "corte")
    private int corte;

    @ColumnInfo(name = "fina")
    private int fina;

    @ColumnInfo(name = "gruesa")
    private int gruesa;

    @ColumnInfo(name = "refolio")
    private int refolio;

    @ColumnInfo(name = "escuadra")
    private int escuadra;

    @ColumnInfo(name = "torcida")
    private int torcida;

    @ColumnInfo(name = "nudos")
    private int nudos;

    @ColumnInfo(name = "piritas")
    private int piritas;

    @ColumnInfo(name = "color")
    private int color;

    @ColumnInfo(name = "vetas")
    private int vetas;

    @ColumnInfo(name = "flor")
    private int flor;

    @ColumnInfo(name = "sonido")
    private int sonido;

    @ColumnInfo(name = "cortescuarzo")
    private int cortescuarzo;

    @ColumnInfo(name = "totalfallos")
    private int totalfallos;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "create_at")
    private String create_at;

    public QualityControl(Long session_id, Long ensayo_id, String size_width, String size_height,
                          String forma, Double espesor, String calidad, int chaflan,
                          int roturas, int corte, int fina, int gruesa,
                          int refolio, int escuadra, int torcida, int nudos,
                          int piritas, int color, int vetas, int flor,
                          int sonido, int cortescuarzo, int totalfallos, String comment,
                          String create_at) {
        this.session_id = session_id;
        this.ensayo_id = ensayo_id;
        this.size_width = size_width;
        this.size_height = size_height;
        this.forma = forma;
        this.espesor = espesor;
        this.calidad = calidad;
        this.chaflan = chaflan;
        this.roturas = roturas;
        this.corte = corte;
        this.fina = fina;
        this.gruesa = gruesa;
        this.refolio = refolio;
        this.escuadra = escuadra;
        this.torcida = torcida;
        this.nudos = nudos;
        this.piritas = piritas;
        this.color = color;
        this.vetas = vetas;
        this.flor = flor;
        this.sonido = sonido;
        this.cortescuarzo = cortescuarzo;
        this.totalfallos = totalfallos;
        this.comment = comment;
        this.create_at = create_at;
    }

    @Ignore
    public QualityControl(){
        //Empty
    }

    public long getQuality_id() {
        return quality_id;
    }
    public void setQuality_id(long quality_id) {
        this.quality_id = quality_id;
    }

    public long getSession_id() { return session_id; }
    public void setSession_id(long session_id) { this.session_id = session_id; }

    public long getEnsayo_id() { return ensayo_id; }
    public void setEnsayo_id(long ensayo_id) { this.ensayo_id = ensayo_id; }

    public String getSize_width() {
        return size_width;
    }
    public void setSize_width(String size_width) {
        this.size_width = size_width;
    }

    public String getSize_height() {
        return size_height;
    }
    public void setSize_height(String size_height) {
        this.size_height = size_height;
    }

    public String getForma() {
        return forma;
    }
    public void setForma(String forma) {
        this.forma = forma;
    }

    public Double getEspesor() {
        return espesor;
    }
    public void setEspesor(Double espesor) {
        this.espesor = espesor;
    }

    public String getCalidad() {
        return calidad;
    }
    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getChaflan() {
        return chaflan;
    }
    public void setChaflan(int chaflan) {
        this.chaflan = chaflan;
    }

    public int getRoturas() {
        return roturas;
    }
    public void setRoturas(int roturas) {
        this.roturas = roturas;
    }

    public int getCorte() {
        return corte;
    }
    public void setCorte(int corte) {
        this.corte = corte;
    }

    public int getFina() {
        return fina;
    }
    public void setFina(int fina) {
        this.fina = fina;
    }

    public int getGruesa() {
        return gruesa;
    }
    public void setGruesa(int gruesa) {
        this.gruesa = gruesa;
    }

    public int getRefolio() {
        return refolio;
    }
    public void setRefolio(int refolio) {
        this.refolio = refolio;
    }

    public int getEscuadra() {
        return escuadra;
    }
    public void setEscuadra(int escuadra) {
        this.escuadra = escuadra;
    }

    public int getTorcida() {
        return torcida;
    }
    public void setTorcida(int torcida) {
        this.torcida = torcida;
    }

    public int getNudos() {
        return nudos;
    }
    public void setNudos(int nudos) {
        this.nudos = nudos;
    }

    public int getPiritas() {
        return piritas;
    }
    public void setPiritas(int piritas) {
        this.piritas = piritas;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getVetas() {
        return vetas;
    }
    public void setVetas(int vetas) {
        this.vetas = vetas;
    }

    public int getFlor() {
        return flor;
    }
    public void setFlor(int flor) {
        this.flor = flor;
    }

    public int getSonido() {
        return sonido;
    }
    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getCortescuarzo() {
        return cortescuarzo;
    }
    public void setCortescuarzo(int cortescuarzo) {
        this.cortescuarzo = cortescuarzo;
    }

    public int getTotalfallos() {
        return totalfallos;
    }
    public void setTotalfallos(int totalfallos) {
        this.totalfallos = totalfallos;
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getCreate_at() {
        return create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}