package com.cupagroup.controlcalidad.db.entity;

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

    @ColumnInfo(name = "size_width")
    private int size_width;

    @ColumnInfo(name = "size_height")
    private int size_height;

    @ColumnInfo(name = "forma")
    private int forma;

    @ColumnInfo(name = "espesor")
    private int espesor;

    @ColumnInfo(name = "calidad")
    private int calidad;

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

    @ColumnInfo(name = "create_at")
    private String create_at;

    public QualityControl(long quality_id, int size_width, int size_height,
                          int forma, int espesor, int calidad, int chaflan,
                          int roturas, int corte, int fina, int gruesa,
                          int refolio, int escuadra, int torcida, int nudos,
                          int piritas, int color, int vetas, int flor,
                          int sonido, int cortescuarzo, int totalfallos, String create_at) {
        this.quality_id = quality_id;
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

    public int getSize_width() {
        return size_width;
    }

    public void setSize_width(int size_width) {
        this.size_width = size_width;
    }

    public int getSize_height() {
        return size_height;
    }

    public void setSize_height(int size_height) {
        this.size_height = size_height;
    }

    public int getForma() {
        return forma;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public int getEspesor() {
        return espesor;
    }

    public void setEspesor(int espesor) {
        this.espesor = espesor;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
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

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}