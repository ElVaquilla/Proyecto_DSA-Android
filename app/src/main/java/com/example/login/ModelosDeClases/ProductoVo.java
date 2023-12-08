package com.example.login.ModelosDeClases;

public class ProductoVo {
    //private int foto;
    private String id;
    private Integer precio;
    private String nombre;
    private Integer efect;
    private String descrip;
    private Integer efectType;
    //private Integer precio;
    public ProductoVo(){

    }
    public ProductoVo( String id, Integer precio,String nombre, Integer efect, String descrip, Integer efectType) {
        //this.foto = foto;
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.efect = efect;
        this.descrip = descrip;
        this.efectType = efectType;
        //this.precio = precio;
    }

    //public int getFoto() {
    //    return foto;
    //}

    //public void setFoto(int foto) {
    //    this.foto = foto;
    //}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEfect() {
        return efect;
    }

    public void setEfect(Integer efect) {
        this.efect = efect;
    }
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getEfectType() {
        return efectType;
    }

    public void setEfectType(Integer efectType) {
        this.efectType = efectType;
    }

    //public Integer getPrecio() {
    //    return precio;
    //}

    //public void setPrecio(Integer precio) {
    //    this.precio = precio;
    //}
}
