package com.cupagroup.controlcalidad.activities;

import androidx.room.ColumnInfo;

public class NavesListClass {
    @ColumnInfo(name = "id")
    public Long id_nave;

    @ColumnInfo(name = "name")
    public String name_nave;

    @ColumnInfo(name = "address")
    public  String address_nave;

    public NavesListClass(Long id_nave, String name_nave, String address_nave) {
        this.id_nave = id_nave;
        this.name_nave = name_nave;
        this.address_nave = address_nave;
    }

    public Long getId_nave() {
        return id_nave;
    }

    public void setId_nave(Long id_nave) {
        this.id_nave = id_nave;
    }

    public String getName_nave() {
        return name_nave;
    }

    public void setName_nave(String name_nave) {
        this.name_nave = name_nave;
    }

    public String getAddress_nave() {
        return address_nave;
    }

    public void setAddress_nave(String address_nave) {
        this.address_nave = address_nave;
    }
}
