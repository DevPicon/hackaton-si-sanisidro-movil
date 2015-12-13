package pe.apiconz.android.sanisidromovil.model.entities;

import java.io.Serializable;

/**
 * Created by Armando on 12/13/2015.
 */
public class EventEntity implements Serializable {

    private String nombre;
    private String fecha;
    private int id;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
