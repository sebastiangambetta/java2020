package entities;

import java.sql.Date;

public class Alquiler {
	private int nroAlquiler;
	private Date fechaAlquiler;
	private float importeAlquiler;
	private int socio;
	
	public int getNroAlquiler() {
		return nroAlquiler;
	}
	public void setNroAlquiler(int nroAlquiler) {
		this.nroAlquiler = nroAlquiler;
	}
	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}
	public void setFechaAlquiler(Date fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}
	public float getImporteAlquiler() {
		return importeAlquiler;
	}
	public void setImporteAlquiler(float importeAlquiler) {
		this.importeAlquiler = importeAlquiler;
	}
	public int getSocio() {
		return socio;
	}
	public void setSocio(int socio) {
		this.socio = socio;
	}	
}
