package com.cupagroup.controlcalidad.activities;

import androidx.room.ColumnInfo;

import java.io.Serializable;

public class CanterasListClass  implements Serializable {
    @ColumnInfo(name = "id")
    private Long idCantera;

    @ColumnInfo(name="name")
    private String nameCantera;

    @ColumnInfo(name="nave")
    private String nameNave;

    @ColumnInfo(name = "address")
    private String addressNave;

    public CanterasListClass(Long idCantera,String nameCantera) {
        this.idCantera = idCantera;
        this.nameCantera = nameCantera;

    }

    public Long getIdCantera() {
        return idCantera;
    }

    public void setIdCantera(Long idCantera) {
        this.idCantera = idCantera;
    }

    public String getNameCantera() {
        return nameCantera;
    }

    public void setNameCantera(String nameCantera) {
        this.nameCantera = nameCantera;
    }

    public String getNameNave() {
        return nameNave;
    }

    public void setNameNave(String nameNave) {
        this.nameNave = nameNave;
    }

    public String getAddressNave() {
        return addressNave;
    }

    public void setAddressNave(String addressNave) {
        this.addressNave = addressNave;
    }
}
