package entities;

import java.sql.Date;

public class LineaAlquiler {

    private Date fechaDevolucion;
    private int diasAlquiler;
    private float importeLineaAlquiler;
    private int pelicula;
    private int alquiler;

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public float getImporteLineaAlquiler() {
        return importeLineaAlquiler;
    }

    public void setImporteAlquiler(float importeLineaAlquiler) {
        this.importeLineaAlquiler = importeLineaAlquiler;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

}
