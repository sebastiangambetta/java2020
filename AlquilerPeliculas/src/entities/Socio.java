package entities;

public class Socio{

    private int nroSocio;    
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    private String mail;
    private Integer nroTarjeta;
    private String estado;   
    
    public int getNroSocio() {
		return nroSocio;
	}
	public void setNroSocio(int nroSocio) {
		this.nroSocio = nroSocio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
    }
    public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
    }

    public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
    }

    public Integer getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(Integer nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
    }

    public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
    }

}