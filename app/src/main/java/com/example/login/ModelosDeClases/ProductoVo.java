package com.example.login.ModelosDeClases;

public class ProductoVo {
    private String imagen;
    private String id;
    private Integer precio;
    private String nombre;
    private Integer efect;
    private String description;
    private Integer type;
    public ProductoVo(){

    }
    public ProductoVo( String imagen,String id, Integer precio,String nombre, Integer efect, String description, Integer type) {
        this.imagen = imagen;
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.efect = efect;
        this.description = description;
        this.type = type;
        //this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String foto) {
        this.imagen = foto;
    }
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrip) {
        this.description = descrip;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    //public Integer getPrecio() {
    //    return precio;
    //}

    //public void setPrecio(Integer precio) {
    //    this.precio = precio;
    //}
}
